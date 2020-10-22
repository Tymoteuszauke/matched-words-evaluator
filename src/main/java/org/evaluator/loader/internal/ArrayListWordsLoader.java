package org.evaluator.loader.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.evaluator.loader.WordsLoader;

public class ArrayListWordsLoader implements WordsLoader<List<String>> {

  @Override
  public Map<String, List<String>> load(Collection<String> filePaths) {
    Map<String, List<String>> sourceWords = new HashMap<>();
    filePaths.forEach(
        path -> {
          try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            List<String> fileWords =
                bufferedReader
                    .lines()
                    .flatMap(s -> Stream.of(s.split("[ |,]")))
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.toList());

            sourceWords.put(path, fileWords);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    return sourceWords;
  }
}
