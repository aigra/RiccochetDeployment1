package edu.brown.cs.student.algorithm;

import edu.brown.cs.student.interfaces.Algorithm;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.MutableBoolean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A solver that solves a given board using all the robots breadth first search.
 */
public class SearchAllRobots implements Algorithm {

  /**
   * Used for the kill switch. Initialized to true.
   */
  private final MutableBoolean shouldRun = new MutableBoolean(true);

  /**
   * The solver that outputs the path.
   */
  private final PathSolver pathSolver;

  /**
   * The maximum depth we are willing to search at overall.
   */
  static int MAX_DEPTH = 15;

  /**
   * Constructor for PathSolver.
   * @param board - the Board to be solved.
   */
  public SearchAllRobots(Board board) {
    ArrayList<Color> myColorList = new ArrayList<>();
    myColorList.add(Color.Blue);
    myColorList.add(Color.Red);
    myColorList.add(Color.Green);
    myColorList.add(Color.Yellow);
    this.pathSolver = new PathSolver(board, myColorList, this.shouldRun);
  }

  @Override
  public List<Move> getPath() {
    Optional<List<Move>> potentialBestPath = pathSolver.getBestPath(MAX_DEPTH);
    pathSolver.clearSearchHistory();
    return potentialBestPath.orElse(null);
  }

  @Override
  public void killThread() {
    this.shouldRun.setBoolean(false);
  }

  @Override
  public Optional<List<Move>> cappedGetPath(int maxDepth) {
    Optional<List<Move>> potentialBestPath = pathSolver.getBestPath(maxDepth);
    pathSolver.clearSearchHistory();
    return potentialBestPath;
  }

}
