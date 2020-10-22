package org.evaluator.client;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.evaluator.client.command.Command;
import org.evaluator.client.command.CommandInvoker;
import org.evaluator.client.command.commands.ExecuteSearchCommand;
import org.evaluator.client.command.commands.PrintUnknownCommandCommand;
import org.evaluator.core.WordsMatchEvaluator;

public class InputConsumer implements Consumer<String> {

  private final Map<String, Command> commandMap = new HashMap<>();

  private final CommandInvoker commandInvoker;
  private final WordsMatchEvaluator wordsMatchEvaluator;
  private final String inputDelimiters;

  public InputConsumer(
      CommandInvoker commandInvoker,
      WordsMatchEvaluator wordsMatchEvaluator,
      String inputDelimiters) {
    this.commandInvoker = commandInvoker;
    this.wordsMatchEvaluator = wordsMatchEvaluator;
    this.inputDelimiters = inputDelimiters;
  }

  @Override
  public void accept(String s) {
    Command command;
    if (s.startsWith(":")) {
      command =
          commandMap.getOrDefault(
              s,
              new PrintUnknownCommandCommand(
                  commandMap.keySet().stream().collect(Collectors.toUnmodifiableList())));
    } else {
      command =
          new ExecuteSearchCommand(
              Stream.of(s.split(String.format("[%s]", inputDelimiters)))
                  .collect(Collectors.toUnmodifiableList()),
              wordsMatchEvaluator);
    }
    commandInvoker.invoke(command);
  }

  public void addCommand(Command command) {
    commandMap.put(command.getKey(), command);
  }
}
