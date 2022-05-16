package edu.brown.cs.student.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * An interface for algorithms that solve Boards.
 */
public interface Algorithm {
  /**
   * Gets a path that has the least possible number of moves.
   * @return a path that has the least possible number of moves.
   */
  List<Move> getPath();

  /**
   * Kills the thread, stopping instruction.
   */
  void killThread();

  /**
   * Gets the shortest path that is at max maxDepth moves total, if there is one.
   * @param maxDepth - the maximum depth that we search through.
   * @return either a list of moves or null.
   */
  Optional<List<Move>> cappedGetPath(int maxDepth);

}
