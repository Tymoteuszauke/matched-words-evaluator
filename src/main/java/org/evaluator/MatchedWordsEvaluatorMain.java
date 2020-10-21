package org.evaluator;

import static org.evaluator.trie.Trie.trie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.evaluator.client.InputConsumer;
import org.evaluator.client.InputReceiver;
import org.evaluator.client.command.CommandInvoker;
import org.evaluator.client.command.commands.ExitCommand;
import org.evaluator.core.TrieWordsMatchScoreEvaluator;
import org.evaluator.core.WordsMatchScoreEvaluator;
import org.evaluator.trie.Trie;

public class MatchedWordsEvaluatorMain {

  public static void main(String[] args) throws IOException {
    Instant start = Instant.now();

    String filePath = args[0];
    Set<String> fileList = listFilesUsingFileWalkAndVisitor(filePath);
    WordsMatchScoreEvaluator wordsMatchScoreEvaluator =
        //        new ArrayListMatchedWordsEvaluator(sourceNameWordsMap(fileList));
        new TrieWordsMatchScoreEvaluator(sourceNameTries(fileList));
    InputConsumer inputConsumer = new InputConsumer(new CommandInvoker(), wordsMatchScoreEvaluator);
    InputReceiver inputReceiver = new InputReceiver(inputConsumer, System.in);
    inputConsumer.addCommand(new ExitCommand(inputReceiver));
    inputReceiver.receiveInputs();

    Instant finish = Instant.now();
    System.out.println(
        "Application finished after "
            + Duration.between(start, finish).toMillis()
            + " milliseconds");
    System.exit(0);
  }

  private static Map<String, Trie> sourceNameTries(Set<String> fileList) {
    Map<String, Trie> sourceWords = new HashMap<>();
    fileList.forEach(
        path -> {
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
    System.out.println(sourceWords);
    return sourceWords;
  }

  private static Map<String, List<String>> sourceNameWordsMap(Set<String> fileList) {
    Map<String, List<String>> sourceWords = new HashMap<>();

    fileList.forEach(
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

  public static Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
    Set<String> fileList = new HashSet<>();
    Files.walkFileTree(
        Paths.get(dir),
        new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!Files.isDirectory(file)) {
              fileList.add(dir + "/" + file.getFileName().toString());
            }
            return FileVisitResult.CONTINUE;
          }
        });
    return fileList;
  }
}
