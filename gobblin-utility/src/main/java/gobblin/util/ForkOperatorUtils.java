/* (c) 2014 LinkedIn Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package gobblin.util;

import com.google.common.base.Preconditions;

import gobblin.configuration.ConfigurationKeys;
import gobblin.configuration.State;


/**
 * Utility class for use with the {@link gobblin.fork.ForkOperator} class.
 *
 * @author ynli
 */
public class ForkOperatorUtils {

  /**
   * Get a branch name for the given branch.
   *
   * @param state       a {@link State} object carrying configuration properties
   * @param index       branch index (non-negative)
   * @param defaultName default branch name
   * @return a branch name
   */
  public static String getBranchName(State state, int index, String defaultName) {
    Preconditions.checkArgument(index >= 0, "index is expected to be non-negative");
    return state.getProp(ConfigurationKeys.FORK_BRANCH_NAME_KEY + "." + index, defaultName);
  }

  /**
   * Get a new property key from an original one with branch index (if applicable).
   *
   * @param key      property key
   * @param branches number of branches (non-negative)
   * @param index    branch index (non-negative)
   * @return a new property key
   */
  public static String getPropertyNameForBranch(String key, int branches, int index) {
    Preconditions.checkArgument(index >= 0, "index is expected to be non-negative");
    Preconditions.checkArgument(branches >= 0, "branches is expected to be non-negative");
    return branches > 1 ? key + "." + index : key;
  }

  /**
   * Get a new property key from an original one with branch index (if applicable).
   *
   * @param key    property key
   * @param branch branch index
   * @return a new property key
   */
  public static String getPropertyNameForBranch(String key, int branch) {
    // A branch index of -1 means there is no fork and branching
    return branch >= 0 ? key + "." + branch : key;
  }

  /**
   * Get a new path with the given branch name as a sub directory.
   *
   * @param branchName branch name
   * @param branches   number of branches (non-negative)
   * @return a new path
   */
  public static String getPathForBranch(String path, String branchName, int branches) {
    Preconditions.checkArgument(branches >= 0, "branches is expected to be non-negative");
    return branches > 1 ? path + "/" + branchName : path;
  }

  /**
   * Get the fork branch ID of a branch of a given task.
   *
   * @param taskId task ID
   * @param index  branch index
   * @return a fork branch ID
   */
  public static String getForkId(String taskId, int index) {
    return taskId + "." + index;
  }
}
