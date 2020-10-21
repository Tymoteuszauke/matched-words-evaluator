package org.evaluator.trie;

import java.util.List;
import java.util.StringJoiner;

public class Trie {

  private final TrieNode root;

  Trie() {
    root = new TrieNode();
  }

  public static Trie trie() {
    return new Trie();
  }

  public static Trie trie(List<String> words) {
    Trie trie = new Trie();
    words.forEach(trie::insert);
    return trie;
  }

  public boolean find(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      TrieNode node = current.getChildren().get(ch);
      if (node == null) {
        return false;
      }
      current = node;
    }
    return current.isEndOfWord();
  }

  public void insert(String word) {
    TrieNode current = root;

    for (char l : word.toCharArray()) {
      current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
    }
    current.setEndOfWord(true);
  }

  public boolean delete(String word) {
    return delete(root, word, 0);
  }

  public boolean containsNode(String word) {
    TrieNode current = root;

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      TrieNode node = current.getChildren().get(ch);
      if (node == null) {
        return false;
      }
      current = node;
    }
    return current.isEndOfWord();
  }

  public boolean isEmpty() {
    return root == null;
  }

  private boolean delete(TrieNode current, String word, int index) {
    if (index == word.length()) {
      if (!current.isEndOfWord()) {
        return false;
      }
      current.setEndOfWord(false);
      return current.getChildren().isEmpty();
    }
    char ch = word.charAt(index);
    TrieNode node = current.getChildren().get(ch);
    if (node == null) {
      return false;
    }
    boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

    if (shouldDeleteCurrentNode) {
      current.getChildren().remove(ch);
      return current.getChildren().isEmpty();
    }
    return false;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Trie.class.getSimpleName() + "[", "]")
        .add("root=" + root)
        .toString();
  }
}
