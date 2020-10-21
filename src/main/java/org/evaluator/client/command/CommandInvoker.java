package org.evaluator.client.command;

import static org.evaluator.util.PrintUtil.printExecutingCommand;

public class CommandInvoker {

  public void invoke(Command command) {
    printExecutingCommand(command.getClass());
    command.execute();
  }
}
