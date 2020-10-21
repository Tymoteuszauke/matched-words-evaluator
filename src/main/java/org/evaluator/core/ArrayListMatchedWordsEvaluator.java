package org.evaluator.core;

import java.util.List;
import java.util.Map;
import org.evaluator.dto.SourceEvaluation;
import org.evaluator.dto.SourceEvaluations;

public class ArrayListMatchedWordsEvaluator implements MatchedWordsEvaluator {

  private final Map<String, List<String>> wordsData;

  public ArrayListMatchedWordsEvaluator(Map<String, List<String>> wordsData) {
    this.wordsData = wordsData;
  }

  @Override
  public SourceEvaluations getEvaluations(List<String> inputWords) {
    SourceEvaluations scores = new SourceEvaluations();

    int inputWordsAmount = inputWords.size();
    wordsData.forEach(
        (key, value) -> {
          long matchedWordsCount = inputWords.stream().filter(value::contains).count();
          scores.put(
              SourceEvaluation.of(key, calculatePercents(inputWordsAmount, matchedWordsCount)));
        });

    return scores;
  }
}
