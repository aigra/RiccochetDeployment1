package generator;

import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.Square;
import org.junit.Assert;
import org.junit.Test;

public class QuadrantTest {

  @Test
  public void testValidateQuadrant() {

    // 1x1 with NW walls and target
    Assert.assertTrue(TestStorage.testQuad0.validateQuadrant(1));

    // 2x2 no walls, should be false because north and west edge walls are required
    Assert.assertFalse(TestStorage.twoEmpty.validateQuadrant(2));

    //2x2 outer walls
    Assert.assertTrue(TestStorage.twoOuterWalls.validateQuadrant(2));

    //2x2 inner wall and outer walls
    Assert.assertTrue(TestStorage.twoInnerWalls.validateQuadrant(2));

    //2x2 malformed inner wall and outer walls
    Assert.assertFalse(TestStorage.malformedInnerWall.validateQuadrant(2));

    // 3x3 outer walls only
    Assert.assertTrue(TestStorage.threeOuterWalls.validateQuadrant(3));

  }

  @Test
  public void testCopy() {

    // Create both a referential and non-referential copy of testQuad1
    Quadrant referential = TestStorage.testQuad1;
    Quadrant nonReferential = Quadrant.copy(TestStorage.testQuad1);

    // Check that both referential and non-referential copies are initially equal to the base
    Assert.assertTrue(referential.equals(TestStorage.testQuad1));
    Assert.assertTrue(nonReferential.equals(TestStorage.testQuad1));

    // After making a change to the base, check that the referential copy remains equal to it while
    // the non-referential copy does not
    TestStorage.testQuad1.setQuad0(new Square[]{});
    Assert.assertTrue(referential.equals(TestStorage.testQuad1));
    Assert.assertFalse(nonReferential.equals(TestStorage.testQuad1));

  }

  @Test
  public void testRotate() {

    // 3x3 outer walls only, no targets, should be the same when rotated
    Assert.assertTrue(TestStorage.threeOuterWalls.equals(
        Quadrant.rotate(TestStorage.threeOuterWalls, 1)));

    // 3x3 outer walls only, with target, should not be the same when rotated (due to targets)
    Assert.assertFalse(TestStorage.threeOuterWallsTarget.equals(
        Quadrant.rotate(TestStorage.threeOuterWallsTarget, 1)));

    // 3x3 outer walls only, with target, should adjust target location when rotated
    Assert.assertTrue(TestStorage.threeOuterWallsRotatedTarget.equals(
        Quadrant.rotate(TestStorage.threeOuterWallsTarget, 1)));

    // 3x3 outer walls only, with center target, should be the same when rotated
    Assert.assertTrue(TestStorage.threeOuterWallsCenterTarget.equals(
        Quadrant.rotate(TestStorage.threeOuterWallsCenterTarget, 1)));

    // 2x2 NW walls should be 2x2 SE walls when rotated twice, checks that walls properly rotate and
    // that quadrants can be rotated successfully multiple times
    Assert.assertTrue(TestStorage.testQuad2Rotated.equals(
        Quadrant.rotate(TestStorage.testQuad2, 2)));

  }

}
