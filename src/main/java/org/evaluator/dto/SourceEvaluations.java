package org.evaluator.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SourceEvaluations {

  private final Map<String, SourceEvaluation> sourceEvaluationsMap = new HashMap<>();
  private final List<SourceEvaluation> rankedEvaluations = new ArrayList<>();

  public void put(SourceEvaluation sourceEvaluation) {
    sourceEvaluationsMap.put(sourceEvaluation.getSourceName(), sourceEvaluation);
    rankedEvaluations.add(sourceEvaluation);
  }

  public void put(String sourceName, double score) {
    SourceEvaluation sourceEvaluation = SourceEvaluation.of(sourceName, score);
    sourceEvaluationsMap.put(sourceName, sourceEvaluation);
    rankedEvaluations.add(sourceEvaluation);
  }

  public SourceEvaluation get(String sourceName) {
    return sourceEvaluationsMap.get(sourceName);
  }

  public List<SourceEvaluation> getRankedEvaluations() {
    return rankedEvaluations.stream().sorted().collect(Collectors.toUnmodifiableList());
  }

  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner("\n");
    rankedEvaluations.forEach(sourceEvaluation -> stringJoiner.add(sourceEvaluation.toString()));
    return stringJoiner.toString();
  }
}
