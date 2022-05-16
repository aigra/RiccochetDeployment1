package edu.brown.cs.student.generator.boards;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;

public class BoardStorage {

  public static final Square[][] defaultBoardAsSquares = BoardGenerator.stringsToSquares(
      new String[][]{
        {"nw","n","n","ne","nw","n","n","n","n","n","ne","nw","ns","n","n","ne"},
        {"w","","","","","e","sw","","","","","e","nw","","","e"},
        {"w","s","","","","","n","","","","","","","e","sw","e"},
        {"w","ne","w","","","s","","e","sw","","","","","","n","e"},
        {"w","","","","e","nw","","","n","","","","","","","se"},
        {"w","", "se","w","","","","se","w","se","w","s","","","","ne"},
        {"sw","","n","","","","","ns","s","n","","ne","w","","","e"},
        {"nw","","","","","","e","nw","ne","w","","","","","","e"},
        {"w","","","","","","e","sw","se","w","","","","","","e"},
        {"w","s","","e","sw","","","n","n","","e","sw","","","","se"},
        {"w","ne","w","","n","","","","","","","n","","","s","ne"},
        {"sw","","","","s","","","","","s","","","","","ne","we"},
        {"nw","","","","ne","w","s","","e","nw","","","","","","e"},
        {"w","","","","","e","nw","","","","","","","","","e"},
        {"w","","se","w","","","","","","","","","se","w","","e"},
        {"sw","s","ns","se","sw","s","s","s","s","s","s","s","ns","se","sw","se"}
    }
  );

  private static final Quadrant q1 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne","nw","n","ns","n","n","n"},
          {"w","","","","ne","w","",""},
          {"w","","","","","","",""},
          {"we","sw","","","","","",""},
          {"w","n","","","","s","",""},
          {"sw","","","","e","nw","",""},
          {"nw","","","se","w","","","s"},
          {"w","","","n","","","e","nw"}
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 3)),
          new Target(Color.Green, new Coordinate(4, 1)),
          new Target(Color.Blue, new Coordinate(3, 6)),
          new Target(Color.Yellow, new Coordinate(5, 5))
      }
  );

  private static final Quadrant q2 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne","nw","n","n"},
          {"w","","","","","","",""},
          {"sw","","","","","","se","w"},
          {"nw","","s","","","","n",""},
          {"w","","ne","sw","","","",""},
          {"w","","","n","","s","",""},
          {"w","","","","e","nw","","s"},
          {"w","","","","","","e","nw"}
      }),
      new Target[] {
          new Target(Color.Green, new Coordinate(2, 4)),
          new Target(Color.Red, new Coordinate(3,4)),
          new Target(Color.Blue, new Coordinate(6, 2)),
          new Target(Color.Yellow, new Coordinate(5, 6))
      }
  );

  private static final Quadrant q3 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","ne","nw","n","n","n"},
          {"w","","","","","e","sw",""},
          {"w","s","","","","","n",""},
          {"w","ne","w","","","s","",""},
          {"w","","","","e","nw","",""},
          {"w","","se","w","","","","se*"},
          {"sw","","n","","","","","ns"},
          {"nw","","","","","","e","nw"}
      }),
      new Target[]{
          new Target(Color.Yellow, new Coordinate(1, 3)),
          new Target(Color.Red, new Coordinate(2, 5)),
          new Target(Color.Blue, new Coordinate(6, 1)),
          new Target(Color.Green, new Coordinate(5, 4)),
//          new Target(Color.Rainbow, new Coordinate(7, 5))
      }
  );

  private static final Quadrant q4 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne","nw","n","n"},
          {"w","","se","w","","","",""},
          {"w","","n","","","","",""},
          {"we","sw","","","","","s",""},
          {"sw","n","","","","e","nw",""},
          {"nw","","","","","s","",""},
          {"w","","","","","ne","w","s"},
          {"w","","","s*e","w","","e","nw"}
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(2,1)),
          new Target(Color.Green, new Coordinate(1,3)),
          new Target(Color.Yellow, new Coordinate(6,4)),
          new Target(Color.Blue, new Coordinate(5,6))
      }
  );

  private static final Quadrant q5 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne","nw","n","n"},
          {"w","","","","","","s",""},
          {"w","","s","","","e","nw",""},
          {"w","","ne","sw","","","",""},
          {"w","","","n","","","",""},
          {"w","se","w","","","","",""},
          {"sw","n","","","","","","s"},
          {"nw","","","","","s*e","ew","nw"}
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1,5)),
          new Target(Color.Blue, new Coordinate(2,3)),
          new Target(Color.Green, new Coordinate(3,3)),
          new Target(Color.Yellow, new Coordinate(6,2)),
//          new Target(Color.Rainbow, new Coordinate(5, 7))
      }
  );

  private static final Quadrant q6 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne","nw","n","n","n","n"},
          {"w","","","","e","sw","",""},
          {"w","","","","","n","","se*"},
          {"sw","","","","","","","n"},
          {"nw","","","se","w","","s",""},
          {"w","s","","n","","e","nw",""},
          {"w","ne","w","","","","","s"},
          {"w","","","","","","e","nw"}
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(3,4)),
          new Target(Color.Blue, new Coordinate(5,1)),
          new Target(Color.Green, new Coordinate(6,5)),
          new Target(Color.Yellow, new Coordinate(1,6)),
//          new Target(Color.Rainbow, new Coordinate(7, 2))
      }
  );

  private static final Quadrant q7 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","ne","nw","n","n","n"},
          {"w","","","","","","",""},
          {"w","","","","","se","w",""},
          {"w","","s","","","n","",""},
          {"ws","","ne","w","","","",""},
          {"wn","s","","","","","e","sw"},
          {"we","wn","","","","","","ns"},
          {"w","","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(7, 5)),
          new Target(Color.Green, new Coordinate(2, 4)),
          new Target(Color.Blue, new Coordinate(5, 2)),
          new Target(Color.Yellow, new Coordinate(1, 6))
      }
  );

  private static final Quadrant q8 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","ne","nw","n","n","n"},
          {"we","sw","","","","","s",""},
          {"w","n","","","","","ne","w"},
          {"w","","","","","","",""},
          {"w","","se","w","","","","s"},
          {"ws","","n","","","","e","nw"},
          {"wn","","","","","","","s"},
          {"w","","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 1)),
          new Target(Color.Green, new Coordinate(6, 2)),
          new Target(Color.Blue, new Coordinate(2, 4)),
          new Target(Color.Yellow, new Coordinate(7, 5))
      }
  );

  private static final Quadrant q9 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ne","nw","n","n","n","n"},
          {"w","","","","","","s",""},
          {"w","","","","","se","nw",""},
          {"w","","","","","n","",""},
          {"w","","","","","","",""},
          {"we","sw","","","","","",""},
          {"ws","n","","","s","","","s"},
          {"wn","","","","ne","w","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 5)),
          new Target(Color.Green, new Coordinate(5, 2)),
          new Target(Color.Blue, new Coordinate(4, 7)),
          new Target(Color.Yellow, new Coordinate(6, 2))
      }
  );

  private static final Quadrant q10 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","ne","nw","n"},
          {"w","","","s","","","",""},
          {"w","","e","nw","","","",""},
          {"ws","","s","","e","sw","",""},
          {"wn","","ne","w","","n","",""},
          {"w","","","","se","w","",""},
          {"w","","","","n","","","s"},
          {"w","","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(2, 4)),
          new Target(Color.Green, new Coordinate(4, 5)),
          new Target(Color.Blue, new Coordinate(5, 3)),
          new Target(Color.Yellow, new Coordinate(3, 2))
      }
  );

  private static final Quadrant q11 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne","nw","n","ns","n","n","n"},
          {"w","s","","e","nw","","",""},
          {"w","ne","w","","","","",""},
          {"w","","","","","","se","w"},
          {"w","","","","","","n",""},
          {"ws","","","","","","",""},
          {"wn","","e","ws","","","","s"},
          {"w","","","n","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(4, 1)),
          new Target(Color.Green, new Coordinate(1, 2)),
          new Target(Color.Blue, new Coordinate(3, 6)),
          new Target(Color.Yellow, new Coordinate(6, 3))
      }
  );

  private static final Quadrant q12 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","ne","wn","n","n"},
          {"w","s","","","","","se","w"},
          {"we","nw","","","","","n",""},
          {"w","","","","","","",""},
          {"w","","","","","","s",""},
          {"ws","","","","","","ne","w"},
          {"wn","","e","ws","","","","s"},
          {"w","","","n","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(3, 6)),
          new Target(Color.Green, new Coordinate(1, 2)),
          new Target(Color.Blue, new Coordinate(6, 5)),
          new Target(Color.Yellow, new Coordinate(6, 1))
      }
  );

  private static final Quadrant q13 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","ns","n","ne","nw","n","n"},
          {"w","e","nw","","","","",""},
          {"w","","","","","","",""},
          {"w","","","","","e","sw",""},
          {"ws","","","","s","","n",""},
          {"wn","","","","ne","w","",""},
          {"w","se","w","","","","","s"},
          {"w","n","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(4, 5)),
          new Target(Color.Green, new Coordinate(1, 6)),
          new Target(Color.Blue, new Coordinate(6, 3)),
          new Target(Color.Yellow, new Coordinate(2, 1))
      }
  );

  private static final Quadrant q14 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","ne","nw","n","n","n"},
          {"w","","","","","se","w",""},
          {"we","sw","","","","n","",""},
          {"ws","n","","","","","s",""},
          {"wn","","","","","e","wn",""},
          {"w","","s","","","","",""},
          {"w","","ne","w","","","","s"},
          {"w","","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 2)),
          new Target(Color.Green, new Coordinate(5, 1)),
          new Target(Color.Blue, new Coordinate(2, 6)),
          new Target(Color.Yellow, new Coordinate(6, 4))
      }
  );

  private static final Quadrant q15 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","n","n","n","n","ne","nw","n"},
          {"w","","","","","","",""},
          {"w","s","","","","","",""},
          {"we","nw","","","","","",""},
          {"w","","","","","","se","w"},
          {"ws","","s","","","","n",""},
          {"wn","","ne","ws","","","","s"},
          {"w","","","n","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 3)),
          new Target(Color.Green, new Coordinate(2, 6)),
          new Target(Color.Blue, new Coordinate(3, 6)),
          new Target(Color.Yellow, new Coordinate(6, 4))
      }
  );

  private static final Quadrant q16 = new Quadrant(
      BoardGenerator.stringsToSquares(new String[][]{
          {"nw","ne","nw","ns","n","n","n","n"},
          {"w","","e","nw","","","",""},
          {"w","","","","","","",""},
          {"w","","","","","","se","w"},
          {"we","sw","","","","","n",""},
          {"w","n","","","s","","",""},
          {"ws","","","","ne","w","","s"},
          {"wn","","","","","","e","nw"},
      }),
      new Target[]{
          new Target(Color.Red, new Coordinate(1, 4)),
          new Target(Color.Green, new Coordinate(3, 1)),
          new Target(Color.Blue, new Coordinate(4, 6)),
          new Target(Color.Yellow, new Coordinate(6, 3))
      }
  );

  public static final Quadrant[] quadrants =
      new Quadrant[]{q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16};

}