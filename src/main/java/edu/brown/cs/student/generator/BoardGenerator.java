package edu.brown.cs.student.generator;

import edu.brown.cs.student.generator.boards.BoardStorage;
import edu.brown.cs.student.generator.boards.RicoRobotsBoard;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Generator;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardGenerator implements Generator {

  @Override
  public Board createBoard(int seed) {
    return null;
  }

  /**
   * Creates a RicoRobotsBoard object by randomly selecting 4 of the stored Quadrants.  Starting
   * locations are also randomized.
   * @return A validated RicoRobotsBoard object
   */
  public Board createBoardFromQuadrants() {

    // Select 4 random quadrants from those hardcoded into BoardStorage
    Quadrant[] quads = new Quadrant[4];
    List<Integer> usedQuadrants = new ArrayList<>();
    for (int i = 0; i < quads.length; i++) {
      int nextQuadrant = (int) Math.floor(Math.random() * BoardStorage.quadrants.length);
      // Continue randomizing until an unused quadrant index is selected
      while (usedQuadrants.contains(nextQuadrant)) {
        nextQuadrant = (int) Math.floor(Math.random() * BoardStorage.quadrants.length);
      }
      quads[i] = BoardStorage.quadrants[nextQuadrant];
      usedQuadrants.add(nextQuadrant);
    }
    // Combine them together
    Quadrant qBoard = stitchQuadrants(quads[0], quads[1], quads[2], quads[3], 8);

    // Generate randomized starting locations
    Color[] robots = new Color[]{Color.Red, Color.Blue, Color.Green, Color.Yellow};
    Map<Color, Coordinate> startingLocations = new HashMap<>();
    for (Color robot : robots) {
      // Randomize an x and y coordinate in the range [0,15]
      int x = (int) Math.floor(Math.random() * 16);
      int y = (int) Math.floor(Math.random() * 16);
      // Continue randomizing as needed until that square is not occupied by another robot or a
      // target, and is not in the middle square
      List<Coordinate> targetCoordinates = new ArrayList<>();
      for (Target t : qBoard.getTargets()) {
        targetCoordinates.add(t.coordinate);
      }
      // The generated coordinate is invalid if it is already a starting location of a robot
      boolean invalidLoc = (startingLocations.containsValue(new Coordinate(x, y)) ||
          // It is in the middle square of the board where it cannot leave
              (x > 6 && x < 9 && y > 6 && y < 9) ||
          // Or it is on top of a target
          targetCoordinates.contains(new Coordinate(x, y)));
      while(invalidLoc) {
        x = (int) Math.floor(Math.random() * 16);
        y = (int) Math.floor(Math.random() * 16);
        invalidLoc = (startingLocations.containsValue(new Coordinate(x, y)) ||
            // It is in the middle square of the board where it cannot leave
            (x > 6 && x < 9 && y > 6 && y < 9) ||
            // Or it is on top of a target
            targetCoordinates.contains(new Coordinate(x, y)));
      }
      // When an unoccupied starting square is found, add it to the Map and continue to the next bot
      startingLocations.put(robot, new Coordinate(x, y));
    }

    // Create new RicoRobotsBoard object with these
    RicoRobotsBoard res = new RicoRobotsBoard(qBoard.getQuad(), new RobotLocations(startingLocations),
        qBoard.getTargets());
    assert verifyBoard(res, 16);
    return res;
  }

  /**
   * Creates a board from the given quadrants.  Exists for testing purposes.
   * @param q1 first quadrant
   * @param q2 second quadrant
   * @param q3 third quadrant
   * @param q4 fourth quadrant
   * @return Board created by stitching the four given quadrants.  No robot locations generated.
   */
  public Board stitchSpecificQuadrants(Quadrant q1, Quadrant q2, Quadrant q3, Quadrant q4) {
    Quadrant q = stitchQuadrants(q1, q2, q3, q4, q1.getQuad().length);
    return new RicoRobotsBoard(q.getQuad(), new RobotLocations(new HashMap<>()), q.getTargets());
  }

  /**
   * Combines the 4 given Quadrants to form a full board's worth of Square[][] and Target[].
   * Quadrant class is used for convenience of storing the data.  Quadrants are stitched in the
   * form   1  2
   *        3  4
   * @param q1 first Quadrant
   * @param q2 second Quadrant
   * @param q3 third Quadrant
   * @param q4 fourth Quadrant
   * @param size size of the quadrants (assumed to be squares of the same size)
   * @return A Quadrant where  the squares and targets of the given quadrants have been combined to
   * a full board.
   */
  private Quadrant stitchQuadrants(Quadrant q1, Quadrant q2, Quadrant q3, Quadrant q4, int size) {
    // Instantiate new array to store result, getting the sizes by combining dimensions of the
    // first two quadrants.  Assumes all Quadrants are of same square dimensions
    int totalNumRows = q1.getQuad().length + q3.getQuad().length;
    int totalNumCols = q1.getQuad()[0].length + q2.getQuad()[0].length;
    Square[][] newSquares = new Square[totalNumRows][totalNumCols];
    // Get rotated versions of the given Quadrants as appropriate
    Quadrant r2 = Quadrant.rotate(q2,1);
    Quadrant r3 = Quadrant.rotate(q3,3);
    Quadrant r4 = Quadrant.rotate(q4,2);

    // Print statements for troubleshooting
//    Quadrant[] quads = new Quadrant[]{q1, r2, r3, r4};
//    for (Quadrant q : quads) {
//      for (Target t : q.getTargets()) {
//        System.out.println(t.targetToString());
//      }
//      System.out.println();
//    }

    // Combine Squares[][] of Quadrants together into newSquares
    int q1NumRows = q1.getQuad().length;
    int q1NumCols = q1.getQuad()[0].length;
    int q3NumCols = r3.getQuad()[0].length;
    for (int row = 0; row < totalNumRows; row++) {
      if (row < q1NumRows) {
        // If you are in top half
        for (int col = 0; col < totalNumCols; col++) {
          if (col < q1NumCols) {
            // If you are in q1
            newSquares[row][col] = q1.getQuad()[row][col];
          } else {
            // If you are in q2
            int q2Col = col - q1NumCols;
            newSquares[row][col] = r2.getQuad()[row][q2Col];
            newSquares[row][col].xCoord += size;
          }
        }
      } else {
        // If you are in bottom half
        int q3Row = row - q1NumRows;
        for (int col = 0; col < totalNumCols; col++) {
          if (col < q3NumCols) {
            // If you are in q3
            newSquares[row][col] = r3.getQuad()[q3Row][col];
          } else {
            // If you are in q4
            int q4Col = col - q3NumCols;
            newSquares[row][col] = r4.getQuad()[q3Row][q4Col];
            newSquares[row][col].xCoord += size;
          }
          newSquares[row][col].yCoord += size;
        }
      }
    }

    // These for loops serve to smooth the handful of walls that are on the edges of quadrants
    // where they connect to other quadrants by adding the corresponding wall to the adjacent
    // square.

    // Smooth walls vertically
    for (int row = 0; row < 2 * size; row++) {
      Square left = newSquares[row][size - 1];
      Square right = newSquares[row][size];
      if (left.eastWall || right.westWall) {
        left.eastWall = true;
        right.westWall = true;
      }
    }
    // Smooth walls horizontally
    for (int col = 0; col < 2 * size; col++) {
      Square top = newSquares[size - 1][col];
      Square bot = newSquares[size][col];
      if (top.southWall || bot.northWall) {
        top.southWall = true;
        bot.northWall = true;
      }
    }

    // Combine Targets
    Target[] newTargets = new Target[q1.getTargets().length + r2.getTargets().length
         + r3.getTargets().length + r4.getTargets().length];
    int i = 0;
    // Put each Target from each Quadrant into the newTargets array, offsetting the coordinates as
    // needed
    for (Target t : q1.getTargets()) {
      newTargets[i] = t;
      i++;
    }
    for (Target t : r2.getTargets()) {
      t.coordinate.x += q1NumCols;
      newTargets[i] = t;
      i++;
    }
    for (Target t : r3.getTargets()) {
      t.coordinate.y += q1NumRows;
      newTargets[i] = t;
      i++;
    }
    for (Target t : r4.getTargets()) {
      t.coordinate.x += q3NumCols;
      t.coordinate.y += q1NumRows;
      newTargets[i] = t;
      i++;
    }
    return new Quadrant(newSquares, newTargets);
  }

  /**
   * Generates the default Ricochet Robots board. Useful for testing.
   * @return A Board object with randomized robot starting locations and the default walls.
   */
  public Board createDefaultBoard(Target[] targets, Coordinate[] robotCoordinates) {
    // Interpret default board stored as strings
    Square[][] defaultBoardAsSquares = BoardStorage.defaultBoardAsSquares;
    // Generate randomized starting locations
    Map<Color, Coordinate> startingLocations = new HashMap<>();

    startingLocations.put(Color.Red, robotCoordinates[0]);
    startingLocations.put(Color.Blue, robotCoordinates[1]);
    startingLocations.put(Color.Yellow, robotCoordinates[2]);
    startingLocations.put(Color.Green, robotCoordinates[3]);
    // Instantiate and return Board object
    return new RicoRobotsBoard(defaultBoardAsSquares, new RobotLocations(startingLocations),
        targets);

  }

  /**
   * Verifies that the given board is well-formed, meaning that the walls of every square match
   * those of its adjacent squares and walls are present along the perimeter of the board
   * @param b board to be verified
   * @param size the size of the board to be verified (assumed to be square)
   * @return true if the board is well-formed, false if not
   */
  public static boolean verifyBoard(Board b, int size) {

    // Extract the actual board array from the Board object
    Square[][] board = b.getBoard();

    // Check that board has proper shape
    if (board.length != size) return false;
    for (Square[] row : board) {
      if (row.length != size) {
        return false;
      }
    }
    System.out.println("Size validated!");
    // Check that the walls of each square either match its neighbors or are on the perimeter
    // If at any point this is not the case, return false
    for (Square[] row : board) {
      for (Square square : row) {
        if (!checkSquare(board, square, size)) {
          System.out.printf("Failed at row %d, col %d%n", square.yCoord, square.xCoord);
          return false;
        }
      }
    }

    // To get here, the board must be the proper dimensions and all the square must have valid walls
    return true;
  }

  /**
   * Helper method for verifyBoard().  Checks if a given square's walls are well-formed in the
   * given board.
   * @param board board for context
   * @param square square to check
   * @param size the size of the board being checked
   * @return true if the walls match, false if they don't
   */
  private static boolean checkSquare (Square[][] board, Square square, int size) {

    int x = square.xCoord;
    int y = square.yCoord;

    if (x < 0 || x >= size) {
      return false;
    }

    // Check X axis
    if (x == 0) {
      // If square is in left-most column
      if (!square.westWall) return false;
      if (x + 1 < size) {
        if (!(square.eastWall == board[y][x + 1].westWall)) return false;
      }
    }
    if (x == size - 1) {
      // If square is in right-most column
      if (!square.eastWall) return false;
      if (x - 1 > 0) {
        if (!(square.westWall == board[y][x - 1].eastWall)) return false;
      }
    }
    if (x != 0 && x != size - 1) {
      // If square is somewhere in the middle
      if (!(square.eastWall == board[y][x + 1].westWall)) return false;
      if (!(square.westWall == board[y][x - 1].eastWall)) return false;
    }

    // Check Y axis
    if (y == 0) {
      // If square is in top-most row
      if (!square.northWall) return false;
      if (y + 1 < size) {
        if (!(square.southWall == board[y + 1][x].northWall)) return false;
      }
    }
    if (y == size - 1) {
      // If square is in bottom-most row
      if (!square.southWall) return false;
      if (y - 1 > 0) {
        if (!(square.northWall == board[y - 1][x].southWall)) return false;
      }
    }
    if (y != 0 && y != size - 1) {
      // If square is somewhere in the middle
      if (!(square.southWall == board[y + 1][x].northWall)) return false;
      return square.northWall == board[y - 1][x].southWall;
    }

    // To get here, all the given square's walls must either be present and on the perimeter or
    //  the same as the appropriate wall of the square in that direction
    return true;
  }


  /**
   * Converts a given board into a string to be printed in console
   * @param board the given board, represented as a 2D array of Squares
   * @return the string version of the board
   */
  public static String boardToString(Square[][] board) {
    StringBuilder str = new StringBuilder(
        "    0 1 2 3 4 5 6 7 8 9 A B C D E F \n    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
    int curRow = 0;
    for (Square[] row : board) {
      boolean first = true;
      for (Square sq : row) {
        if (first) {
          if (curRow < 10) str.append(curRow).append("  |");
          else str.append(curRow).append(" |");
          first = false;
        }
        if (sq.southWall) str.append("_");
        else str.append(" ");
        if (sq.eastWall) str.append("|");
        else str.append(" ");
      }
      str.append("\n");
      curRow += 1;
    }

    return str.toString();
  }

  /**
   * Converts boards from user-friendly strings to program-friendly Squares.
   * @param input The 2D array of strings representing the board.  Each string represents one square
   *              and contains 'n', 'e', 's', and 'w' to indicate the existence of the corresponding
   *              wall.
   * @return a 2D array of Squares based on the given strings
   */
  public static Square[][] stringsToSquares(String[][] input) {
    if (input.length == 0) return null;
    int numRows = input.length;
    int numCols = input[0].length;
    Square[][] result = new Square[numRows][numCols];

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Square sq = new Square(false,false,false,false, col, row);
        String[] values = input[row][col].split("");
        for (String val : values) {
          switch (val) {
            case "n":
              sq.northWall = true;
              break;
            case "e":
              sq.eastWall = true;
              break;
            case "s":
              sq.southWall = true;
              break;
            case "w":
              sq.westWall = true;
              break;
          }
        }
        result[row][col] = sq;
      }
    }
    return result;
  }
}
