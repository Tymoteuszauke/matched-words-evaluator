package org.evaluator.client.receiver;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

public class ScannerInputReceiver implements InputReceiver {

  private final Consumer<String> inputConsumer;
  private final InputStream inputStream;
  private boolean isRunning = true;

  public ScannerInputReceiver(Consumer<String> inputConsumer, InputStream inputStream) {
    this.inputConsumer = inputConsumer;
    this.inputStream = inputStream;
  }

  public void receiveInputs() {
    Scanner scanner = new Scanner(inputStream);
    String command;
    while (isRunning) {
      command = scanner.nextLine();
      inputConsumer.accept(command);
    }
  }

  public void stop() {
    isRunning = false;
  }
}
