package edu.brown.cs.student.generator;

import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;

import java.util.HashMap;

/**
 * Class used to create
 */
public class BoardConverter {
  public static Square[][] convertBoard(Board board) {
    System.out.println("running convert board");
    Square[][] original = board.getBoard();
    Target[] targets = board.getTargets();
    HashMap<Color, ShapeGenerator> randomShapes = new HashMap<>();
    randomShapes.put(Color.Red, new ShapeGenerator());
    randomShapes.put(Color.Green, new ShapeGenerator());
    randomShapes.put(Color.Blue, new ShapeGenerator());
    randomShapes.put(Color.Yellow, new ShapeGenerator());
    for (Target t : targets) {
      Coordinate targetCoordinate = t.coordinate;
      Square sq = original[targetCoordinate.y][targetCoordinate.x];
      sq.target = true;
      sq.targetColor = t.color.toString();
      if (t.color != Color.Rainbow) {
        sq.targetShape = randomShapes.get(t.color).GetTargetShape().toString();
      }
      System.out.println("target found");
    }
    return original;
  }
}
