package edu.brown.cs.student.algorithm;

import edu.brown.cs.student.interfaces.Algorithm;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.MutableBoolean;
import edu.brown.cs.student.interfaces.RobotLocations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * A solver that solves a given board using 2 robots at a time breadth first search.
 */
public class Search2Robots implements Algorithm {

  /**
   * Used for the kill switch. Initialized to true.
   */
  private final MutableBoolean shouldRun = new MutableBoolean(true);

  /**
   * A list of solvers, one for each color combination, to solve the best path.
   */
  private final ArrayList<PathSolver> pathSolvers = new ArrayList<>();

  /**
   * The maximum depth we are willing to search at overall.
   */
  static int MAX_DEPTH = 8;

  /**
   * Constructor for PathSolver.
   * @param board - the Board to be solved.
   */
  public Search2Robots(Board board) {
    ArrayList<Color> allColorList = new ArrayList<>();
    allColorList.add(Color.Blue);
    allColorList.add(Color.Red);
    allColorList.add(Color.Green);
    allColorList.add(Color.Yellow);
    Color targetColor = board.getActiveTarget().color;
    for (Color color : allColorList) {
      ArrayList<Color> curColorList = new ArrayList<>();
      curColorList.add(targetColor);
      if (!color.equals(targetColor)) {
        curColorList.add(color);
        pathSolvers.add(new PathSolver(board, curColorList, this.shouldRun));
      }
    }
  }

  @Override
  public List<Move> getPath() {
    List<Move> bestPath = new ArrayList<>();
    int maxDepth = MAX_DEPTH;
    HashMap<RobotLocations, RobotLocations> searchHistory = new HashMap<>();
    for (PathSolver solver : this.pathSolvers) {
      solver.importSearchHistory(searchHistory);
      Optional<List<Move>> potentialBetterPath = solver.getBestPath(maxDepth - 1);
      if (potentialBetterPath.isPresent()) {
        maxDepth = potentialBetterPath.get().size();
        bestPath = potentialBetterPath.get();
      }
      searchHistory = solver.getRobotMoveSearchHistory();
      solver.clearSearchHistory();
    }
    if (bestPath.size() == 0) {
      return null;
    } else {
      return bestPath;
    }
  }

  @Override
  public void killThread() {
    this.shouldRun.setBoolean(false);
  }

  @Override
  public Optional<List<Move>> cappedGetPath(int maxDepth) {
    Optional<List<Move>> bestPath = Optional.empty();
    HashMap<RobotLocations, RobotLocations> searchHistory = new HashMap<>();
    for (PathSolver solver : this.pathSolvers) {
      solver.importSearchHistory(searchHistory);
      Optional<List<Move>> potentialBetterPath = solver.getBestPath(maxDepth);
      if (potentialBetterPath.isPresent()) {
        maxDepth = potentialBetterPath.get().size();
        bestPath = potentialBetterPath;
      }
      searchHistory = solver.getRobotMoveSearchHistory();
      solver.clearSearchHistory();
    }
    return bestPath;
  }

}
