package edu.brown.cs.student.generator.boards;

import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RicoRobotsBoard implements Board {

  private final Square[][] boardAsSquares;
  private RobotLocations robotLocations;
  private final Target[] targets;
  private Target activeTarget;
  private Set<Target> availableTargets = new HashSet<>();


  public RicoRobotsBoard(Square[][] squares, RobotLocations start, Target[] targets) {
    this.boardAsSquares = squares;
    this.robotLocations = start;
    this.targets = targets;
    this.setActiveTarget();
  }

  @Override
  public RobotLocations getRobotLocations() {
    return this.robotLocations;
  }

  /**
   * Getter for the array of targets.
   * @return The array of targets
   */
  public Target[] getTargets() {
    return this.targets;
  }

  /**
   * Getter for the active target.
   * @return The active target
   */
  public Target getActiveTarget() {
    return this.activeTarget;
  }

  /**
   * Performs the updates necessary to transition to a new round on the active board.  These are
   * choosing a new active target and updating the new initial robot locations to be the end
   * locations of the winning solution from the previous round.
   * @param solution The best verified solution based on which the new starting locations of the
   *                 robots will be determined.
   */
  public void updateBetweenRounds(Move[] solution) {
    // Update the robotLocations of the board to be the end result of the given solution
    // This given solution is assumed to already be verified and official
    this.robotLocations = this.simulateMoves(solution);

    // Set a new active target for use in the next round
    this.setActiveTarget();
  }

  /**
   * Checks if the given solution results in a solved state of the board.
   * @param solution An array of sequential Moves representing a purported solution
   * @return True if executing the moves results in a solved board state, false otherwise
   */
  public boolean verifySolution(Move[] solution) {
    RobotLocations outcome = simulateMoves(solution);
    return this.isSolvedWithLocations(outcome);
  }

  /**
   * Chooses a new Target at random from those that have not yet been used and sets it as the active
   * target.
   */
  private void setActiveTarget() {
    // If you have run out of available targets, reset them to make all targets available again
    // This should never happen after initialization, as the frontend should end the round when it
    //   runs out of targets.
    if (this.availableTargets.size() == 0) {
      this.availableTargets = new HashSet<>(Arrays.asList(this.targets));
    }
    // Pick a Target at random from the available targets
    // Had to do it in this weird way; otherwise the REPLCmdMapTests would cause errors for some
    //   reason.
    Target nextActive = null;
    if (this.availableTargets.size() > 0) {
      // Convert to list to select a random index
      List<Target> availableList = new ArrayList<>(this.availableTargets);
      nextActive = availableList.get((int) Math.floor(Math.random() * availableList.size()));

    }
    // Set it to be the active Target
    this.activeTarget = nextActive;
    // Since it has now been used, remove it from the available targets
    this.availableTargets.remove(nextActive);
  }

  /**
   * Simulates all the Moves in the given array sequentially, starting at the current robotLocations of
   * the board on which it is called
   * @param moves An array of the Move objects to be made
   * @return A RobotLocations object indicating where the robots are after all the given moves have
   * been made
   */
  private RobotLocations simulateMoves(Move[] moves) {
    // It is done this way to avoid updating the robotLocations of the board inadvertently, since this
    // method is not supposed to.
    RobotLocations initial = this.robotLocations;
    RobotLocations current = null;
    int moveIndex = 0;

    // Iterate through every move in the array
    while (moveIndex < moves.length) {
      // If you've already made a move, use its output to make the next move and update current
      if (current != null) {
        current = algorithmMove(current, moves[moveIndex]);
      } else {
        // If no move has been made yet, make the first move using the initial locations
        current = algorithmMove(initial, moves[moveIndex]);
      }
      // Increment the move you're on
      moveIndex++;
    }

    // If there were moves in the array, return the output of the last move
    if (current != null) return current;
    // Otherwise return the initial robotLocations, as no moves were made
    else return initial;
  }

  /**
   * Simulates the given move on the board with the robots starting at the given locations.
   * @param startLocations Starting locations of the robots
   * @param m The Move being made (consisting of color and direction)
   * @return The RobotLocations after the move has been made
   */
  public RobotLocations algorithmMove(RobotLocations startLocations, Move m) {
    assert this.boardAsSquares != null;
    // Extract relevant information from the Move
    RobotLocations newRobotLocations = startLocations.createCopy();


    Coordinate pos = newRobotLocations.locations.get(m.robot);
    Direction dir = m.direction;

    // Check if given robot can move in given direction
    boolean canMove = true;
    switch (dir) {
      case NORTH:
        // Check if there is a wall in given direction
        if (this.boardAsSquares[pos.y][pos.x].northWall) canMove = false;
        // Check if there is a robot in given direction
        if (startLocations.locations.containsValue(new Coordinate(pos.x, pos.y - 1))) canMove = false;
        // Update position accordingly
        if (canMove) pos.y -= 1;
        break;
      case SOUTH:
        if (this.boardAsSquares[pos.y][pos.x].southWall) canMove = false;
        if (startLocations.locations.containsValue(new Coordinate(pos.x, pos.y + 1))) canMove = false;
        if (canMove) pos.y += 1;
        break;
      case EAST:
        if (this.boardAsSquares[pos.y][pos.x].eastWall) canMove = false;
        if (startLocations.locations.containsValue(new Coordinate(pos.x + 1, pos.y))) canMove = false;
        if (canMove) pos.x += 1;
        break;
      case WEST:
        if (this.boardAsSquares[pos.y][pos.x].westWall) canMove = false;
        if (startLocations.locations.containsValue(new Coordinate(pos.x - 1, pos.y))) canMove = false;
        if (canMove) pos.x -= 1;
        break;
      default:
        canMove = false;
    }

    // If the robot can move, commit the updated position and repeat the move
    if (canMove) {
      return this.algorithmMove(newRobotLocations, m);
    } else {
      return startLocations;
    }
  }

  /**
   * Determines if the board is solved by the given RobotLocations, checking if the active target is
   * occupied by the correct color robot.
   * @param loc The RobotLocations object to check
   * @return if the board is solved or not.
   */
  public boolean isSolvedWithLocations(RobotLocations loc) {
    // Check that the board has an active target
    if (this.activeTarget == null) return false;
    // For each color robot on the board
    for (Color robot : loc.locations.keySet()) {
      // Check if the robot color, target color, and robot and target coordinates all match
      Coordinate coordinate = loc.locations.get(robot);
      Coordinate target = this.activeTarget.coordinate;

      if (robot.equals(this.activeTarget.color) && coordinate.x == target.x && coordinate.y == target.y) {
        // If so, return true
        return true;
      }
    }
    // If the correct robot is not on the active target, return false
    return false;
  }

  @Override
  public Square[][] getBoard() {
    return this.boardAsSquares;
  }



  public void setTestTarget(Target target) {
    this.activeTarget = target;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof RicoRobotsBoard) {
      RicoRobotsBoard b = (RicoRobotsBoard) o;
      Quadrant q1 = new Quadrant(this.boardAsSquares, this.targets);
      Quadrant q2 = new Quadrant(b.getBoard(), b.getTargets());
      return (q1.equals(q2) && this.robotLocations.equals(b.getRobotLocations()));
    } else {
      return false;
    }
  }

}
