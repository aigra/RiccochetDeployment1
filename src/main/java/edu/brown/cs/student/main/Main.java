package edu.brown.cs.student.main;

import edu.brown.cs.student.playerManagement.PlayerEndpoint;
import edu.brown.cs.student.repl.REPL;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;


/**
 * The Main class of our project. This is where execution begins.
 *
 */

public final class Main {

  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
	  
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    
	if (options.has("gui")) {
		runSparkServer((int) options.valueOf("port"));
	}
    REPL r = new REPL();
    r.repl();
  }

  /**
   * Starts the spark server
   * @param port The port spark will run on
   */
  public static void runSparkServer(int port) {
      Spark.port(port);
      Spark.externalStaticFileLocation("src/main/resources/static");
      Spark.webSocket("/connect", PlayerEndpoint.class);
      Spark.init();
      
//      Spark.options("/*", (request, response) -> {
//          String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
//          if (accessControlRequestHeaders != null) {
//            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
//          }
//
//          String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
//
//          if (accessControlRequestMethod != null) {
//            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
//          }
//
//          return "OK";
//        });
//
//        Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
//        LobbyManager lobby = new LobbyManager();
  //      Spark.get("/create", new CreateHandler(lobby));
  //  Spark.staticFileLocation("/public"); //index.html is served at localhost:4567 (default port)


        
        // Put Routes Here
        // Spark.get("/table", new TableHandler());

    }
}
