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
import org.evaluator.core.ArrayListMatchedWordsEvaluator;

public class InputConsumer implements Consumer<String> {

  private final Map<String, Command> commandMap = new HashMap<>();
  private final CommandInvoker commandInvoker;
  private final ArrayListMatchedWordsEvaluator arrayListMatchedWordsEvaluator;

  public InputConsumer(CommandInvoker commandInvoker,
      ArrayListMatchedWordsEvaluator arrayListMatchedWordsEvaluator) {
    this.commandInvoker = commandInvoker;
    this.arrayListMatchedWordsEvaluator = arrayListMatchedWordsEvaluator;
  }

  @Override
  public void accept(String s) {
    Command command = new PrintUnknownCommandCommand();
    if (s.startsWith(":")) {
      command = commandMap.get(s);
    } else {
      command =
          new ExecuteSearchCommand(
              Stream.of(s.split("[ |,]")).collect(Collectors.toUnmodifiableList()),
              arrayListMatchedWordsEvaluator);
    }
    commandInvoker.invoke(command);
  }

  public void addCommand(Command command) {
    commandMap.put(command.getKey(), command);
  }
}
