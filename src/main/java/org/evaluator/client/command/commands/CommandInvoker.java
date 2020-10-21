package org.evaluator.client.command.commands;

import org.evaluator.client.command.Command;

public class CommandInvoker {

  public void invoke(Command command) {
    command.execute();
  }
}
