package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Target;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class GenerateRandom implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {
    BoardGenerator gen = new BoardGenerator();
    Board board = gen.createBoardFromQuadrants();
    System.out.println("TARGETS");
    for (Target t : board.getTargets()) System.out.println(t.targetToString());
    System.out.println(BoardGenerator.boardToString(board.getBoard()));
    System.out.println("ROBOTS");
    System.out.println(board.getRobotLocations().robotLocationsToString());

    return "";
  }
}
