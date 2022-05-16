package edu.brown.cs.student.interfaces;

public interface Board {
  Square[][] getBoard();

  RobotLocations getRobotLocations();

  RobotLocations algorithmMove(RobotLocations startLocations, Move m);

  Target[] getTargets();

  Target getActiveTarget();

  boolean isSolvedWithLocations(RobotLocations locations);

  void updateBetweenRounds(Move[] moves);

  boolean verifySolution(Move[] moves);
}
