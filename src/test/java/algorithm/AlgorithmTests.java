package algorithm;

import edu.brown.cs.student.algorithm.Search2Robots;
import edu.brown.cs.student.algorithm.SearchAllRobots;
import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.generator.boards.RicoRobotsBoard;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.Target;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmTests {

  /**
   * Default Board 1 looks like this:
   * (b/r/y/g is for the target while B/R/Y/G is for the robot)
   *
   *     0 1 2 3 4 5 6 7 8 9 A B C D E F
   *     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
   * 0  |       |g            |  _     y|
   * 1  |           |_          |       |
   * 2  |  _                        |_ G|
   * 3  |   |      _    |_             Y|
   * 4  |         |                    _|
   * 5  |    _|        _|  _|  _        |
   * 6  |_             _ _      |       |
   * 7  |             |   |             |
   * 8  |             |_ _|             |
   * 9  |  _    |_            |_       _|
   * 10 |   |                        _  |
   * 11 |_       _         _          | |
   * 12 |         |  _    |             |
   * 13 |           |                   |
   * 14 |    _|                  _|     |
   * 15 |r _ B R|b _ _ _ _ _ _ _ _ _|_ _|
   */
  @Test
  public void testSearchAllRobotsDefaultBoard1() {
    BoardGenerator boardGenerator = new BoardGenerator();
    Target blueTarget = new Target(Color.Blue, new Coordinate(4, 15));
    Target redTarget = new Target(Color.Red, new Coordinate(0, 15));
    Target yellowTarget = new Target(Color.Yellow, new Coordinate(15, 0));
    Target greenTarget = new Target(Color.Green, new Coordinate(4, 0));
    Target[] targets1 = new Target[]{
        blueTarget,
        redTarget,
        yellowTarget,
        greenTarget,
    };
    Coordinate blueCoordinate = new Coordinate(3, 15);
    Coordinate redCoordinate = new Coordinate(2, 15);
    Coordinate yellowCoordinate = new Coordinate(15, 3);
    Coordinate greenCoordinate = new Coordinate(15, 2);
    Coordinate[] coordinates = new Coordinate[]{
        blueCoordinate,
        redCoordinate,
        yellowCoordinate,
        greenCoordinate,
    };
    RicoRobotsBoard board1 = (RicoRobotsBoard) boardGenerator.createDefaultBoard(targets1, coordinates);

    board1.setTestTarget(blueTarget);
    SearchAllRobots searchAllRobotsBlue = new SearchAllRobots(board1);
    List<Move> moveListSolution = new ArrayList<>();
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Blue, Direction.EAST));
    moveListSolution.add(new Move(Color.Blue, Direction.SOUTH));
    Assert.assertEquals(moveListSolution, searchAllRobotsBlue.getPath());
    moveListSolution.clear();

    board1.setTestTarget(redTarget);
    SearchAllRobots searchAllRobotsRed = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Red, Direction.WEST));
    Assert.assertEquals(moveListSolution, searchAllRobotsRed.getPath());
    moveListSolution.clear();

    board1.setTestTarget(yellowTarget);
    SearchAllRobots searchAllRobotsYellow = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Yellow, Direction.NORTH));
    Assert.assertEquals(moveListSolution, searchAllRobotsYellow.getPath());
    moveListSolution.clear();

    board1.setTestTarget(greenTarget);
    SearchAllRobots searchAllRobotsGreen = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Yellow, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Green, Direction.NORTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    Assert.assertEquals(moveListSolution, searchAllRobotsGreen.getPath());
    moveListSolution.clear();

    board1.setTestTarget(blueTarget);
    SearchAllRobots searchAllRobotsBlueLimited = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Blue, Direction.EAST));
    moveListSolution.add(new Move(Color.Blue, Direction.SOUTH));
    Assert.assertTrue(searchAllRobotsBlueLimited.cappedGetPath(3).isEmpty());
    Assert.assertEquals(moveListSolution, searchAllRobotsBlueLimited.cappedGetPath(4).get());
    Assert.assertEquals(moveListSolution, searchAllRobotsBlueLimited.cappedGetPath(15).get());
    moveListSolution.clear();

    board1.setTestTarget(redTarget);
    SearchAllRobots searchAllRobotsRedLimited = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Red, Direction.WEST));
    Assert.assertTrue(searchAllRobotsRedLimited.cappedGetPath(2).isEmpty());
    Assert.assertEquals(moveListSolution, searchAllRobotsRedLimited.cappedGetPath(3).get());
    Assert.assertEquals(moveListSolution, searchAllRobotsRedLimited.cappedGetPath(6).get());
    moveListSolution.clear();

    board1.setTestTarget(yellowTarget);
    SearchAllRobots searchAllRobotsYellowLimited = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Yellow, Direction.NORTH));
    Assert.assertTrue(searchAllRobotsYellowLimited.cappedGetPath(1).isEmpty());
    Assert.assertEquals(moveListSolution, searchAllRobotsYellowLimited.cappedGetPath(2).get());
    Assert.assertEquals(moveListSolution, searchAllRobotsYellowLimited.cappedGetPath(4).get());
    moveListSolution.clear();

    board1.setTestTarget(greenTarget);
    SearchAllRobots searchAllRobotsGreenLimited = new SearchAllRobots(board1);
    moveListSolution.add(new Move(Color.Yellow, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Green, Direction.NORTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    Assert.assertTrue(searchAllRobotsGreenLimited.cappedGetPath(4).isEmpty());
    Assert.assertEquals(moveListSolution, searchAllRobotsGreenLimited.cappedGetPath(5).get());
    Assert.assertEquals(moveListSolution, searchAllRobotsGreenLimited.cappedGetPath(20).get());
    moveListSolution.clear();
  }

  /**
   * Default Board 1 looks like this:
   * (b/r/y/g is for the target while B/R/Y/G is for the robot)
   *
   *     0 1 2 3 4 5 6 7 8 9 A B C D E F
   *     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
   * 0  |       |g            |  _     y|
   * 1  |           |_          |       |
   * 2  |  _                        |_ G|
   * 3  |   |      _    |_             Y|
   * 4  |         |                    _|
   * 5  |    _|        _|  _|  _        |
   * 6  |_             _ _      |       |
   * 7  |             |   |             |
   * 8  |             |_ _|             |
   * 9  |  _    |_            |_       _|
   * 10 |   |                        _  |
   * 11 |_       _         _          | |
   * 12 |         |  _    |             |
   * 13 |           |                   |
   * 14 |    _|                  _|     |
   * 15 |r _ B R|b _ _ _ _ _ _ _ _ _|_ _|
   */
  @Test
  public void testSearch2RobotsDefaultBoard1() {
    BoardGenerator boardGenerator = new BoardGenerator();
    Target blueTarget = new Target(Color.Blue, new Coordinate(4, 15));
    Target redTarget = new Target(Color.Red, new Coordinate(0, 15));
    Target yellowTarget = new Target(Color.Yellow, new Coordinate(15, 0));
    Target greenTarget = new Target(Color.Green, new Coordinate(4, 0));
    Target[] targets1 = new Target[]{
        blueTarget,
        redTarget,
        yellowTarget,
        greenTarget,
    };
    Coordinate blueCoordinate = new Coordinate(3, 15);
    Coordinate redCoordinate = new Coordinate(2, 15);
    Coordinate yellowCoordinate = new Coordinate(15, 3);
    Coordinate greenCoordinate = new Coordinate(15, 2);
    Coordinate[] coordinates = new Coordinate[]{
        blueCoordinate,
        redCoordinate,
        yellowCoordinate,
        greenCoordinate,
    };
    RicoRobotsBoard board1 = (RicoRobotsBoard) boardGenerator.createDefaultBoard(targets1, coordinates);

    board1.setTestTarget(blueTarget);
    Search2Robots search2RobotsBlue = new Search2Robots(board1);
    List<Move> moveListSolution = new ArrayList<>();
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Blue, Direction.EAST));
    moveListSolution.add(new Move(Color.Blue, Direction.SOUTH));
    Assert.assertEquals(moveListSolution, search2RobotsBlue.getPath());
    moveListSolution.clear();

    board1.setTestTarget(redTarget);
    Search2Robots search2RobotsRed = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Red, Direction.WEST));
    Assert.assertEquals(moveListSolution, search2RobotsRed.getPath());
    moveListSolution.clear();

    board1.setTestTarget(yellowTarget);
    Search2Robots search2RobotsYellow = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Yellow, Direction.NORTH));
    Assert.assertEquals(moveListSolution, search2RobotsYellow.getPath());
    moveListSolution.clear();

    board1.setTestTarget(greenTarget);
    Search2Robots search2RobotsGreen = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Yellow, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Green, Direction.NORTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    Assert.assertEquals(moveListSolution, search2RobotsGreen.getPath());
    moveListSolution.clear();

    board1.setTestTarget(blueTarget);
    Search2Robots search2RobotsBlueLimited = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Blue, Direction.EAST));
    moveListSolution.add(new Move(Color.Blue, Direction.SOUTH));
    Assert.assertTrue(search2RobotsBlueLimited.cappedGetPath(3).isEmpty());
    Assert.assertEquals(moveListSolution, search2RobotsBlueLimited.cappedGetPath(4).get());
    Assert.assertEquals(moveListSolution, search2RobotsBlueLimited.cappedGetPath(15).get());
    moveListSolution.clear();

    board1.setTestTarget(redTarget);
    Search2Robots search2RobotsRedLimited = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Blue, Direction.WEST));
    moveListSolution.add(new Move(Color.Blue, Direction.NORTH));
    moveListSolution.add(new Move(Color.Red, Direction.WEST));
    Assert.assertTrue(search2RobotsRedLimited.cappedGetPath(2).isEmpty());
    Assert.assertEquals(moveListSolution, search2RobotsRedLimited.cappedGetPath(3).get());
    Assert.assertEquals(moveListSolution, search2RobotsRedLimited.cappedGetPath(6).get());
    moveListSolution.clear();

    board1.setTestTarget(yellowTarget);
    Search2Robots search2RobotsYellowLimited = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Yellow, Direction.NORTH));
    Assert.assertTrue(search2RobotsYellowLimited.cappedGetPath(1).isEmpty());
    Assert.assertEquals(moveListSolution, search2RobotsYellowLimited.cappedGetPath(2).get());
    Assert.assertEquals(moveListSolution, search2RobotsYellowLimited.cappedGetPath(4).get());
    moveListSolution.clear();

    board1.setTestTarget(greenTarget);
    Search2Robots search2RobotsGreenLimited = new Search2Robots(board1);
    moveListSolution.add(new Move(Color.Yellow, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.SOUTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    moveListSolution.add(new Move(Color.Green, Direction.NORTH));
    moveListSolution.add(new Move(Color.Green, Direction.WEST));
    Assert.assertTrue(search2RobotsGreenLimited.cappedGetPath(4).isEmpty());
    Assert.assertEquals(moveListSolution, search2RobotsGreenLimited.cappedGetPath(5).get());
    Assert.assertEquals(moveListSolution, search2RobotsGreenLimited.cappedGetPath(20).get());
    moveListSolution.clear();
  }

}
