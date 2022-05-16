package repl;

import edu.brown.cs.student.playerManagement.LobbyManager;
import edu.brown.cs.student.playerManagement.Score;
import edu.brown.cs.student.playerManagement.Scoreboard;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ScoreBoardTest {

  @Test
  public void testBasicScoreboard() {
    Scoreboard scores = new Scoreboard();
    scores.insert("Aaron", 8);
    scores.insert("Bob", 8);
    Score[] myArray = {new Score("Aaron", 8),  new Score("Bob", 8)};
    assertArrayEquals(scores.getLowestScores(), myArray);
  }

  @Test
  public void testComplexScoreboard() {
    Scoreboard scores = new Scoreboard();
    scores.insert("Aaron", 8);
    scores.insert("Bob", 8);
    scores.insert("Bob", 5);
    scores.insert("Bob", 9);

    Score[] myArray = {new Score("Bob", 5), new Score("Aaron", 8)};
    assertArrayEquals(scores.getLowestScores(), myArray);

    scores.insert("George", 6);
    Score[] myArray2 = {new Score("Bob", 5), new Score("George", 6), new Score("Aaron", 8)};
    assertArrayEquals(scores.getLowestScores(), myArray2);
  }

}
