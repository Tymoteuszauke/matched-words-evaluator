package org.evaluator;

import java.util.List;
import java.util.Map;
import org.evaluator.dto.SourceEvaluation;
import org.evaluator.dto.SourceEvaluations;

public class MatchedWordsEvaluator {

  private final Map<String, List<String>> wordsData;

  public MatchedWordsEvaluator(Map<String, List<String>> wordsData) {
    this.wordsData = wordsData;
  }

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

  private double calculatePercents(double total, double obtained) {
    if (total < 0) {
      throw new UnsupportedOperationException(
          "Cannot evaluate percentage for total value less than 0");
    }
    return obtained * 100 / total;
  }
}
