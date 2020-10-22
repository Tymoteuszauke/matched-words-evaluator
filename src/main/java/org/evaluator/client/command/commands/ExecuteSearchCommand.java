package org.evaluator.client.command.commands;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.evaluator.client.command.Command;
import org.evaluator.core.dto.SourceEvaluations;
import org.evaluator.core.evaluator.WordsMatchEvaluator;

public class ExecuteSearchCommand implements Command {

  private final List<String> inputWords;
  private final WordsMatchEvaluator wordsMatchEvaluator;

  public ExecuteSearchCommand(
      List<String> inputWords, WordsMatchEvaluator wordsMatchEvaluator) {
    this.inputWords = inputWords;
    this.wordsMatchEvaluator = wordsMatchEvaluator;
  }

  @Override
  public String getKey() {
    return ":search";
  }

  @Override
  public void execute() {
    SourceEvaluations evaluations = wordsMatchEvaluator.getEvaluations(inputWords);
    System.out.println(evaluations);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ExecuteSearchCommand.class.getSimpleName() + "[", "]")
        .add("inputWords=" + inputWords)
        .add("wordsMatchScoreEvaluator=" + wordsMatchEvaluator)
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
        Objects.equals(wordsMatchEvaluator, that.wordsMatchEvaluator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputWords, wordsMatchEvaluator);
  }
}
