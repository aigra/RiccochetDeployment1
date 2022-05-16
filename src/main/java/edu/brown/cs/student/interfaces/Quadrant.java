package edu.brown.cs.student.interfaces;

public class Quadrant {

  private final Square[][] quad;
  private final Target[] targets;

  public Quadrant(Square[][] quad, Target[] targets) {
    this.quad = quad;
    this.targets = targets;
  }

  /**
   * Getter for the actual Squares of the quadrant.
   * @return the 2D array of Squares
   */
  public Square[][] getQuad() {
    return this.quad;
  }

  /**
   * Setter for the first index of quad, made available for purposes of testing the copy
   * functionality (some mutable variable is necessary).
   * @param s 1D array of Squares to be set
   */
  public void setQuad0(Square[] s) {
    this.quad[0] = s;
  }

  /**
   * Getter for the Targets of the quadrant
   * @return the array of Targets
   */
  public Target[] getTargets() {
    return this.targets;
  }


  /**
   * Rotates the quadrant on which it is called by increments of 90 degrees clockwise, returning a
   * new Quadrant with the rotated Squares and Targets.  This functionality is necessary since all
   * Quadrants will be stored by default as if they are the top left corner of the board.
   * @param r The number of times for the quadrant to be rotated 90 degrees clockwise.  Should be
   *          either 1, 2, or 3.
   * @return A new Quadrant, whose Squares and Targets correspond to the rotated positions of the
   * original target.
   */
  public static Quadrant rotate(Quadrant q, int r) {
    Quadrant res = copy(q);
    // Check that a valid number of rotations is given
    if (r > 0 && r < 4) {
      for (int i = 0; i < r; i++) {
        rotateOnce(res);
      }
    }
    return res;
  }

  /**
   * Helper function for rotate().  Rotates the quadrant 90 degrees clockwise, updating the relevant
   * values to match.
   */
  private static void rotateOnce(Quadrant q) {
    int numRows = q.quad.length;
    int numCols = q.quad[0].length;
    Quadrant oldQuad = copy(q);
    Square[][] oldSquares = oldQuad.getQuad();
    Target[] oldTargets = oldQuad.getTargets();
    // Rotate the Square array 90 degrees clockwise
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        // Doing it in this order assumes the Quadrant is a square, but that's ok because they are
        int newCol = numRows - 1 - r;
        Square old = oldSquares[r][c];
        Square newSquare =
            new Square(old.westWall, old.eastWall, old.northWall, old.southWall, newCol, c);
        q.quad[c][newCol] = newSquare;
      }
    }
    // Rotate each Target in the Target array 90 degrees clockwise
    for (int i = 0; i < oldTargets.length; i++) {
      int oldX = q.targets[i].coordinate.x;
      int oldY = q.targets[i].coordinate.y;
      // Create a new Coordinate for each Target holding rotated x,y values
      q.targets[i].coordinate = new Coordinate(numRows - 1 - oldY, oldX);
    }
    // adapted from https://stackoverflow.com/questions/2799755/rotate-array-clockwise
  }

  /**
   * Converts the given 2D array of squares (from a Quadrant) into a user-friendly string
   * @param board Squares to be turned into a string version.
   * @return Visually appealing rendition of the data in the given Squares
   */
  public static String quadToString(Square[][] board) {
    StringBuilder str = new StringBuilder("    0 1 2 3 4 5 6 7 \n");
    boolean topRow = true;
    int curRow = 0;
    for (Square[] row : board) {
      if (topRow) {
        str.append("    ");
        for (Square sq : row) {
          if (sq.northWall) str.append("_ ");
          else str.append("  ");
        }
        str.append("\n");
        topRow = false;
      }
      boolean first = true;
      for (Square sq : row) {
        if (first && sq.westWall) {
          if (curRow < 10) str.append(curRow).append("  |");
          else str.append(curRow).append(" |");
          first = false;
        } else if (first) {
          if (curRow < 10) str.append(curRow).append("   ");
          else str.append(curRow).append("  ");
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
   * Creates a non-referential copy of the given Quadrant.  Helps clean up rotation so that
   * stored Quadrants can be rotated without actually altering their stored values.
   * @param q The Quadrant to be copied
   * @return A copy of the given Quadrant, which has all identical values that can be independently
   * changed.
   */
  public static Quadrant copy(Quadrant q) {
    Square[][] s = new Square[q.getQuad().length][q.getQuad()[0].length];
    for (int row = 0; row < q.getQuad().length; row++) {
      for (int col = 0; col < q.getQuad()[0].length; col++) {
        Square old = q.getQuad()[row][col];
        Square ns = new Square(old.northWall, old.southWall, old.eastWall, old.westWall,
            old.xCoord, old.yCoord);
        s[row][col] = ns;
      }
    }

    Target[] t = new Target[q.getTargets().length];
    for (int i = 0; i < q.getTargets().length; i++) {
      Target old = q.getTargets()[i];
      t[i] = new Target(old.color, new Coordinate(old.coordinate.x, old.coordinate.y));
    }

    return new Quadrant(s, t);
  }

  /**
   * Checks if the Quadrant on which it is called is well-formed, with all walls being present in
   * both relevant Squares (unless on the edge of the Quadrant).
   * @return True if the Quadrant is well-formed, false if not
   */
  public boolean validateQuadrant(int size) {
    if (this.quad.length != size) return false;
    for (Square[] row : this.quad) {
      if (row.length != size) {
        System.out.println("Row " + row[0].yCoord + " is not proper length");
        return false;
      }
      for (Square sq : row) {
        if(!this.checkSquare(this.quad, sq, size)){
          System.out.printf("Failed at row: %d, col %d%n", sq.yCoord, sq.xCoord);
          return false;
        }
      }
    }
    return true;
  }

  /**
   * A version of checkSquare() from BoardGenerator, adapted to check Quadrants rather than entire
   * boards.  The only differences are the dimensions, and that the bottom and right-most squares
   * do not need south and east walls respectively (since all quadrants are oriented in the top-left
   * by default)
   * @param board The 2D array of squares for context
   * @param square The square to check
   * @return True if the square is valid relative to the board and its neighbors, false if not
   */
  private boolean checkSquare (Square[][] board, Square square, int size) {

    int x = square.xCoord;
    int y = square.yCoord;

    // Check X axis
    if (x == 0) {
      // If square is in left-most column
      if (!square.westWall) return false;
      if (x + 1 < size) {
        if (!(square.eastWall == board[y][x + 1].westWall)) return false;
      }
    }
    // If square is in right-most column
    if (x == size - 1) {
      if(x - 1 > 0) {
        if (!(square.westWall == board[y][x - 1].eastWall)) return false;
      }
    }
    // If square is somewhere in the middle
    if (x != 0 && x != size - 1) {
      if (x + 1 < size) {
        if (!(square.eastWall == board[y][x + 1].westWall)) return false;
      }
      if (x - 1 > 0) {
        if (!(square.westWall == board[y][x - 1].eastWall)) return false;
      }
    }

    // Check Y axis
    if (y == 0) {
      // If square is in top-most row
      if (!square.northWall) return false;
      if (y + 1 < size) {
        if (!(square.southWall == board[y + 1][x].northWall)) return false;
      }
    }
      // If square is in bottom-most row
    if (y == size - 1) {
      if (y - 1 > 0) {
        if (!(square.northWall == board[y - 1][x].southWall)) return false;
      }
    }
    // If square is somewhere in the middle
    if (y != 0 && y != size - 1) {
      if (y + 1 < size) {
        if (!(square.southWall == board[y + 1][x].northWall)) return false;
      }
      if (y - 1 > 0) {
        if (!(square.northWall == board[y - 1][x].southWall)) return false;
      }
    }

    // To get here, all the given square's walls must either be present and on the perimeter or
    //  the same as the appropriate wall of the square in that direction
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Quadrant) {
      Quadrant q = (Quadrant) o;
      if (this.quad.length != q.getQuad().length) return false;
      if (this.targets.length != q.getTargets().length) return false;
      // Check that every square in the 2D array is the same
      for (int i = 0; i < this.quad.length; i++) {
        if (this.quad[i].length != q.getQuad()[i].length) return false;
        for (int j = 0; j < this.quad[i].length; j++) {
          if (!this.quad[i][j].equals(q.getQuad()[i][j])) return false;
        }
      }
      // Check that every target is the same (currently order-dependent)
      for (int i = 0; i < this.targets.length; i++) {
        if (!this.targets[i].equals(q.getTargets()[i])) return false;
      }
      return true;
    } else {
      return false;
    }
  }

}
