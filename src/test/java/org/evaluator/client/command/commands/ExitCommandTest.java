package org.evaluator.client.command.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.evaluator.client.receiver.ScannerInputReceiver;
import org.junit.jupiter.api.Test;

class ExitCommandTest {

  @Test
  void shouldInvokeStop() {
    ScannerInputReceiver scannerInputReceiver = mock(ScannerInputReceiver.class);
    ExitCommand exitCommand = new ExitCommand(scannerInputReceiver);

    exitCommand.execute();

    verify(scannerInputReceiver, times(1)).stop();
    assertEquals(":exit", exitCommand.getKey());
  }
}
