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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchedWordsEvaluatorMain {

  public static void main(String[] args) throws IOException {
    String filePath = args[0];
    List<String> inputWords = Arrays.asList(args[1].split(","));

    Set<String> fileList = listFilesUsingFileWalkAndVisitor(filePath);

    Map<String, List<String>> sourceWords = new HashMap<>();

    fileList.forEach(
        path -> {
          try {
            BufferedReader bufferedReader =
                new BufferedReader(new FileReader(filePath + "/" + path));
            List<String> lines = bufferedReader
                .lines()
                .collect(Collectors.toList());

            List<String> fileWords = lines.stream().flatMap(s -> Stream.of(s.split(" ")))
                .collect(Collectors.toList());

            sourceWords.put(
                path,
                fileWords
            );
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });

    Map<String, Double> matchingScore = new HashMap<>();

    sourceWords.forEach(
        (key, value) -> {
          double matchedWordsCount =
              inputWords.stream().filter(value::contains).count();

          matchingScore.put(key, calculatePercents(inputWords.size(), matchedWordsCount));
        });

    System.out.println(matchingScore);
  }

  public static double calculatePercents(double total, double obtained) {
    if (total < 0) {
      throw new UnsupportedOperationException(
          "Cannot evaluate percentage for total value less than 0");
    }
    return obtained * 100 / total;
  }

  public static Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
    Set<String> fileList = new HashSet<>();
    Files.walkFileTree(
        Paths.get(dir),
        new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!Files.isDirectory(file)) {
              fileList.add(file.getFileName().toString());
            }
            return FileVisitResult.CONTINUE;
          }
        });
    return fileList;
  }
}
