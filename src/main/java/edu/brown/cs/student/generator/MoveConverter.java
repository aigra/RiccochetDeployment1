package edu.brown.cs.student.generator;

import edu.brown.cs.student.interfaces.Color;
import edu.brown.cs.student.interfaces.Direction;
import edu.brown.cs.student.interfaces.Move;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Class for converting an array of moves from the front end into corresponding backend versions
 */
public class MoveConverter {
  /**
   *Converts a JSONArray of frontend move objects to a backend Move[]
   * @param jsonArray The frontend representation of moves taken
   * @return The Move[] corresponding to the frontend array.
   */
  public static Move[] getMoves(JSONArray jsonArray) {
    Move[] finalMoves = new Move[jsonArray.length()];
    try {
      for (int i = 0; i < finalMoves.length; i++) {
        JSONObject move = jsonArray.getJSONObject(i);
        Color color = Color.valueOf(move.get("Color").toString());
        Direction direction = Direction.valueOf(move.get("Direction").toString());
        finalMoves[i] = new Move(color, direction);
      }
      return finalMoves;
    } catch (JSONException | IllegalArgumentException e) {
      System.out.println("Improper move submission: " + jsonArray);
      return new Move[0];
    }
  }

  /**
   * Converts a backend representation of a Move[] into a frontend usable version
   * @param moves An array of backend move objects
   * @return A JSONArray of those moves
   */
  public static JSONArray getFrontendMoves(Move[] moves) {
    JSONArray finalArray = new JSONArray();
    try {
      for (Move move : moves) {
        JSONObject jsonMove = new JSONObject();
        jsonMove.put("Color", move.robot.toString());
        jsonMove.put("Direction", move.robot.toString());
        finalArray.put(jsonMove);
      }
      return finalArray;
    } catch (JSONException e) {
      System.out.println("Improper move submission");
      return new JSONArray();
    }
  }
}
