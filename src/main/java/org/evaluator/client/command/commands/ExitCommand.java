package org.evaluator.client.command.commands;

import org.evaluator.client.InputReceiver;
import org.evaluator.client.command.Command;

public class ExitCommand implements Command {

  private final InputReceiver inputReceiver;

  public ExitCommand(InputReceiver inputReceiver) {
    this.inputReceiver = inputReceiver;
  }

  @Override
  public String getKey() {
    return ":exit";
  }

  @Override
  public void execute() {
    inputReceiver.stop();
    System.exit(0);
  }
}
