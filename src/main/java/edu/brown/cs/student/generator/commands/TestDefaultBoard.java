package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Target;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class TestDefaultBoard implements REPLCommand {

  @Override
  public String commandExec(List<String> userInput) {
    BoardGenerator gen = new BoardGenerator();
    Board def = gen.createDefaultBoard(new Target[]{}, new Coordinate[] {
        new Coordinate(1, 1),
        new Coordinate(0, 1),
        new Coordinate(1, 0),
        new Coordinate(0, 0)
    });
    String res = BoardGenerator.boardToString(def.getBoard());
    res += "\nInitial locations:\n" + def.getRobotLocations().robotLocationsToString();
    RobotLocations redUp = def.algorithmMove(def.getRobotLocations(),
        new Move(Color.Red, Direction.NORTH));
    res += "\nLocations after moving Red up:\n" + redUp.robotLocationsToString();
    RobotLocations yellowLeft = def.algorithmMove(redUp, new Move(Color.Yellow, Direction.WEST));
    res += "\nLocations after moving Yellow left:\n" + yellowLeft.robotLocationsToString();
    return res;
  }
}
