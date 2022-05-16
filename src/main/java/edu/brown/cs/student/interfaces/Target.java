package edu.brown.cs.student.interfaces;

public class Target {

//  public TargetShape shape;
  public Color color;
  public Coordinate coordinate;

  public Target(Color color, Coordinate coord) {
//    this.shape = shape;
    this.color = color;
    this.coordinate = coord;
  }

  public String targetToString() {
    return "COLOR: " + this.color + "; COORD: " + this.coordinate.coordinateToString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Target) {
      Target t = (Target) o;
      return (this.coordinate.equals(t.coordinate) && this.color == t.color);
    } else {
      return false;
    }
  }

}
