package org.evaluator.client.command.commands;

import java.util.List;
import org.evaluator.client.command.Command;

public class PrintUnknownCommandCommand implements Command {

  private final List<String> knownCommands;

  public PrintUnknownCommandCommand(List<String> knownCommands) {
    this.knownCommands = knownCommands;
  }

  @Override
  public String getKey() {
    return null;
  }

  @Override
  public void execute() {
    System.out.println("Unknown command! List of known commands: " + knownCommands);
  }
}
