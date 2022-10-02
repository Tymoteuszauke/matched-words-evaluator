package org.evaluator.client.command;

public class CommandInvoker {

  public void invoke(Command command) {
//    System.out.println("Executing " + command.getClass().getSimpleName() + "...");
    command.execute();
  }
}
