package org.evaluator.client.command.commands;

import java.util.List;
import org.evaluator.client.command.Command;
import org.evaluator.core.ArrayListMatchedWordsEvaluator;
import org.evaluator.dto.SourceEvaluations;

public class ExecuteSearchCommand implements Command {

  private final List<String> inputWords;
  private final ArrayListMatchedWordsEvaluator arrayListMatchedWordsEvaluator;

  public ExecuteSearchCommand(
      List<String> inputWords, ArrayListMatchedWordsEvaluator arrayListMatchedWordsEvaluator) {
    this.inputWords = inputWords;
    this.arrayListMatchedWordsEvaluator = arrayListMatchedWordsEvaluator;
  }

  @Override
  public String getKey() {
    return ":search";
  }

  @Override
  public void execute() {
    System.out.println("Executing command " + getClass().getSimpleName());
    SourceEvaluations evaluations = arrayListMatchedWordsEvaluator.getEvaluations(inputWords);
    System.out.println(evaluations);
  }
}
