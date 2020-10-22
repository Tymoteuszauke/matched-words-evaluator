package org.evaluator.core.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SourceEvaluations {

  private final Map<String, SourceEvaluation> sourceEvaluationsMap = new HashMap<>();
  private final List<SourceEvaluation> evaluations = new ArrayList<>();

  public void put(SourceEvaluation sourceEvaluation) {
    sourceEvaluationsMap.put(sourceEvaluation.getSourceName(), sourceEvaluation);
    evaluations.add(sourceEvaluation);
  }

  public List<SourceEvaluation> getRankedEvaluations() {
    return evaluations.stream().sorted().limit(10).collect(Collectors.toUnmodifiableList());
  }

  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner("\n");
    getRankedEvaluations()
        .forEach(sourceEvaluation -> stringJoiner.add(sourceEvaluation.toString()));
    return stringJoiner.toString();
  }
}
