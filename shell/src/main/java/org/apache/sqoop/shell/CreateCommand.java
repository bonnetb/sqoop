/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.shell;

import org.apache.sqoop.shell.core.Constants;
import org.codehaus.groovy.tools.shell.Shell;

import java.util.List;

import static org.apache.sqoop.shell.ShellEnvironment.*;

/**
 *
 */
public class CreateCommand extends SqoopCommand {

  private CreateConnectionFunction connectionFunction;
  private CreateJobFunction jobFunction;

  public CreateCommand(Shell shell) {
    super(shell, Constants.CMD_CREATE, Constants.CMD_CREATE_SC,
      new String[] {Constants.FN_CONNECTION, Constants.FN_JOB},
      Constants.PRE_CREATE, Constants.SUF_INFO);
  }

  public Object executeCommand(List args) {
    if (args.size() == 0) {
      printlnResource(Constants.RES_CREATE_USAGE, getUsage());
      return null;
    }

    String func = (String)args.get(0);
    if (func.equals(Constants.FN_CONNECTION)) {
      if (connectionFunction == null) {
        connectionFunction = new CreateConnectionFunction();
      }
      return connectionFunction.execute(args);
    } else if (func.equals(Constants.FN_JOB)) {
      if (jobFunction == null) {
        jobFunction = new CreateJobFunction();
      }
      return jobFunction.execute(args);
    } else {
      printlnResource(Constants.RES_FUNCTION_UNKNOWN, func);
      return null;
    }
  }
}