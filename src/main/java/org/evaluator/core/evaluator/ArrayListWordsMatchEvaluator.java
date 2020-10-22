package org.evaluator.core.evaluator;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.evaluator.core.dto.SourceEvaluation;
import org.evaluator.core.dto.SourceEvaluations;

public class ArrayListWordsMatchEvaluator implements WordsMatchEvaluator {

  private final Map<String, List<String>> wordsData;

  public ArrayListWordsMatchEvaluator(Map<String, List<String>> wordsData) {
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
