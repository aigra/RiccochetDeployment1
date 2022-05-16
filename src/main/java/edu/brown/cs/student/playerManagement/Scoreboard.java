package edu.brown.cs.student.playerManagement;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Class which maintains a sorted list of scores and corresponding players.
 */
public class Scoreboard {
  private HashMap<String, Node> scoreMap;
  private Node start;


  /**
   * Creates a new scoreboard. Implemented as a doubly linked list.
   */
  public Scoreboard() {
    start = null;
    scoreMap = new HashMap<>();
  }

  /**
   * Get the score of a given player.
   * @param username A player who has a score.
   * @return The score of that player
   */
  public int getScore(String username) {
    Node nd = scoreMap.get(username);
    if (nd != null) {
      return scoreMap.get(username).score.score;
    } else {
      return Integer.MAX_VALUE;
    }
  }

  /**
   * Gets the highest scoring player
   * @return The username associated with the highest score.
   */
  public String getFirst() {
    return start.score.username;
  }

  /**
   * Class to store one of the scores on the scoreboard.
   */
  public class Node {
    public Score score;
    public Node prev;
    public Node next;

    public Node(Score score, Node prev, Node next) {
      this.score = score;
      this.prev = prev;
      this.next = next;
    }
  }

  /**
   * Increases the score of a player by one.
   * @param username The player of which their score should be increased.
   */
  public void increase(String username) {
    Node prevScore = scoreMap.get(username);
    if (prevScore == null) {
      insert(username, 1);
    } else {
      remove(username);
      insert(username, prevScore.score.score + 1);
    }
  }

  /**
   * Adds a player and their score to the scoreboard, keeping their lowest overall score
   * @param username The player to be added to the scoreboard
   * @param score The players score
   * @return True if it updated the list, false if it didn't.
   */
  public boolean insert(String username, int score) {
    if (getScore(username) < score) {
      return false;
    }
    remove(username);
    Score newScore = new Score(username, score);
    Node prevNode = findPrev(score);
    if (prevNode == null) {
      Node myNode = new Node(newScore, null, start);
      scoreMap.put(username, myNode);
      if (start != null) {
        start.prev = myNode;
      }
      start = myNode;
    } else {
      Node myNode = new Node(newScore, prevNode, prevNode.next);
      scoreMap.put(username, myNode);
      if (prevNode.next != null) {
        prevNode.next.prev = myNode;
      }
      prevNode.next = myNode;
    }
    return true;
  }

  private void remove(String username) {
    Node oldNode = scoreMap.get(username);
    if (oldNode == null) {
      return;
    }
    scoreMap.remove(username);
    if (oldNode.prev != null) {
      oldNode.prev.next = oldNode.next;
    } else {
      start = oldNode.next;
    }
    if (oldNode.next != null) {
      oldNode.next.prev = oldNode.prev;
    }
  }

  /**
   * Helper method for insert, finds the node that goes before a score to be added.
   * @param score The score which will be added.
   * @return The node which should come before a new one, null if it should be the first element
   */
  private Node findPrev(int score) {
    Node curr = null;
    Node next = start;
    while (next != null && next.score.score <= score) {
      curr = next;
      next = curr.next;
    }
    return curr;
  }

  /**
   * Get an array of all scores, sorted lowest to highest.
   * @return An array of scores
   */
  public Score[] getLowestScores() {
    Node curr = start;
    Score[] scores = new Score[scoreMap.size()];
    int i = 0;
    while (curr != null) {
      scores[i] = curr.score;
      curr = curr.next;
      i++;
    }
    return scores;
  }

  /**
   * Get an array of all scores, sorted highest to lowest.
   * @return An array of scores
   */
  public Score[] getHighestScores() {
    Node curr = start;
    Score[] scores = new Score[scoreMap.size()];
    int j = scoreMap.size() - 1;
    while (curr != null) {
      scores[j] = curr.score;
      curr = curr.next;
      j--;
    }
    return scores;
  }

//  public Score[] getTopScores(int n) {
//    int size = Math.min(scoreMap.size(), n);
//    Score[] scores = new Score[size];
//    Node curr = start;
//    for (int i = 0; i < size; i++) {
//      scores[i] = curr.score;
//      curr = curr.next;
//    }
//    return scores;
//  }
}
