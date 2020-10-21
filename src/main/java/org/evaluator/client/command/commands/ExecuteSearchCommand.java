package org.evaluator.client.command.commands;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.evaluator.client.command.Command;
import org.evaluator.core.WordsMatchScoreEvaluator;
import org.evaluator.dto.SourceEvaluations;

public class ExecuteSearchCommand implements Command {

  private final List<String> inputWords;
  private final WordsMatchScoreEvaluator wordsMatchScoreEvaluator;

  public ExecuteSearchCommand(
      List<String> inputWords, WordsMatchScoreEvaluator wordsMatchScoreEvaluator) {
    this.inputWords = inputWords;
    this.wordsMatchScoreEvaluator = wordsMatchScoreEvaluator;
  }

  @Override
  public String getKey() {
    return ":search";
  }

  @Override
  public void execute() {
    SourceEvaluations evaluations = wordsMatchScoreEvaluator.getEvaluations(inputWords);
    System.out.println(evaluations);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ExecuteSearchCommand.class.getSimpleName() + "[", "]")
        .add("inputWords=" + inputWords)
        .add("wordsMatchScoreEvaluator=" + wordsMatchScoreEvaluator)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExecuteSearchCommand that = (ExecuteSearchCommand) o;
    return Objects.equals(inputWords, that.inputWords) &&
        Objects.equals(wordsMatchScoreEvaluator, that.wordsMatchScoreEvaluator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputWords, wordsMatchScoreEvaluator);
  }
}
