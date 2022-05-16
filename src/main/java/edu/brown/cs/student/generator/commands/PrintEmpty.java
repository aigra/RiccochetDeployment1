package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.generator.boards.EmptyBoard;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class PrintEmpty implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {
    return BoardGenerator.boardToString(new EmptyBoard().getBoard());
  }
}
