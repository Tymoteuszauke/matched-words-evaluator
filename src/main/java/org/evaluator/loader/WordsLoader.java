package org.evaluator.loader;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.evaluator.loader.internal.ArrayListWordsLoader;
import org.evaluator.loader.internal.TrieWordsLoader;
import org.evaluator.trie.Trie;

public interface WordsLoader<D> {

  static WordsLoader<Trie> trieWordsLoader() {
    return new TrieWordsLoader();
  }

  static WordsLoader<List<String>> arrayListWordsLoader() {
    return new ArrayListWordsLoader();
  }

  Map<String, D> load(Collection<String> filePaths);
}
