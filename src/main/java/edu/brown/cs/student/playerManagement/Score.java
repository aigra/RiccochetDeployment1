package edu.brown.cs.student.playerManagement;

/**
 * Class representing a player's score
 */
public class Score {
  public String username;
  public int score;

  /**
   * Constructor for a player's score
   * @param username the player's username
   * @param score the player's score on the leaderboard
   */
  public Score(String username, int score) {
    this.username = username;
    this.score = score;
  }

  @Override
  public boolean equals(Object a) {
    if (!(a instanceof Score)) {
      return false;
    }
    Score A = (Score) a;
    return (A.username.equals(username) && A.score == score);
  }

  @Override
  public String toString() {
    return "username: " + username + " score: " + score;
  }
}
