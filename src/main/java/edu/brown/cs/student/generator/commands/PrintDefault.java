package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Target;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class PrintDefault implements REPLCommand {

  BoardGenerator gen = new BoardGenerator();
  Board board = gen.createDefaultBoard(new Target[]{}, new Coordinate[] {
      new Coordinate(1, 1),
      new Coordinate(0, 1),
      new Coordinate(1, 0),
      new Coordinate(0, 0)
  });
  @Override
  public String commandExec(List<String> userInput) {
    return BoardGenerator.boardToString(board.getBoard());
  }
}
