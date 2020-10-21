package org.evaluator.core;

import java.util.List;
import org.evaluator.dto.SourceEvaluations;

public interface MatchedWordsEvaluator {

  SourceEvaluations getEvaluations(List<String> inputWords);

  default double calculatePercents(double total, double obtained) {
    if (total < 0) {
      throw new UnsupportedOperationException(
          "Cannot evaluate percentage for total value less than 0");
    }
    return obtained * 100 / total;
  }
}
