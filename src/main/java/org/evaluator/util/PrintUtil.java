package org.evaluator.util;

import org.evaluator.client.command.Command;

public class PrintUtil {

  private PrintUtil() {
  }

  public static void printExecutingCommand(Class<? extends Command> commandClass) {
    System.out.println("Executing " + commandClass.getSimpleName() + "...");
  }
}
