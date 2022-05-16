package generator;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.generator.boards.RicoRobotsBoard;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Target;

import java.util.HashMap;
import java.util.Map;

public class TestStorage {

  //QUADRANTS
  // 1x1 Quadrants
  public static Quadrant testQuad0 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{{"nw"}}),
      new Target[]{
          new Target(Color.Red, new Coordinate(0,0))
      }
  );

  public static Quadrant testQuad1 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{{"nw"}}),
      new Target[]{
          new Target(Color.Red, new Coordinate(0,0))
      }
  );

  public static Quadrant testQuad2 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{{"nw"}}),
      new Target[]{}
  );

  public static Quadrant testQuad2Rotated = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{{"se"}}),
      new Target[]{}
  );

  public static Quadrant testQuad3 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{{"nwe"}}),
      new Target[]{}
  );

  // 2x2 Quadrants
  public static Quadrant twoEmpty = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"",""},
          {"",""}
      }),
      new Target[]{}
  );

  public static Quadrant twoOuterWalls = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne"},
          {"sw","se"}
      }),
      new Target[]{}
  );

  public static Quadrant twoInnerWalls = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nwse","nwe"},
          {"snw","se"}
      }),
      new Target[]{}
  );

  public static Quadrant malformedInnerWall = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nwe","nwe"},
          {"snw","se"}
      }),
      new Target[]{}
  );

  public static Quadrant threeOuterWalls = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne"},
          {"w","","e"},
          {"sw","s","se"}
      }),
      new Target[]{}
  );

  public static Quadrant threeOuterWallsTarget = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne"},
          {"w","","e"},
          {"sw","s","se"}
      }),
      new Target[]{ new Target(Color.Red, new Coordinate(0, 1)) }
  );

  public static Quadrant threeOuterWallsRotatedTarget = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne"},
          {"w","","e"},
          {"sw","s","se"}
      }),
      new Target[]{ new Target(Color.Red, new Coordinate(1, 0)) }
  );

  public static Quadrant threeOuterWallsCenterTarget = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne"},
          {"w","","e"},
          {"sw","s","se"}
      }),
      new Target[]{ new Target(Color.Red, new Coordinate(1, 1)) }
  );

  public static Quadrant threeBadShape = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n", "ne"},
          {"w", "", "", "e"},
          {"sw","s","se"}
      }),
      new Target[]{}
  );

  // BOARDS
  // 1x1 well-formed
  public static RicoRobotsBoard singleWellFormed= new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{{"news"}}),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 16x16 empty well-formed
  public static RicoRobotsBoard properWellFormed = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","n","n","n","n","n","n","n","n","n","n","ne"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"sw","s","s","s","s","s","s","s","s","s","s","s","s","s","s","se"},

      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 16x16 empty missing outer wall
  public static RicoRobotsBoard properMalformed = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","n","n","n","n","n","n","n","n","n","n","ne"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","",""},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"sw","s","s","s","s","s","s","s","s","s","s","s","s","s","s","se"},

      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  public static RicoRobotsBoard missingInnerWall = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","n","n","n","n","n","n","n","n","n","n","ne"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","e","w","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","s","","","","","","","","","","","e"},
          {"w","","","","n","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","s","","","","","","","","","","","","e"},
          {"w","","","ne","w","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","es","sw","s","","","e"},
          {"w","","","","","","","","","","n","","n","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"sw","s","s","s","s","s","s","s","s","s","s","s","s","s","s","se"},

      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  public static RicoRobotsBoard inconsistentRowSize = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","n","n","n","n","n","n","n","n","n","n","ne"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","e","w","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","s","","","","","","","","","","","","e"}, // This row has length 17
          {"w","","","","n","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","s","","","","","","","","","","","","e"},
          {"w","","","ne","w","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","es","sw","s","","","e"},
          {"w","","","","","","","","","","n","n","n","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"w","","","","","","","","","","","","","","","e"},
          {"sw","s","s","s","s","s","s","s","s","s","s","s","s","s","s","se"},

      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 1x1 missing east wall
  public static RicoRobotsBoard singleMalformed= new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{{"nws"}}),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 2x2 well-formed outer walls
  public static RicoRobotsBoard doubleWellFormed= new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne"},
          {"sw","se"}
      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 2x2 malformed inner wall
  public static RicoRobotsBoard doubleMalformed= new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nwe","ne"},
          {"sw","se"}
      }),
      new RobotLocations(new HashMap<>()), new Target[]{});

  // 2x2 well-formed with a target
  public static RicoRobotsBoard doubleWithTarget = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne"},
          {"sw","se"}
      }),
      new RobotLocations(new HashMap<>()),
      new Target[]{new Target(Color.Red, new Coordinate(0, 1))});

  // 5x5 well-formed, red robot starts in middle, red target in bottom left
  public static RicoRobotsBoard fiveWellFormed = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne"},
          {"w","","","","e"},
          {"w","","","","e"},
          {"w","","","","e"},
          {"sw","s","s","s","se"}
      }),
      new RobotLocations(new HashMap<>(Map.of(
          Color.Red, new Coordinate(2, 2)))),
      new Target[]{
          new Target(Color.Red, new Coordinate(0, 4))
      });


  // 5x5 well-formed, red robot starts in middle, blue robot starts to the right of it, red target
  // in bottom left, blue target in bottom right
  public static RicoRobotsBoard fiveTwoColor = new RicoRobotsBoard(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne"},
          {"w","","","","e"},
          {"w","","","","e"},
          {"w","","","","e"},
          {"sw","s","s","s","se"}
      }),
      new RobotLocations(new HashMap<>(Map.of(
          Color.Red, new Coordinate(2, 2),
          Color.Blue, new Coordinate(3, 2)))),
      new Target[]{
          new Target(Color.Red, new Coordinate(0, 4)),
          new Target(Color.Blue, new Coordinate(4, 4))
      });

}
