package org.evaluator.client.receiver;

import java.io.Console;
import java.io.InputStream;
import java.util.function.Consumer;

public class ConsoleInputReceiver implements InputReceiver {

  private final Consumer<String> inputConsumer;
  private boolean isRunning = true;

  public ConsoleInputReceiver(Consumer<String> inputConsumer, InputStream inputStream) {
    this.inputConsumer = inputConsumer;
  }

  @Override
  public void receiveInputs() {
    Console console = System.console();
    String command;
    while (isRunning) {
      command = console.readLine("search> ");
      inputConsumer.accept(command);
    }
  }

  @Override
  public void stop() {
    isRunning = false;
  }
}
