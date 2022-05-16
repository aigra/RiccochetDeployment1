package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Target;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class VerifyDefault implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {
    BoardGenerator gen = new BoardGenerator();
    if (BoardGenerator.verifyBoard(gen.createDefaultBoard(new Target[]{}, new Coordinate[] {
            new Coordinate(1, 1),
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, 0)
        }), 16)) {
      return "Default board is valid!\n";
    } else {
      return "Default board is not valid :(\n";
    }
  }
}
