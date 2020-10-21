package org.evaluator.client.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class CommandInvokerTest {

  @Test
  void shouldExecuteCommand() {
    Command mockCommand = mock(Command.class);
    CommandInvoker commandInvoker = new CommandInvoker();

    commandInvoker.invoke(mockCommand);

    verify(mockCommand, times(1)).execute();
  }
}
