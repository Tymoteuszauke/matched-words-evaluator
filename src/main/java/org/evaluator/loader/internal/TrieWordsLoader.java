package org.evaluator.loader.internal;

import static org.evaluator.trie.Trie.trie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.evaluator.loader.WordsLoader;
import org.evaluator.trie.Trie;

public class TrieWordsLoader implements WordsLoader<Trie> {

  @Override
  public Map<String, Trie> load(Collection<String> filePaths) {
    Map<String, Trie> sourceWords = new HashMap<>();
    filePaths.forEach(
        path -> {
          System.out.println("Loading words: " + path);
          try {
            Trie trie = trie();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader
                .lines()
                .flatMap(s -> Stream.of(s.split("[ |,]")))
                .filter(s -> !s.isBlank())
                .forEach(trie::insert);

            sourceWords.put(path, trie);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    System.out.println();
    return sourceWords;
  }
}
