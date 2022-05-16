package edu.brown.cs.student.algorithm;

import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.MutableBoolean;
import edu.brown.cs.student.interfaces.RobotLocations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * A class that solves paths. Used for all algorithms.
 */
public class PathSolver {
  /**
   * A HashMap that is used for the solver. The value is the old RobotLocations, and the key is the
   * result RobotLocations.
   * At the end of the solver, it builds the path by iterating through this HashMap.
   */
  private HashMap<RobotLocations, RobotLocations> robotMoveSearchHistory = new HashMap<>();

  /**
   * The board we are trying to solve the optimal path for.
   */
  private final Board myBoard;

  /**
   * The list of the colors we want to search through. Used in getPath.
   */
  private final ArrayList<Color> curColorList;

  /**
   * Used for the kill switch. Initialized to true.
   */
  private final MutableBoolean shouldRun;

  /**
   * Constructor for PathSolver.
   * @param board - the Board to be solved.
   * @param colorList - the list of colors to search through.
   * @param shouldRun - kill switch.
   */
  public PathSolver(Board board, ArrayList<Color> colorList, MutableBoolean shouldRun) {
    this.myBoard = board;
    this.curColorList = colorList;
    this.shouldRun = shouldRun;
  }

  /**
   * Gets the best path with a max depth to stop at.
   * @param maxDepth - the maximum depth to search.
   * @return - the list of moves representing the best path.
   */
  public Optional<List<Move>> getBestPath(int maxDepth) {
    RobotLocations originalLocations = myBoard.getRobotLocations();
    ArrayList<RobotLocations> inputList = new ArrayList<>();
    inputList.add(originalLocations);
    Optional<RobotLocations> currentLocation = getPathHelper(inputList, 1, maxDepth);
    return currentLocation.map(
        robotLocations -> this.getPathFromLocation(robotLocations, myBoard.getRobotLocations()));
  }

  /**
   * Clears the search history so the solver can be run with different maxDepths.
   */
  public void clearSearchHistory() {
    robotMoveSearchHistory.clear();
  }

  /**
   * Imports the search history so the solver can use other solver's work.
   * @param searchHistory - the search history to import.
   */
  public void importSearchHistory(HashMap<RobotLocations, RobotLocations> searchHistory) {
    robotMoveSearchHistory = searchHistory;
  }

  /**
   * Gets the search history so other solvers can use it.
   * @return - the current search history.
   */
  public HashMap<RobotLocations, RobotLocations> getRobotMoveSearchHistory() {
    return this.robotMoveSearchHistory;
  }

  /**
   * Recursively finds a path with the least number of moves on the Board.
   * @param startingPositionsList - the list of RobotLocations we start from at this depth.
   * @param depth - the current depth of the search.
   * @param maxDepth - the maximum depth of the search.
   * @return The final RobotLocations that is the solution for a given board, to be used in getPath
   * to output the list of Moves.
   */
  private Optional<RobotLocations> getPathHelper(List<RobotLocations> startingPositionsList, int depth, int maxDepth) {
    if (maxDepth < depth) {
      return Optional.empty();
    }
    ArrayList<RobotLocations> nextToSearch = new ArrayList<>();
    for (RobotLocations currentLocation : startingPositionsList) {
      for (Direction dir : Direction.values()) {
        for (Color color : this.curColorList) {
          if (!this.shouldRun.getBoolean()) {
            return Optional.empty();
          }
          Move newMove = new Move(color, dir);
          RobotLocations newRobotLocations = myBoard.algorithmMove(currentLocation, newMove);
          if (myBoard.isSolvedWithLocations(newRobotLocations)) {
            robotMoveSearchHistory
                .put(newRobotLocations, currentLocation);
            return(Optional.of(newRobotLocations));
          } else if (!(robotMoveSearchHistory.containsValue(newRobotLocations) || robotMoveSearchHistory.containsKey(newRobotLocations))) {
            robotMoveSearchHistory
                .put(newRobotLocations, currentLocation);
            nextToSearch.add(newRobotLocations);
          }
        }
      }
    }
    return getPathHelper(nextToSearch, depth + 1, maxDepth);
  }

  /**
   * Gets the path from one location to the other.
   * @param finalLocation The final RobotLocations.
   * @param originalLocations The starting RobotLocations.
   * @return - the list of Moves to get from originalLocations to finalLocation.
   */
  private List<Move> getPathFromLocation(RobotLocations finalLocation, RobotLocations originalLocations) {
    RobotLocations currentLocation = finalLocation;
    RobotLocations locationsBeforeCurrent = robotMoveSearchHistory.get(finalLocation);
    ArrayList<Move> solution = new ArrayList<>();
    while (!currentLocation.equals(originalLocations)) {
      solution.add(getMoveFromLocations(currentLocation, locationsBeforeCurrent));
      currentLocation = locationsBeforeCurrent;
      locationsBeforeCurrent = robotMoveSearchHistory.get(currentLocation);
    }
    Collections.reverse(solution);
    return solution;
  }

  /**
   * Gets the move from a pair of adjacent locations.
   * @param newLocation The RobotLocations after one move.
   * @param oldLocation The starting RobotLocations.
   * @return - The Move that is used to go from oldLocation to newLocation.
   */
  private Move getMoveFromLocations(RobotLocations newLocation, RobotLocations oldLocation) {
    for (Color color : this.curColorList) {
      Coordinate oldCoordinate = oldLocation.locations.get(color);
      Coordinate newCoordinate = newLocation.locations.get(color);
      if (oldCoordinate.x < newCoordinate.x) {
        return new Move(color, Direction.EAST);
      } else if (oldCoordinate.x > newCoordinate.x) {
        return new Move(color, Direction.WEST);
      } else if (oldCoordinate.y < newCoordinate.y) {
        return new Move(color, Direction.SOUTH);
      } else if (oldCoordinate.y > newCoordinate.y) {
        return new Move(color, Direction.NORTH);
      }
    }
    throw new RuntimeException("ERROR: getMoveFromLocations didn't find a move.");
  }

}
