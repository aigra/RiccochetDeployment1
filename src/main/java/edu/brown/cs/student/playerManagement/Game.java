package edu.brown.cs.student.playerManagement;


import com.google.gson.Gson;
import edu.brown.cs.student.algorithm.Search2Robots;
import edu.brown.cs.student.algorithm.SearchAllRobots;
import edu.brown.cs.student.generator.BoardConverter;
import edu.brown.cs.student.generator.BoardGenerator;
import edu.brown.cs.student.interfaces.Algorithm;
import edu.brown.cs.student.interfaces.Board;
import edu.brown.cs.student.interfaces.Coordinate;
import edu.brown.cs.student.interfaces.Move;
import edu.brown.cs.student.interfaces.RobotLocations;
import edu.brown.cs.student.interfaces.Square;
import edu.brown.cs.student.interfaces.Target;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The main logic of running a game happens in this class.
 */
public class Game {
  int gameCode;
  LinkedList<String> playerList;
  LinkedList<Session> sessions;
  Move[] shortestPath;
  Date endTime;
  int timerLength = 10;
  Board currBoard = new BoardGenerator().createBoardFromQuadrants();
  boolean gameStarted = false;
  Scoreboard shortestPaths = new Scoreboard();
  public Scoreboard overallScores = new Scoreboard();
  public boolean BFS = false;
  public boolean alexMethod = false;
  private List<Algorithm> algoThreads = new LinkedList<>();
  private static final Gson gson = new Gson();


  /**
   * Creates a new game that players can join.
   * @param lobbyCode the lobby number of this game
   */
  public Game(int lobbyCode) {
    gameCode = lobbyCode;
    playerList = new LinkedList<>();
    sessions = new LinkedList<>();
  }

  /**
   * Creates a new game that players can join.
   * @param lobbyCode the lobby number of this game
   */
  public Game(int lobbyCode, boolean BFS, boolean limitBFS) {
    gameCode = lobbyCode;
    playerList = new LinkedList<>();
    sessions = new LinkedList<>();
    this.BFS = BFS;
    this.alexMethod = limitBFS;
  }

  /**
   * Creates a new game that players can join.
   * @param lobbyCode the lobby number of this game
   * @param timerLength how long the timer should be for this game
   */
  public Game(int lobbyCode, int timerLength) {
    gameCode = lobbyCode;
    playerList = new LinkedList<>();
    sessions = new LinkedList<>();
    this.timerLength = timerLength;
  }

  //How should we handle multiple players entering the same username? We could have it not be allowed,
  //or we could let two people have the same username to let someone reconnect if they are disconnected.

  /**
   * Allows a user to enter a game, updates all players if they have a
   * @param username the new player to enter the game
   * @param connection the connection that will be used to send messages to this player
   */
  public void addPlayer(String username, Session connection) {
    sessions.addLast(connection);
    playerList.add(username);
    if (gameStarted) {
      try {
        connection.getRemote().sendString(gson.toJson(new joinOngoingMessage(), joinOngoingMessage.class));
      } catch (IOException e) {
        System.out.println(e.getMessage());
        System.out.println("Unable to join ongoing game");
      }
    } else {
      sendAll(gson.toJson(new lobbyMessage(), lobbyMessage.class));
    }
  }

  /**
   * Moves a game from waiting to start into the playing state.
   */
  public void startGame() {
    gameStarted = true;
    sendAll(gson.toJson(new beginGameMessage(), beginGameMessage.class));
    startAlgos();
  }

  /**
   * Method for a player or algorithm to submit a solution by stating how many moves it takes to
   * solve. Cannot verify whether this claim is accurate.
   * @param username The name of the player or algorithm which submits the solution.
   * @param number_of_moves The number of moves it the solution is.
   */
  public void submitSolution(String username, int number_of_moves) {
    if (shortestPaths.insert(username, number_of_moves)) {
      if (endTime == null) {
        endTime = new Date(new Date().getTime() + timerLength * 1000L);
        Timer timer = new Timer(this, endTime);
        Thread timerThread = new Thread(timer);
        timerThread.start();
      }
      sendAll(gson.toJson(new scoresMessage(), scoresMessage.class));
    }
  }

  /**
   * Method for a player or algorithm to submit a solution by giving an array of moves which
   * solves the current board.
   * @param username The name of the player or algorithm which submits the solution.
   * @param path The array of moves representing the solution.
   */
  public void submitSolution(String username, Move[] path) {
    if (currBoard.verifySolution(path)) {
      shortestPath = path;
      if (shortestPaths.insert(username, path.length)) {
        if (endTime == null) {
          endTime = new Date(new Date().getTime() + timerLength * 1000L);
          Timer timer = new Timer(this, endTime);
          Thread timerThread = new Thread(timer);
          timerThread.start();
        }
        sendAll(gson.toJson(new scoresMessage(), scoresMessage.class));
      }
    } else {
      System.out.println(username + " submitted illegal solution:\n" + Arrays.toString(path));
    }
  }

  /**
   * Ends a round in the game.
   */
  public void endRound() {
    System.out.println("End round called");
    stopAlgos();
    String winner = shortestPaths.getFirst();
    overallScores.increase(winner);
    currBoard.updateBetweenRounds(shortestPath);
    sendAll(gson.toJson(new endRoundMessage(), endRoundMessage.class));
    endTime = null;
    shortestPaths = new Scoreboard();
    startAlgos();
  }

  /**
   * Method to send a string message to all open connections in this game
   * @param message The message that should be sent
   */
  public void sendAll(String message) {
    System.out.println(message);
    for (Session s : sessions) {
      if (s.isOpen()) {
        try {
          s.getRemote().sendString(message);
        } catch (IOException e) {
          System.out.println("Error occurred while sending");
          System.out.println(e.getMessage());
        }
      }
    }
  }

  /**
   * Clears all closed connections
   * @return true if all sessions are closed, false otherwise
   */
  public boolean closeSessions() {
    LinkedList<Session> connectionList = new LinkedList<>();
    for (Session connection : sessions) {
      if (connection.isOpen()) {
        connectionList.addLast(connection);
      }
    }
    sessions = connectionList;
    return sessions.isEmpty();
  }

  /**
   * Called for a player exiting the game, either by connection termination or leaving
   * on their own.
   * @param user The connection that is exiting the game.
   * @param username The username of the player that is leaving the game.
   * @return true if there are no more open connections, false otherwise
   */
  public boolean leaveGame(Session user, String username) {
    sessions.remove(user);
    playerList.remove(username);
    if (!closeSessions()) {
      try {
        sessions.getFirst().getRemote().sendString(gson.toJson(new hostMessage()));
        sendAll(gson.toJson(new scoresMessage(), scoresMessage.class));
      } catch (IOException e) {
        System.out.println("error leaving game");
        System.out.println(e.getMessage());
      }
      return false;
    }
    return true;
  }

  public void startAlgos() {
    algoThreads = new LinkedList<>();
    if (BFS) {
      Algorithm BFSAlgorithm = new SearchAllRobots(currBoard);
      algoThreads.add(BFSAlgorithm);
      Runnable BFSRunner = new AlgorithmRunner(BFSAlgorithm, "BFS", this);
      new Thread(BFSRunner).start();
    }
    if (alexMethod) {
      Algorithm AlexAlgorithm = new Search2Robots(currBoard);
      algoThreads.add(AlexAlgorithm);
      Runnable BFSRunner = new AlgorithmRunner(AlexAlgorithm, "AlexRobot", this);
      new Thread(BFSRunner).start();
    }
  }

  public void stopAlgos() {
    for (Algorithm algo : algoThreads) {
      algo.killThread();
    }
  }

  /**
   * Class used to send an endTime if one exists
   * @param d any Date
   * @return The dates time if it's non-null, 0 otherwise
   */
  private long getTime(Date d) {
    if (d == null) {
      return 0;
    } else {
      return d.getTime();
    }
  }

  /**
   * Class used to create a message that will be sent to all players containing the players in the lobby.
   */
  public class lobbyMessage {
    String type = "lobby";
    int game_code = gameCode;
    String[] player_list = playerList.toArray(new String[0]);
  }

  /**
   * Class used to create a message that will be sent to all players containing the scores for that.
   * round.
   */
  public class scoresMessage {
    String type = "scores";
    Score[] scores = shortestPaths.getLowestScores();
    long end_time = getTime(endTime);
  }

  /**
   * Class used to create a message that ends a round.
   */
  public class endRoundMessage {
    String type = "end_round";
    Move[] path = shortestPath;
    Score[] overall_leaderboard = overallScores.getHighestScores();
    Map<String, Coordinate> robot_locations = currBoard.getRobotLocations().stringLocations();
    Coordinate active_target = currBoard.getActiveTarget().coordinate;
    String target_color = currBoard.getActiveTarget().color.toString();
    String target_shape = getTargetShape(active_target);
    boolean game_over = false;
  }

  /**
   * Class used to create a message that begins the playable part of the game.
   */
  public class beginGameMessage {
    String type = "begin_game";
    Square[][] board = BoardConverter.convertBoard(currBoard);
    Map<String, Coordinate> robot_locations = currBoard.getRobotLocations().stringLocations();
    Coordinate active_target = currBoard.getActiveTarget().coordinate;
    String target_color = currBoard.getActiveTarget().color.toString();
    String target_shape = getTargetShape(active_target);
  }

  private String getTargetShape(Coordinate coords) {
    return currBoard.getBoard()[coords.y][coords.x].targetShape;
  }

  /**
   * Class used for a player who joins the game once it has begun.
   */
  public class joinOngoingMessage {
    String type = "join_ongoing";
    Square[][] board = BoardConverter.convertBoard(currBoard);
    Target target = currBoard.getActiveTarget();
    RobotLocations robots = currBoard.getRobotLocations();
    Score[] scores = shortestPaths.getLowestScores();
    Score[] overall_leaderboard = overallScores.getHighestScores();
    long end_time = getTime(endTime);
  }

  /**
   * Class used to make a connection the new host.
   */
  public class hostMessage {
    String type = "host";
  }
}
