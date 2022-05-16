package generator;

import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Target;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RicoRobotsBoardTest {

  @Test
  public void testIsSolvedWithLocations() {

    // Base case with no robots or targets
    Assert.assertFalse(TestStorage.singleWellFormed
        .isSolvedWithLocations(new RobotLocations(new HashMap<>())));

    // Base case with robot but no target
    HashMap<Color, Coordinate> r1 = new HashMap<>();
    r1.put(Color.Red, new Coordinate(0, 0));
    Assert.assertFalse(TestStorage.singleWellFormed
        .isSolvedWithLocations(new RobotLocations(r1)));

    // Base case with wrong color robot on target
    HashMap<Color, Coordinate> r2 = new HashMap<>();
    r2.put(Color.Blue, new Coordinate(0, 1));
    Assert.assertFalse(TestStorage.doubleWithTarget
        .isSolvedWithLocations(new RobotLocations(r2)));

    // Base case with correct color robot on target
    HashMap<Color, Coordinate> r3 = new HashMap<>();
    r3.put(Color.Red, new Coordinate(0, 1));
    Assert.assertTrue(TestStorage.doubleWithTarget
        .isSolvedWithLocations(new RobotLocations(r3)));

    // Base case with correct color robot off target
    HashMap<Color, Coordinate> r4 = new HashMap<>();
    r4.put(Color.Red, new Coordinate(1, 1));
    Assert.assertFalse(TestStorage.doubleWithTarget
        .isSolvedWithLocations(new RobotLocations(r4)));

  }

  @Test
  public void testAlgorithmMove() {

    // 1x1 board, test collision detection with walls in all directions
    HashMap<Color, Coordinate> r1 = new HashMap<>();
    r1.put(Color.Red, new Coordinate(0, 0));
    RobotLocations loc1 = new RobotLocations(r1);
    Assert.assertEquals(TestStorage.singleWellFormed.algorithmMove(loc1,
        new Move(Color.Red, Direction.NORTH)), loc1);
    Assert.assertEquals(TestStorage.singleWellFormed.algorithmMove(loc1,
        new Move(Color.Red, Direction.EAST)), loc1);
    Assert.assertEquals(TestStorage.singleWellFormed.algorithmMove(loc1,
        new Move(Color.Red, Direction.SOUTH)), loc1);
    Assert.assertEquals(TestStorage.singleWellFormed.algorithmMove(loc1,
        new Move(Color.Red, Direction.WEST)), loc1);

    // 2x2 board, test collision detection with other robots in all directions
    HashMap<Color, Coordinate> r2 = new HashMap<>();
    r2.put(Color.Red, new Coordinate(0, 0));
    r2.put(Color.Blue, new Coordinate(0, 1));
    r2.put(Color.Green, new Coordinate(1, 0));
    RobotLocations loc2 = new RobotLocations(r2);
    Assert.assertEquals(TestStorage.doubleWellFormed.algorithmMove(loc2,
        new Move(Color.Red, Direction.SOUTH)), loc2);
    Assert.assertEquals(TestStorage.doubleWellFormed.algorithmMove(loc2,
        new Move(Color.Blue, Direction.NORTH)), loc2);
    Assert.assertEquals(TestStorage.doubleWellFormed.algorithmMove(loc2,
        new Move(Color.Red, Direction.EAST)), loc2);
    Assert.assertEquals(TestStorage.doubleWellFormed.algorithmMove(loc2,
        new Move(Color.Green, Direction.WEST)), loc2);

    // 5x5 board, test that robot moves multiple squares in each direction until colliding
    HashMap<Color, Coordinate> r3 = new HashMap<>();
    r3.put(Color.Red, new Coordinate(2, 2));
    RobotLocations loc3 = new RobotLocations(r3);
    Assert.assertEquals(TestStorage.fiveWellFormed.algorithmMove(loc3,
        new Move(Color.Red, Direction.NORTH)),
        new RobotLocations(new HashMap<Color, Coordinate>(Map.of(Color.Red,
            new Coordinate(2, 0)))));
    Assert.assertEquals(TestStorage.fiveWellFormed.algorithmMove(loc3,
            new Move(Color.Red, Direction.EAST)),
        new RobotLocations(new HashMap<Color, Coordinate>(Map.of(Color.Red,
            new Coordinate(4, 0)))));
    Assert.assertEquals(TestStorage.fiveWellFormed.algorithmMove(loc3,
            new Move(Color.Red, Direction.SOUTH)),
        new RobotLocations(new HashMap<Color, Coordinate>(Map.of(Color.Red,
            new Coordinate(4, 4)))));
    Assert.assertEquals(TestStorage.fiveWellFormed.algorithmMove(loc3,
            new Move(Color.Red, Direction.WEST)),
        new RobotLocations(new HashMap<Color, Coordinate>(Map.of(Color.Red,
            new Coordinate(0, 4)))));

  }

  @Test
  public void testVerifySolution() {

    // Sequence of moves should be followed, and should end with the robot on the target
    Move[] m1 = new Move[]{
        new Move(Color.Red, Direction.NORTH),
        new Move(Color.Red, Direction.EAST),
        new Move(Color.Red, Direction.SOUTH),
        new Move(Color.Red, Direction.WEST)
    };
    Assert.assertTrue(TestStorage.fiveWellFormed.verifySolution(m1));

    // Sequence of moves should be followed, and should end with the robot not on the target
    Move[] m2 = new Move[]{
        new Move(Color.Red, Direction.NORTH),
        new Move(Color.Red, Direction.EAST),
        new Move(Color.Red, Direction.SOUTH)
    };
    Assert.assertFalse(TestStorage.fiveWellFormed.verifySolution(m2));

  }

  @Test
  public void testUpdateBetweenRounds() {

    // Execute a series of moves
    Target firstActive = TestStorage.fiveTwoColor.getActiveTarget();
    Target secondActive = null;
    for (int i = 0; i < TestStorage.fiveTwoColor.getTargets().length; i++) {
      if (firstActive.equals(TestStorage.fiveTwoColor.getTargets()[i])) {
        if (i == 0) {
          secondActive = TestStorage.fiveTwoColor.getTargets()[1];
        } else {
          secondActive = TestStorage.fiveTwoColor.getTargets()[0];
        }
      }
    }
    // Check that the target that is supposed to be the next active target is not the same as
    // the current active target (this is sort of a test for the test)
    Assert.assertFalse(firstActive.equals(secondActive));
    Move[] m1 = {
        new Move(Color.Red, Direction.NORTH),
        new Move(Color.Red, Direction.EAST),
        new Move(Color.Red, Direction.SOUTH),
        new Move(Color.Blue, Direction.WEST)
    };
    TestStorage.fiveTwoColor.updateBetweenRounds(m1);
    // Check that the active target is changed to the one that wasn't used originally
    Assert.assertTrue(secondActive.equals(TestStorage.fiveTwoColor.getActiveTarget()));
    // Check that the RobotLocations of the board is changed to the robots' end locations after
    // executing the given moves
    RobotLocations r1 = new RobotLocations(new HashMap<>(Map.of(Color.Red,
        new Coordinate(4, 4), Color.Blue, new Coordinate(0, 2))));
    Assert.assertTrue(TestStorage.fiveTwoColor.getRobotLocations().equals(r1));

  }

}
