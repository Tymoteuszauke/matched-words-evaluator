package org.evaluator;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class MatchedWordsEvaluatorMainTest {

  @Test
  void name() throws IOException {
    MatchedWordsEvaluatorMain.main(new String[]{"src/test/resources", "to,boss,hard,money,be"});

  }
}