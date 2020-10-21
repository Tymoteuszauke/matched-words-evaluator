package org.evaluator.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class TrieNode {

  private final Map<Character, TrieNode> children = new HashMap<>();
  private boolean endOfWord;

  Map<Character, TrieNode> getChildren() {
    return children;
  }

  boolean isEndOfWord() {
    return endOfWord;
  }

  void setEndOfWord(boolean endOfWord) {
    this.endOfWord = endOfWord;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TrieNode.class.getSimpleName() + "[", "]")
        .add("children=" + children)
        .add("endOfWord=" + endOfWord)
        .toString();
  }
}
