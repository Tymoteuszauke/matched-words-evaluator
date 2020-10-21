package org.evaluator.client.command.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.evaluator.client.InputReceiver;
import org.junit.jupiter.api.Test;

class ExitCommandTest {

  @Test
  void shouldInvokeStop() {
    InputReceiver inputReceiver = mock(InputReceiver.class);
    ExitCommand exitCommand = new ExitCommand(inputReceiver);

    exitCommand.execute();

    verify(inputReceiver, times(1)).stop();
    assertEquals(":exit", exitCommand.getKey());
  }
}
