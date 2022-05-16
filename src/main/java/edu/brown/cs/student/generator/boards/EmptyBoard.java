package edu.brown.cs.student.generator.boards;

import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;

public class EmptyBoard implements Board {

  @Override
  public Square[][] getBoard() {
    Square[][] basic = new Square[16][16];
    for (int i = 0; i < basic.length; i++) {
      for (int j = 0; j < basic[i].length; j++) {
        // i == 0 means top row
        if (i == 0) {
          // j == 0 means left col
          if (j == 0) {
            basic[i][j] = new Square(true,false,false,true, j, i);
          } else if (j == 15) {
            basic[i][j] = new Square(true,false,true,false, j, i);
          } else {
            basic[i][j] = new Square(true,false,false,false, j, i);
          }
        } else if (i == 15) {
          if (j == 0) {
            basic[i][j] = new Square(false,true,false,true, j, i);
          } else if (j == 15) {
            basic[i][j] = new Square(false,true,true,false, j, i);
          } else {
            basic[i][j] = new Square(false,true,false,false, j, i);
          }
        } else {
          if (j == 0) {
            basic[i][j] = new Square(false,false,false,true, j, i);
          } else if (j == 15) {
            basic[i][j] = new Square(false,false,true,false, j, i);
          } else {
            basic[i][j] = new Square(false,false,false,false, j, i);
          }
        }
      }
    }
    return basic;
  }


  @Override
  public RobotLocations getRobotLocations() {
    return null;
  }

  @Override
  public RobotLocations algorithmMove(RobotLocations startLocations, Move m) {
    return null;
  }

  @Override
  public Target[] getTargets() {
    return new Target[0];
  }

  @Override
  public Target getActiveTarget() {
    return null;
  }

  @Override
  public boolean isSolvedWithLocations(RobotLocations locations) {
    return false;
  }

  @Override
  public void updateBetweenRounds(Move[] moves) {

  }

  @Override
  public boolean verifySolution(Move[] moves) {
    return false;
  }
}
