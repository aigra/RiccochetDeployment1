package edu.brown.cs.student.interfaces;

public class Coordinate {

  public int x;
  public int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Converts the Coordinate on which it is called to a print-friendly string.
   * @return Print-friendly string in question
   */
  public String coordinateToString() {
    return "(X: " + this.x + ", Y: " + this.y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Coordinate) {
      Coordinate c = (Coordinate) o;
      return (this.x == c.x && this.y == c.y);
    } else {
      return false;
    }
  }
}
