package edu.brown.cs.student.playerManagement;

import edu.brown.cs.student.interfaces.Algorithm;
import edu.brown.cs.student.interfaces.Move;

import java.util.List;

public class AlgorithmRunner implements Runnable {

  public Game game;
  public Algorithm algo;
  public String playerName;

  /**
   * Class for creating a thread which runs an algorithm
   * @param algo The algorithm that will be used to solve the board
   * @param playerName If a solution is found, the name the algorithm will submit with
   */
  public AlgorithmRunner(Algorithm algo, String playerName, Game game) {
    this.algo = algo;
    this.playerName = playerName;
    this.game = game;
  }


  @Override
  public void run() {
    List<Move> solution = algo.getPath();
    if (solution != null) {
      game.submitSolution(playerName, solution.toArray(new Move[0]));
      System.out.println("Solution found!");
    } else {
      System.out.println("No solution found or algorithm terminated early");
    }
  }
}
