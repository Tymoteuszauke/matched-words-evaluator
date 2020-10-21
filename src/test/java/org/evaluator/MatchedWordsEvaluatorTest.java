package org.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.evaluator.dto.SourceEvaluation;
import org.evaluator.dto.SourceEvaluations;
import org.junit.jupiter.api.Test;

class MatchedWordsEvaluatorTest {

  private static final String FRUIT_SOURCE = "fruitSource";
  private static final String ANIMAL_SOURCE = "animalSource";
  private static final String MIXED_SOURCE = "mixedSource";

  @Test
  void rankedEvaluations_shouldReturnInScoreDescOrder() {
    MatchedWordsEvaluator matchedWordsEvaluator = new MatchedWordsEvaluator(
        Map.of(FRUIT_SOURCE, someFruitData(),
            ANIMAL_SOURCE, someAnimalData(),
            MIXED_SOURCE, someMixedData()));
    SourceEvaluations actualEvaluations = matchedWordsEvaluator
        .getEvaluations(List.of("apple", "pear"));

    assertEquals(actualEvaluations.getRankedEvaluations().get(0),
        SourceEvaluation.of(FRUIT_SOURCE, 100));
    assertEquals(actualEvaluations.getRankedEvaluations().get(1),
        SourceEvaluation.of(MIXED_SOURCE, 50));
    assertEquals(actualEvaluations.getRankedEvaluations().get(2),
        SourceEvaluation.of(ANIMAL_SOURCE, 0));
  }

  List<String> someFruitData() {
    return List.of("apple", "orange", "pear", "grape", "pineapple");
  }

  List<String> someAnimalData() {
    return List.of("tiger", "elephant", "cat", "dog");
  }

  List<String> someMixedData() {
    return List.of("elephant", "grape", "cat", "pear");
  }
}