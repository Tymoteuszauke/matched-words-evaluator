package org.evaluator.client.command.commands;

import org.evaluator.client.command.Command;

public class PrintUnknownCommandCommand implements Command {

  @Override
  public String getKey() {
    return null;
  }

  @Override
  public void execute() {
    System.out.println("Unknown command! List of known commands: {insert command list}");
  }
}
