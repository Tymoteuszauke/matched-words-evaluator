package org.evaluator.core;

import static org.evaluator.core.InputDataTestFixtures.ANIMAL_SOURCE;
import static org.evaluator.core.InputDataTestFixtures.FRUIT_SOURCE;
import static org.evaluator.core.InputDataTestFixtures.MIXED_SOURCE;
import static org.evaluator.core.InputDataTestFixtures.someAnimalData;
import static org.evaluator.core.InputDataTestFixtures.someFruitData;
import static org.evaluator.core.InputDataTestFixtures.someMixedData;
import static org.evaluator.trie.Trie.trie;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.evaluator.dto.SourceEvaluation;
import org.evaluator.dto.SourceEvaluations;
import org.junit.jupiter.api.Test;

class TrieWordsMatchScoreEvaluatorTest {

  @Test
  void rankedEvaluations_shouldReturnInScoreDescOrder() {
    TrieWordsMatchScoreEvaluator arrayListMatchedWordsEvaluator =
        new TrieWordsMatchScoreEvaluator(
            Map.of(
                FRUIT_SOURCE,
                trie(someFruitData()),
                ANIMAL_SOURCE,
                trie(someAnimalData()),
                MIXED_SOURCE,
                trie(someMixedData())));

    SourceEvaluations actualEvaluations =
        arrayListMatchedWordsEvaluator.getEvaluations(List.of("apple", "pear"));

    assertEquals(
        actualEvaluations.getRankedEvaluations().get(0), SourceEvaluation.of(FRUIT_SOURCE, 100));
    assertEquals(
        actualEvaluations.getRankedEvaluations().get(1), SourceEvaluation.of(MIXED_SOURCE, 50));
    assertEquals(
        actualEvaluations.getRankedEvaluations().get(2), SourceEvaluation.of(ANIMAL_SOURCE, 0));
  }
}
