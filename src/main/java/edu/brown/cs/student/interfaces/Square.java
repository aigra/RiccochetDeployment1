package edu.brown.cs.student.interfaces;

public class Square {
  public boolean northWall;
  public boolean southWall;
  public boolean eastWall;
  public boolean westWall;

  // Coordinates for the board start at (0,0) at top left and go down to (15,15) at bottom right
  public int xCoord;
  public int yCoord;

  public boolean target = false;
  public boolean containsRobot = false;
  public String targetColor = "";
  public String targetShape = "";

  public Square(boolean north, boolean south, boolean east, boolean west, int x, int y) {
    this.northWall = north;
    this.southWall = south;
    this.eastWall = east;
    this.westWall = west;
    this.xCoord = x;
    this.yCoord = y;
  }

  /**
   * Converts the square on which it is called to a user-friendly string containing all pertinent
   * information.
   * @return Stringified version of the Square
   */
  public String squareToString() {
    String res = "North: " + this.northWall;
    res += "\nEast: " + this.eastWall;
    res += "\nSouth: " + this.southWall;
    res += "\nWest: " + this.westWall;
    res += "\nRow (Y): " + this.yCoord;
    res += "\nCol (X): " + this.xCoord;
    return res;
  }

  @Override
  public boolean equals(Object square) {
    if (square instanceof Square) {
      Square sq = (Square) square;
      return (this.northWall == sq.northWall && this.southWall == sq.southWall &&
          this. eastWall == sq.eastWall && this.westWall == sq.westWall &&
          this.xCoord == sq.xCoord && this.yCoord == sq.yCoord);
    } else {
      return false;
    }
  }

}
