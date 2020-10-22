package org.evaluator.core.evaluator;

import java.util.List;
import org.evaluator.core.dto.SourceEvaluations;

public interface WordsMatchEvaluator {

  SourceEvaluations getEvaluations(List<String> inputWords);

  default double calculatePercents(double total, double obtained) {
    return obtained * 100 / total;
  }
}
