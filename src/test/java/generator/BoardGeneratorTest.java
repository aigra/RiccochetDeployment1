package generator;

import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.generator.boards.BoardStorage;
import edu.brown.cs.student.generator.boards.RicoRobotsBoard;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class BoardGeneratorTest {

  @Test
  public void testStringsToSquares() {

    // Single square, no walls
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {""}
    }), new Square[][]{
        {new Square(false, false, false, false, 0, 0)}
    });

    // Single square, two walls
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {"nw"}
    }), new Square[][]{
        {new Square(true, false, false, true, 0, 0)}
    });

    // Single square, four walls
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {"nesw"}
    }), new Square[][]{
        {new Square(true, true, true, true, 0, 0)}
    });

    // 2x2 outer walls
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {"nw","ne"},
        {"sw","se"}
    }), new Square[][]{
        {new Square(true, false, false, true, 0, 0),
            new Square(true, false, true, false, 1, 0)},
        {new Square(false, true, false, true, 0, 1),
            new Square(false, true, true, false, 1, 1)},
    });

    // 2x2 outer walls, one inner wall
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {"nwe","nwe"},
        {"sw","se"}
    }), new Square[][]{
        {new Square(true, false, true, true, 0, 0),
            new Square(true, false, true, true, 1, 0)},
        {new Square(false, true, false, true, 0, 1),
            new Square(false, true, true, false, 1, 1)},
    });

    // 2x2 outer walls, malformed inner wall (not expecting it to get caught here)
    Assert.assertEquals(BoardGenerator.stringsToSquares(new String[][]{
        {"nwe","ne"},
        {"sw","se"}
    }), new Square[][]{
        {new Square(true, false, true, true, 0, 0),
            new Square(true, false, true, false, 1, 0)},
        {new Square(false, true, false, true, 0, 1),
            new Square(false, true, true, false, 1, 1)},
    });

  }

  @Test
  public void testVerifyBoard() {

    // 1x1 well-formed
    Assert.assertTrue(BoardGenerator.verifyBoard(TestStorage.singleWellFormed, 1));

    // 1x1 well-formed, should be false due to improper size (16x16 required)
    Assert.assertFalse(BoardGenerator.verifyBoard(TestStorage.singleWellFormed, 16));

    // 16x16 well-formed empty
    Assert.assertTrue(BoardGenerator.verifyBoard(TestStorage.properWellFormed, 16));

    // 16x16 missing outer wall
    Assert.assertFalse(BoardGenerator.verifyBoard(TestStorage.properMalformed, 16));

    // 16x16 well-formed with inner walls
    BoardGenerator gen = new BoardGenerator();
    Assert.assertTrue(BoardGenerator.verifyBoard(gen.createDefaultBoard(new Target[]{}, new Coordinate[] {
        new Coordinate(1, 1),
        new Coordinate(0, 1),
        new Coordinate(1, 0),
        new Coordinate(0, 0)
    }), 16));

    // 16x16 malformed inner wall
    Assert.assertFalse(BoardGenerator.verifyBoard(TestStorage.missingInnerWall, 16));

    // Well-formed but one row is 17 long
    Assert.assertFalse(BoardGenerator.verifyBoard(TestStorage.inconsistentRowSize, 16));

  }

  @Test
  public void testStitchQuadrants() {

    BoardGenerator gen = new BoardGenerator();

    // Test that basic corner quadrants can be combined into 2x2 board, maintaining target in
    // the top left
    Board test1 = gen.stitchSpecificQuadrants(TestStorage.testQuad1, TestStorage.testQuad2,
        TestStorage.testQuad2, TestStorage.testQuad2);
    Board test1A = new RicoRobotsBoard(BoardGenerator.stringsToSquares(new String[][]{
        {"nw","ne"},
        {"sw","se"}
    }), new RobotLocations(new HashMap<>()), new Target[]{
        new Target(Color.Red, new Coordinate(0, 0))
    });
    Assert.assertTrue(test1.equals(test1A));

    // Test the same, but with the target being rotated into the bottom right
    Board test2 = gen.stitchSpecificQuadrants(TestStorage.testQuad2, TestStorage.testQuad2,
        TestStorage.testQuad2, TestStorage.testQuad1);
    Board test2A = new RicoRobotsBoard(BoardGenerator.stringsToSquares(new String[][]{
        {"nw","ne"},
        {"sw","se"}
    }), new RobotLocations(new HashMap<>()), new Target[]{
        new Target(Color.Red, new Coordinate(1, 1))
    });
    Assert.assertTrue(test2.equals(test2A));

    // Test that walls existing on the edge of a quadrant are properly "smoothed"; i.e. a wall is
    // inserted in the adjacent square to it once the quadrants are stitched to ensure that the
    // resulting board is well formed
    Board test3 = gen.stitchSpecificQuadrants(TestStorage.testQuad3, TestStorage.testQuad2,
        TestStorage.testQuad1, TestStorage.testQuad1);
    Board test3A = new RicoRobotsBoard(BoardGenerator.stringsToSquares(new String[][]{
        {"nwe","new"},
        {"sw","se"}
    }), new RobotLocations(new HashMap<>()), new Target[]{
        new Target(Color.Red, new Coordinate(0, 1)),
        new Target(Color.Red, new Coordinate(1, 1))
    });
    Assert.assertTrue(test3.equals(test3A));

  }

}