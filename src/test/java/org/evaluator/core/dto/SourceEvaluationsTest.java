package org.evaluator.core.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SourceEvaluationsTest {

  @Test
  void shouldReturnTenMostRankedEvaluations() {
    SourceEvaluations sourceEvaluations = new SourceEvaluations();
    sourceEvaluations.put(SourceEvaluation.of("l", 1));
    sourceEvaluations.put(SourceEvaluation.of("k", 5));
    sourceEvaluations.put(SourceEvaluation.of("j", 10));
    sourceEvaluations.put(SourceEvaluation.of("i", 20));
    sourceEvaluations.put(SourceEvaluation.of("h", 30));
    sourceEvaluations.put(SourceEvaluation.of("g", 40));
    sourceEvaluations.put(SourceEvaluation.of("f", 50));
    sourceEvaluations.put(SourceEvaluation.of("e", 60));
    sourceEvaluations.put(SourceEvaluation.of("d", 70));
    sourceEvaluations.put(SourceEvaluation.of("c", 80));
    sourceEvaluations.put(SourceEvaluation.of("b", 90));
    sourceEvaluations.put(SourceEvaluation.of("a", 100));

    assertEquals(
        List.of(
            SourceEvaluation.of("a", 100),
            SourceEvaluation.of("b", 90),
            SourceEvaluation.of("c", 80),
            SourceEvaluation.of("d", 70),
            SourceEvaluation.of("e", 60),
            SourceEvaluation.of("f", 50),
            SourceEvaluation.of("g", 40),
            SourceEvaluation.of("h", 30),
            SourceEvaluation.of("i", 20),
            SourceEvaluation.of("j", 10)),
        sourceEvaluations.getRankedEvaluations());
  }
}
