package org.evaluator.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.evaluator.client.command.Command;
import org.evaluator.client.command.CommandInvoker;
import org.evaluator.client.command.commands.ExecuteSearchCommand;
import org.evaluator.client.command.commands.PrintUnknownCommandCommand;
import org.evaluator.core.WordsMatchEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InputConsumerTest {

  @Mock
  private CommandInvoker commandInvoker;

  @Mock
  private WordsMatchEvaluator wordsMatchEvaluator;

  @Mock
  private Command mockCommand;

  private InputConsumer inputConsumer;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    inputConsumer = new InputConsumer(commandInvoker, wordsMatchEvaluator, " ,.");
  }

  @Test
  void inputConsumerTest_shouldExecuteMockCommand() {
    when(mockCommand.getKey()).thenReturn(":mock");
    inputConsumer.addCommand(mockCommand);
    inputConsumer.accept(":mock");
    verify(commandInvoker, times(1)).invoke(eq(mockCommand));
  }

  @Test
  void inputConsumerTest_shouldExecutePrintUnknownCommandCommand() {
    inputConsumer.accept(":mock");
    verify(commandInvoker, times(1)).invoke(any(PrintUnknownCommandCommand.class));
  }

  @Test
  void inputConsumerTest_shouldExecuteSearchCommand() {
    String shouldExecuteSearchCommandString = "should,execute.search command";
    List<String> expectedInputWords = List.of("should", "execute", "search", "command");

    inputConsumer.accept(shouldExecuteSearchCommandString);
    verify(commandInvoker, times(1))
        .invoke(eq(new ExecuteSearchCommand(expectedInputWords, wordsMatchEvaluator)));
  }
}
