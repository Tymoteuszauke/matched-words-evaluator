package org.evaluator.core;

import java.util.List;
import org.evaluator.dto.SourceEvaluations;

public interface WordsMatchEvaluator {

  SourceEvaluations getEvaluations(List<String> inputWords);

  default double calculatePercents(double total, double obtained) {
    return obtained * 100 / total;
  }
}
