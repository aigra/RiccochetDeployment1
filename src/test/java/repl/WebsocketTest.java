//package repl;
//
//import edu.brown.cs.student.main.Main;
//import edu.brown.cs.student.playerManagement.LobbyManager;
//import edu.brown.cs.student.playerManagement.PlayerEndpoint;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.WebSocket;
//import java.time.Duration;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletionStage;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class WebsocketTest {
//  private static final int TIMEOUT = 60;
//  private JSONObject message = new JSONObject();
//
//  @Before
//  public void setup() {
//    Main.runSparkServer(4567);
//  }
//
//  @Test
//  public void testCreate() {
//    try {
//      HttpClient client1 = HttpClient.newBuilder()
//          .connectTimeout(Duration.ofSeconds(TIMEOUT))
//          .version(HttpClient.Version.HTTP_2)
//          .build();
//
//      WebSocket.Listener listener1 = new WebSocket.Listener() {
//        int messageNum = 0;
//        @Override
//        public void onOpen(WebSocket webSocket) {
//          WebSocket.Listener.super.onOpen(webSocket);
//        }
//
//        @Override
//        public CompletionStage<WebSocket> onText(WebSocket webSocket, CharSequence chars, boolean last) {
//          try {
//            System.out.println("Message recieved" + chars);
//            message = new JSONObject(chars.toString());
//          } catch (Exception e) {
//            System.out.println("FAILURE");
//          }
//          notifyAll();
//          return null;
//        }
//
//      };
//
//      URI uri = new URI("ws://localhost:4567/connect");
//
//      WebSocket socket1 = client1.newWebSocketBuilder().buildAsync(uri, listener1).get();
////      CompletableFuture<WebSocket>
////          create = socket1.sendText("{type: 'create', username: 'aaron'}", true);
////      Thread.sleep(500);
////      Assert.assertEquals(message.getString("type"), "lobby");
////      socket1.sendText("{type: 'submit', username: 'aaron', number_of_moves: 5}", true);
//      LobbyManager generalLobby = PlayerEndpoint.lobby;
//      int lobbyCode = generalLobby.createGame(2);
//      CompletableFuture<WebSocket> future = socket1.sendText("{type: 'join', username: 'aaron', game_code: " + lobbyCode + "}", true);
//      future.wait();
////      wait();
////      socket1.sendText("{type: 'submit', username: 'aaron', number_of_moves: 5}", true);
////      wait();
//      Assert.assertTrue(true);
////      System.out.println(message.getString("type"));
////      Assert.assertEquals(message.getString("type"), "end_round");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      e.printStackTrace();
//    }
//  }
//}
