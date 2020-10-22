package org.evaluator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.evaluator.exceptions.FileDirectoryPathNotFoundException;
import org.evaluator.runner.EvaluatorRunner;

public class MatchedWordsEvaluatorMain {

  public static void main(String[] args) throws IOException {
    Instant start = Instant.now();

    List<String> argsList = Stream.of(args).collect(Collectors.toUnmodifiableList());
    validateArgs(argsList);

    EvaluatorRunner evaluatorRunner = new EvaluatorRunner();
    evaluatorRunner.start(argsList);

    Instant finish = Instant.now();
    System.out.println(
        "Application finished after "
            + Duration.between(start, finish).toMillis()
            + " milliseconds");
    System.exit(0);
  }

  public static void validateArgs(List<String> argsList) {
    if (argsList.isEmpty()) {
      throw new FileDirectoryPathNotFoundException("Please provide directory path!");
    }
  }
}
