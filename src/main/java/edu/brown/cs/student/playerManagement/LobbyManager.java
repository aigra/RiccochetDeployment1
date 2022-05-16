package edu.brown.cs.student.playerManagement;


import java.util.HashMap;
import java.util.Random;
import org.eclipse.jetty.websocket.api.Session;

/**
 * Class which manages all games currently being played on the site.
 * WARNING: CURRENTLY NOT THREAD SAFE, ALTHOUGH IT SHOULD BE
 */
public class LobbyManager {
  HashMap<Integer, Game> allGames = new HashMap<>();
  final Random random = new Random();
  final int MINCODE = 1000;
  final int MAXCODE = 10000;


  /**
   * Generates a unique random key for a new game.
   * Note, numbers that come after one or more games will be slightly more common
   * Also will take a long time for high numbers of active games.
   * @return a random ID for the game that is unique.
   */
  private int generateID() {
    int randomVal = random.nextInt(MAXCODE - MINCODE) + MINCODE;
    while (allGames.containsKey(randomVal)) {
      randomVal = (randomVal + 1);
      if (randomVal == MAXCODE) {
        randomVal = MINCODE;
      }
    }
    return randomVal;
  }

  /**
   * Creates a new game that people can play
   * @return the game code of the game that was created
   * @throws RuntimeException If all games are full
   */
  public int createGame() throws RuntimeException {
    if (allGames.size() == MAXCODE - MINCODE) {
      throw new RuntimeException("ALL GAMES FULL");
    }
    int gameCode = generateID();
    Game newGame = new Game(gameCode);
    allGames.put(gameCode, newGame);
    return gameCode;
  }

  //TEST ONLY
  public int createGame(int timer) throws RuntimeException {
    if (allGames.size() == MAXCODE - MINCODE) {
      throw new RuntimeException("ALL GAMES FULL");
    }
    int gameCode = generateID();
    Game newGame = new Game(gameCode, timer);
    allGames.put(gameCode, newGame);
    return gameCode;
  }

  public int createGame(boolean BFS, boolean LimitBFS) throws RuntimeException {
    if (allGames.size() == MAXCODE - MINCODE) {
      throw new RuntimeException("ALL GAMES FULL");
    }
    int gameCode = generateID();
    Game newGame = new Game(gameCode, BFS, LimitBFS);
    allGames.put(gameCode, newGame);
    return gameCode;
  }

  /**
   * Joins a game by adding a players username and network connection
   * @param gameCode the code the player is using to connect to the game
   * @param username the players username
   * @param connection the players websocket session
   * @return the game object the player joined
   * @throws IllegalArgumentException if the code does not have an active game
   */
  public Game joinGame(int gameCode, String username, Session connection)
      throws IllegalArgumentException {
    Game myGame = allGames.get(gameCode);
    if (myGame == null) {
      throw new IllegalArgumentException("game not found");
    } else {
      myGame.addPlayer(username, connection);
      return myGame;
    }
  }

  /**
   * Given a game code, returns that game object
   * @param gameCode the code to join the game
   * @return the associated game object
   * @throws IllegalArgumentException if the item is not found
   */
  public Game findGame(int gameCode) throws IllegalArgumentException {
    Game myGame = allGames.get(gameCode);
    if (myGame == null) {
      throw new IllegalArgumentException("game not found");
    }
    return myGame;
  }

  /**
   * Called when a player disconnects from the websocket
   * @param gameCode the game code they were disconnected to
   */
  public void exitGame(int gameCode) {
    Game myGame = allGames.get(gameCode);
    if (myGame.closeSessions()) {
      allGames.remove(gameCode);
    }
  }
}
