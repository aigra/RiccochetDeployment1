package repl;

import edu.brown.cs.student.playerManagement.Game;
import edu.brown.cs.student.playerManagement.Score;
import edu.brown.cs.student.playerManagement.Scoreboard;
import edu.brown.cs.student.repl.REPLCmdMap;
import edu.brown.cs.student.repl.REPLCommand;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class AlgoSubmissionTest {
  @Test
  public void testAlgorithmSubmission() throws InterruptedException {
    Game algoTest = new Game(1, 10);
    algoTest.overallScores.insert("AlexRobot", 0);
    algoTest.overallScores.insert("BFS", 0);
    algoTest.overallScores.insert("BlankSolution", 0);
    algoTest.alexMethod = true;
    System.out.println("About to start game");
    algoTest.startGame();
    System.out.println("game begun");
    algoTest.submitSolution("BlankSolution", 10);
    Thread.sleep(15000);
  }
}
