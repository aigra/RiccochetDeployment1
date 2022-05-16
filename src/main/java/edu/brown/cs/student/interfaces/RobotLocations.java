package edu.brown.cs.student.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * A representation of where the robots are located on the board.
 */
public class RobotLocations {
  /**
   * A map that describes where the robots are located. Each robot is mapped to its corresponding
   * coordinate.
   */
  public Map<Color, Coordinate> locations;

  /**
   * Constructor for RobotLocations.
   * @param locations - the map of Color/Robot locations.
   */
  public RobotLocations(Map<Color, Coordinate> locations) {
    this.locations = locations;
  }

  /**
   * Converts the RobotLocations on which it is called to a print-friendly string
   * @return String version of the RobotLocation
   */
  public String robotLocationsToString() {
    StringBuilder res = new StringBuilder();
    for (Color robot : this.locations.keySet()) {
      res.append(robot.toString()).append(": ")
          .append(this.locations.get(robot).coordinateToString());
      res.append("\n");
    }
    return res.toString();
  }

  public Map<String, Coordinate> stringLocations() {
    Map<String, Coordinate> toReturn = new HashMap<>();
    for (Color robot : this.locations.keySet()) {
      toReturn.put(robot.toString(), locations.get(robot));
    }
    return toReturn;
  }

  public RobotLocations createCopy() {
    Map<Color, Coordinate> newLocations = new HashMap<>();
    for (Color robot : this.locations.keySet()) {
      Coordinate curLocation = this.locations.get(robot);
      int x = curLocation.x;
      int y = curLocation.y;
      newLocations.put(robot, new Coordinate(x, y));
    }
    return new RobotLocations(newLocations);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof RobotLocations) {
      RobotLocations r = (RobotLocations) o;
      // Check that they have the same colors
      for (Color c : this.locations.keySet()) {
        if (!r.locations.containsKey(c)) return false;
      }
      for (Color c : r.locations.keySet()) {
        if (!this.locations.containsKey(c)) return false;
      }
      // Check that the coordinate for each color matches
      for (Color c : this.locations.keySet()) {
        if (!this.locations.get(c).equals(r.locations.get(c))) return false;
      }
      return true;
    } else {
      return false;
    }
  }
}
