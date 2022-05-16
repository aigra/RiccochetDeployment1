package repl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import edu.brown.cs.student.main.Main;
import edu.brown.cs.student.playerManagement.LobbyManager;
import edu.brown.cs.student.playerManagement.PlayerEndpoint;
import edu.brown.cs.student.repl.REPLCmdMap;
import edu.brown.cs.student.repl.REPLCommand;
import org.junit.Test;
import spark.Spark;

import java.net.http.WebSocket;
import java.util.HashSet;
import java.util.Map;

public class LobbyTest {

//  @Test
//  public void testGenerateKeys() {
//    LobbyManager lobby = new LobbyManager();
//    HashSet<Integer> gameCodes = new HashSet<>();
//    for (int i= 0; i < 9000; i++) {
//      gameCodes.add(lobby.createGame());
//    }
//    assertEquals(gameCodes.size(), 9000);
//    //assertThrows(RuntimeException.class, lobby::createGame);
//  }
}
