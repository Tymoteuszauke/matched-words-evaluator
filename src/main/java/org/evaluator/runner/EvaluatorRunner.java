package org.evaluator.runner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.evaluator.client.InputConsumer;
import org.evaluator.client.command.CommandInvoker;
import org.evaluator.client.command.commands.ExitCommand;
import org.evaluator.client.receiver.ConsoleInputReceiver;
import org.evaluator.client.receiver.InputReceiver;
import org.evaluator.client.receiver.ScannerInputReceiver;
import org.evaluator.core.evaluator.ArrayListWordsMatchEvaluator;
import org.evaluator.core.evaluator.TrieWordsMatchEvaluator;
import org.evaluator.core.evaluator.WordsMatchEvaluator;
import org.evaluator.loader.WordsLoader;
import org.evaluator.util.PropertiesLoader;

public class EvaluatorRunner {

  public void start(List<String> args) throws IOException {
    String filePath = args.get(0);

    Set<String> fileList = listFilesUsingFileWalkAndVisitor(filePath);

    WordsMatchEvaluator wordsMatchEvaluator = getWordsMatchEvaluator(fileList, args);
    InputConsumer inputConsumer =
        new InputConsumer(
            new CommandInvoker(),
            wordsMatchEvaluator,
            PropertiesLoader.INSTANCE.getProperty("input.delimiters"));
    InputReceiver inputReceiver = getInputReceiver(inputConsumer, args);
    inputConsumer.addCommand(new ExitCommand(inputReceiver));
    inputReceiver.receiveInputs();
  }

  private WordsMatchEvaluator getWordsMatchEvaluator(Set<String> fileList, List<String> argsList) {
    WordsMatchEvaluator wordsMatchEvaluator;
    if (argsList.contains("--arrayListEvaluator")) {
      wordsMatchEvaluator =
          new ArrayListWordsMatchEvaluator(WordsLoader.arrayListWordsLoader().load(fileList));
    } else {
      wordsMatchEvaluator =
          new TrieWordsMatchEvaluator(WordsLoader.trieWordsLoader().load(fileList));
    }

    return wordsMatchEvaluator;
  }

  private InputReceiver getInputReceiver(InputConsumer inputConsumer, List<String> args) {
    InputReceiver inputReceiver = new ConsoleInputReceiver(inputConsumer);
    if (args.contains("--ide")) {
      return new ScannerInputReceiver(inputConsumer, System.in);
    }
    return inputReceiver;
  }

  private Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
    Set<String> fileList = new HashSet<>();
    Files.walkFileTree(
        Paths.get(dir),
        new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!Files.isDirectory(file)) {
              String filePath = dir + "/" + file.getFileName().toString();
              System.out.println(filePath);
              fileList.add(filePath);
            }
            return FileVisitResult.CONTINUE;
          }
        });
    System.out.println("Found " + fileList.size() + " files under dir path " + dir);
    return fileList;
  }
}
