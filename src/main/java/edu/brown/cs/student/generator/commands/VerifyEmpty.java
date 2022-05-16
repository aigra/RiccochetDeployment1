package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.generator.boards.EmptyBoard;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class VerifyEmpty implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {
    if (BoardGenerator.verifyBoard(new EmptyBoard(), 16)) {
      return "Empty board is valid!\n";
    } else {
      return "Empty board is not valid :(\n";
    }
  }
}
