package org.evaluator.core.dto;

import java.text.NumberFormat;
import java.util.Objects;

public class SourceEvaluation implements Comparable<SourceEvaluation> {

  private final String sourceName;
  private final Double score;

  private SourceEvaluation(String sourceName, Double score) {
    this.sourceName = sourceName;
    this.score = score;
  }

  public static SourceEvaluation of(String sourceName, double score) {
    return new SourceEvaluation(sourceName, score);
  }

  public String getSourceName() {
    return sourceName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SourceEvaluation that = (SourceEvaluation) o;

    if (!Objects.equals(sourceName, that.sourceName)) {
      return false;
    }
    return Objects.equals(score, that.score);
  }

  @Override
  public int hashCode() {
    int result = sourceName != null ? sourceName.hashCode() : 0;
    result = 31 * result + (score != null ? score.hashCode() : 0);
    return result;
  }

  @Override
  public int compareTo(SourceEvaluation o) {
    return Double.compare(o.score, this.score);
  }

  @Override
  public String toString() {
    NumberFormat percentageNumberFormat = NumberFormat.getNumberInstance();
    return sourceName + " : " + percentageNumberFormat.format(score) + "%";
  }
}
