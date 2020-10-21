package org.evaluator.core;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.evaluator.dto.SourceEvaluation;
import org.evaluator.dto.SourceEvaluations;

public class ArrayListWordsMatchScoreEvaluator implements WordsMatchScoreEvaluator {

  private final Map<String, List<String>> wordsData;

  public ArrayListWordsMatchScoreEvaluator(Map<String, List<String>> wordsData) {
    this.wordsData = wordsData;
  }

  @Override
  public SourceEvaluations getEvaluations(List<String> inputWords) {
    Instant start = Instant.now();
    SourceEvaluations scores = new SourceEvaluations();

    int inputWordsAmount = inputWords.size();
    wordsData.forEach(
        (key, value) -> {
          long matchedWordsCount = inputWords.stream().filter(value::contains).count();
          scores.put(
              SourceEvaluation.of(key, calculatePercents(inputWordsAmount, matchedWordsCount)));
        });
    Instant finish = Instant.now();
    System.out.println(
        "getEvaluations finished after "
            + Duration.between(start, finish).toMillis()
            + " milliseconds");
    return scores;
  }
}
