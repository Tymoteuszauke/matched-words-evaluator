package org.evaluator.client.command;

public interface Command {

  String getKey();

  void execute();
}
