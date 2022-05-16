package edu.brown.cs.student.playerManagement;

import java.util.Date;

/**
 * Class for determining when a round will end. Waits for a set amount of time, then ends the round.
 */
public class Timer implements Runnable {

  public Game game;
  public Date endTime;

  /**
   * Creates a new timer object.
   * @param game The game this timer is for.
   * @param endTime The time at which this thread should end the game.
   */
  public Timer(Game game, Date endTime) {
    this.game = game;
    this.endTime = endTime;
  }

  @Override
  public void run() {
      try {
        System.out.println("Timer begin");
        Thread.sleep(endTime.getTime() - new Date().getTime());
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
      System.out.println("Timer over");
      game.endRound();
  }
}
