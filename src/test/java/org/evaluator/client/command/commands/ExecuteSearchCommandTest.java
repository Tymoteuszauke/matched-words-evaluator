package org.evaluator.client.command.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.evaluator.core.WordsMatchScoreEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ExecuteSearchCommandTest {

  @Mock
  private WordsMatchScoreEvaluator wordsMatchScoreEvaluator;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldInvokeGetEvaluations() {
    List<String> inputWords = List.of("Tolkien", "Sapkowski", "Gombrowicz");
    ExecuteSearchCommand executeSearchCommand =
        new ExecuteSearchCommand(inputWords, wordsMatchScoreEvaluator);
    executeSearchCommand.execute();

    verify(wordsMatchScoreEvaluator, times(1)).getEvaluations(eq(inputWords));
    assertEquals(":search", executeSearchCommand.getKey());
  }
}
