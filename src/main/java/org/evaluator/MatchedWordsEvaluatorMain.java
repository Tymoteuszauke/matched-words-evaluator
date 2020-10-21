package org.evaluator;

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
import org.evaluator.core.ArrayListMatchedWordsEvaluator;

public class MatchedWordsEvaluatorMain {

  public static void main(String[] args) throws IOException {
    String filePath = args[0];
    Set<String> fileList = listFilesUsingFileWalkAndVisitor(filePath);
    Map<String, List<String>> sourceWords = sourceNameWordsMap(fileList);

    ArrayListMatchedWordsEvaluator arrayListMatchedWordsEvaluator = new ArrayListMatchedWordsEvaluator(
        sourceWords);
    InputConsumer inputConsumer = new InputConsumer(new CommandInvoker(),
        arrayListMatchedWordsEvaluator);
    InputReceiver inputReceiver = new InputReceiver(inputConsumer);
    inputConsumer.addCommand(new ExitCommand(inputReceiver));
    inputReceiver.getInputs();
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
