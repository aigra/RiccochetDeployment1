package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.boards.BoardStorage;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class ValidateQuadrants implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {
    for (int i = 0; i < BoardStorage.quadrants.length; i++) {
      if (BoardStorage.quadrants[i].validateQuadrant(8)) {
        System.out.println("Quadrant " + i + " validated");
      } else {
        System.out.println(Quadrant.quadToString(BoardStorage.quadrants[i].getQuad()));
      }
    }
    return "";
  }
}
