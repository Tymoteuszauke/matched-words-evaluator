package org.evaluator;

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
import org.evaluator.client.receiver.InputReceiver;
import org.evaluator.client.receiver.ScannerInputReceiver;
import org.evaluator.core.TrieWordsMatchEvaluator;
import org.evaluator.core.WordsMatchEvaluator;
import org.evaluator.loader.WordsLoader;

public class EvaluatorRunner {


  public void start(List<String> args) throws IOException {
    String filePath = args.get(0);
    Set<String> fileList = listFilesUsingFileWalkAndVisitor(filePath);
    WordsMatchEvaluator wordsMatchEvaluator =
        //        new ArrayListMatchedWordsEvaluator(sourceNameWordsMap(fileList));
        new TrieWordsMatchEvaluator(WordsLoader.trieWordsLoader().load(fileList));
    InputConsumer inputConsumer = new InputConsumer(new CommandInvoker(), wordsMatchEvaluator);
    InputReceiver scannerInputReceiver = new ScannerInputReceiver(inputConsumer, System.in);
    inputConsumer.addCommand(new ExitCommand(scannerInputReceiver));
    scannerInputReceiver.receiveInputs();
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
    return fileList;
  }
}
