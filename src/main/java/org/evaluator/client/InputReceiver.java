package org.evaluator.client;

import java.util.Scanner;
import java.util.function.Consumer;

public class InputReceiver {

  private final Consumer<String> inputConsumer;
  private boolean isRunning = true;

  public InputReceiver(Consumer<String> inputConsumer) {
    this.inputConsumer = inputConsumer;
  }

  public void getInputs() {
    boolean run = true;
    Scanner scanner = new Scanner(System.in);
//    Console console = System.console();
    String command;
    while (run) {
      command = scanner.nextLine();
//      command = console.readLine("search> ");
      inputConsumer.accept(command);
    }
  }

  public void stop() {
    isRunning = false;
  }
}
