package org.evaluator.loader.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.evaluator.trie.Trie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TrieWordsLoaderTest {

  @Test
  void shouldReturnSourceNamesTrieMap(@TempDir File tempDir) throws IOException {
    File fileOne = new File(tempDir, "sourceFirst.txt");
    FileWriter fileWriter = new FileWriter(fileOne);
    fileWriter.append("first line").append("second line");

    File fileSecond = new File(tempDir, "sourceSecond.txt");
    FileWriter fileWriter2 = new FileWriter(fileSecond);
    fileWriter2.append("first line").append("second line");

    Map<String, Trie> trieWords =
        new TrieWordsLoader()
            .load(List.of(fileOne.getAbsolutePath(), fileSecond.getAbsolutePath()));

    assertEquals(Set.of("sourceFirst.txt", "sourceSecond.txt"), trieWords.keySet());
  }
}
