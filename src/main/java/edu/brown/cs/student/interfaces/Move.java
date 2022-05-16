package edu.brown.cs.student.interfaces;

public class Move {
  public Color robot;
  public Direction direction;

  public Move(Color color, Direction direction) {
    this.robot = color;
    this.direction = direction;
  }

  public String toString() {
    return robot.toString() + ": " + direction.toString();
  }

  @Override
  public boolean equals(Object move) {
    if (move instanceof Move) {
      return (this.robot== ((Move) move).robot) && (this.direction == ((Move) move).direction);
    } else {
      return false;
    }
  }

}
