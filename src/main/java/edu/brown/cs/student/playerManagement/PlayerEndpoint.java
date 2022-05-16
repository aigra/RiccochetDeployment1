package edu.brown.cs.student.playerManagement;

import edu.brown.cs.student.generator.MoveConverter;
import edu.brown.cs.student.interfaces.Move;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;
import org.eclipse.jetty.websocket.api.Session;

@WebSocket
/**
 * Handles websocket connections to the backend.
 */
public class PlayerEndpoint {
  private Game game = null;
  public static final LobbyManager lobby = new LobbyManager();


  /**
   * Method to be called on connection to the websocket. Currently does nothing,
   * as no backend code needs to run when a player loads up the webpage
   * @param user
   * @throws Exception
   */
  @OnWebSocketConnect
  public void onConnect(Session user) throws Exception {

  }

  /**
   * Method to be called when a disconnects from websocket.
   * Clears their connection from the current game, and clears games from active if they were
   * the only active connection.
   * @param statusCode status sent over from the disconnect
   * @param reason the reason the connection was terminated
   */
  @OnWebSocketClose
  public void onClose(int statusCode, String reason) {
    if (game != null) {
      lobby.exitGame(game.gameCode);
    }
  }

  /**
   * Handles an incoming message, first by figuring out what type it is than processing
   * it appropriately.
   * @param connection The websocket connection the message was sent over
   * @param message The string contents of the message
   */
  @OnWebSocketMessage
  public void onMessage(Session connection, String message) {
    System.out.println(message);
    try {
      JSONObject json = new JSONObject(message);
      String type = json.getString("type");
      String username;
      switch (type) {
        case "start_game":
          game.startGame();
          break;
        case "create":
          try {
            username = json.getString("username");
            int code = lobby.createGame(json.getBoolean("BFS"), json.getBoolean("Limit_BFS"));
            game = lobby.joinGame(code, username, connection);
          } catch (JSONException e) {
            username = json.getString("username");
            int code = lobby.createGame();
            game = lobby.joinGame(code, username, connection);
          }
          break;
        case "join":
          username = json.getString("username");
          game = lobby.joinGame(json.getInt("game_code"), username, connection);
          break;
        case "submit":
          username = json.getString("username");
          try {
            game.submitSolution(username, json.getInt("number_of_moves"));
          } catch (JSONException e) {
            game.submitSolution(username, MoveConverter.getMoves(json.getJSONArray("path")));
          }
          break;
        case "leave_lobby":
          username = json.getString("username");
          if (game.leaveGame(connection, username)) {
            lobby.exitGame(game.gameCode);
          }
          break;
        case "next_round":
          System.out.println("next_round not yet supported");
        default: System.out.println(type + " not supported");
      }
    } catch (JSONException e) {
      System.out.println("Improper Args");
      System.out.println(e.getMessage());
    }
  }
}