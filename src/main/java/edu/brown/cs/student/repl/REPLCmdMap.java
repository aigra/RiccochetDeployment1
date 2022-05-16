package edu.brown.cs.student.repl;

import edu.brown.cs.student.generator.commands.GenerateRandom;
import edu.brown.cs.student.generator.commands.PrintDefault;
import edu.brown.cs.student.generator.commands.PrintEmpty;
import edu.brown.cs.student.generator.commands.PrintQuadrants;
import edu.brown.cs.student.generator.commands.TestDefaultBoard;
import edu.brown.cs.student.generator.commands.ValidateQuadrants;
import edu.brown.cs.student.generator.commands.VerifyDefault;
import edu.brown.cs.student.generator.commands.VerifyEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
///**
// * Creates a hashmap of REPL commands engineers want REPL support for, as well as stores any
// * data types that need to be shared between multiple REPL commands.
// *
// * @author Elizabeth Wu
// */
public class REPLCmdMap {
  private final Map<String, REPLCommand> allCommands; // map that stores REPL commands
//  // Engineers should add data structures that they need shared by multiple commands here!
//  // kdTree fields
//  private KDTree kd;
//  // bloom filter fields
//  private BloomFilter bf;
//  private double bfPositivityRate;
//  private int bfNumElts;
//  private Map<String, BloomFilter> bfMap;
//  // stars fields
//  private List<Star> starLs;
//  // API fields
//  private RequestHandler handler = new RequestHandler(new ApiClient());
//  // Database fields
//  private List<DatabaseStudent> dbStudentList; // #### not necessary
//  private DatabaseProxy proxy;
//  private Map<String, String> dbProxyPermissions;
//  // Recommender fields
//  private Map<String, String> headerTypes;
//  private Map<Integer, List<Pair<String, String>>> id2Data;
//  private List<RecommenderInsertable> studentLs;
//
//  /** Constructs a REPLCmdMap and adds commands to the allCommands hashmap field. */
  public REPLCmdMap() {
    this.allCommands = new HashMap<String, REPLCommand>();
//    // Engineers should edit this constructor to add their own REPL commands to the allCommands map
//    CreateBF cbf = new CreateBF(this);
//    InsertBF ibf = new InsertBF(this);
//    QueryBF qbf = new QueryBF(this);
//    SimilarKD skd = new SimilarKD(this);
//    LoadKD lkd = new LoadKD(this);
//    LoadBF lbf = new LoadBF(this);
//    StarsCmd stars = new StarsCmd(this);
//    Neighbors nn = new Neighbors(this);
//    SimilarBF sbf = new SimilarBF(this);
//    HeadersLoad hl = new HeadersLoad(this);
//    RecsysCmd rs = new RecsysCmd(this);
//    ActiveAPI aApi = new ActiveAPI(this);
//    RequestApi api = new RequestApi(this);
//    Aggregate agg = new Aggregate(this);
//    CreateProxy initProxy = new CreateProxy(this, this.dbProxyPermissions);
//    QueryProxy queryProxy = new QueryProxy(this);
//    CreateDBStudent createDBStudent = new CreateDBStudent(this);
//    RecommendCmd rec = new RecommendCmd(this);
//    ReadFile readDB = new ReadFile();
//    allCommands.put("create_bf", cbf);
//    allCommands.put("insert_bf", ibf);
//    allCommands.put("query_bf", qbf);
//    allCommands.put("load_kd", lkd);
//    allCommands.put("similar_kd", skd);
//    allCommands.put("load_bf", lbf);
//    allCommands.put("stars", stars);
//    allCommands.put("naive_neighbors", nn);
//    allCommands.put("similar_bf", sbf);
//    allCommands.put("headers_load", hl);
//    allCommands.put("recsys_load", rs);
//    allCommands.put("active", aApi);
//    allCommands.put("api", api);
//    allCommands.put("api_aggregate", agg);
//    allCommands.put("load_db", initProxy);
//    allCommands.put("query_db", queryProxy);
//    allCommands.put("create_db_students", createDBStudent);
//    allCommands.put("recommend", rec);
//    allCommands.put("read_file", readDB);
//    allCommands.put("setup_backend", new SetupBackend());
//    allCommands.put("api_post", new APIPost());
//    allCommands.put("api_get", new APIGet());
    allCommands.put("print_empty", new PrintEmpty());
    allCommands.put("verify_empty", new VerifyEmpty());
    allCommands.put("print_default", new PrintDefault());
    allCommands.put("verify_default", new VerifyDefault());
    allCommands.put("test_default", new TestDefaultBoard());
    allCommands.put("generate", new GenerateRandom());
    allCommands.put("quad", new PrintQuadrants());
    allCommands.put("validate_quadrants", new ValidateQuadrants());
  }
//  /**
//   * Method to get dbProxyPermissions for creating a DatabaseProxy for a generic database.
//   * @return A map corresponding to the permissions of a database
//   */
//  public Map<String, String> getDBProxyPermissions() {
//    return this.dbProxyPermissions;
//  }
//
//  /**
//   * Method to set dbProxyPermissions when creating a DatabaseProxy for a generic database.
//   * @param dbProxyPermissions dbProxyPermission map for a Database
//   */
//  public void setDbProxyPermissions(Map<String, String> dbProxyPermissions) {
//    this.dbProxyPermissions = dbProxyPermissions;
//  }
//
//  /** Retrieves the map of repl commands.
//   *
//   * @return the allCommands hashmap
//   */
  public Map<String, REPLCommand> getCmdMap() {
    return this.allCommands;
  }
//
//  /** Retrieves the stored bloom filter.
//   *
//   * @return the stored bloom filter
//   */
//  public BloomFilter getBF() {
//    return this.bf;
//  }
//
//  /** Retrieves the stored kd tree.
//   *
//   * @return the stored kd tree
//   */
//  public KDTree getKD() {
//    return this.kd;
//  }
//
//  /** Sets the value of the stored kd tree.
//   *
//   * @param tree the KDTree to set this.kd to
//   */
//  public void setKD(KDTree tree) {
//    this.kd = tree;
//  }
//
//  /** Retrieves the stored positivity rate.
//   *
//   * @return the stored positivity rate
//   */
//  public double getPositivityRate() {
//    return this.bfPositivityRate;
//  }
//
//  /** Retrieves the number of elements for the bloom filter.
//   *
//   * @return the num of elts for the bloom filter
//   */
//  public int getNumElts() {
//    return this.bfNumElts;
//  }
//
//  /** Retrieves the bloom filter hashmap.
//   *
//   * @return the stored bloom filter hashmap
//   */
//  public Map<String, BloomFilter> getBfMap() {
//    return this.bfMap;
//  }
//
//  /** Retrieves the stored list of Stars.
//   *
//   * @return the stored list of Stars
//   */
//  public List<Star> getStarLs() {
//    return this.starLs;
//  }
//
//  // TODO: javadoc
//  public Map<String, String> getHeaderTypes() {
//    return this.headerTypes;
//  }
//
//  // TODO: javadoc
//  public Map<Integer, List<Pair<String, String>>> getId2Data() {
//    return this.id2Data;
//  }
//
//  // TODO: javadoc
//  public List<RecommenderInsertable> getStudentLs() {
//    return this.studentLs;
//  }
//
//  // TODO: javadoc
//  public void setStudentLs(List<RecommenderInsertable> students) {
//    this.studentLs = students;
//  }
//
//  // TODO: javadoc
//  public void setGetId2Data(Map<Integer, List<Pair<String, String>>> idToData) {
//    this.id2Data = idToData;
//  }
//
//  // TODO: javadoc
//  public void setHeaderTypes(Map<String, String> headerTypes) {
//    this.headerTypes = headerTypes;
//  }
//
//  /** Sets the value of the stored list of Stars.
//   *
//   * @param toSet the List of Stars to set the stored list to
//   */
//  public void setStarLs(List<Star> toSet) {
//    this.starLs = toSet;
//  }
//
//  /** Sets the value of the stored bloom filter.
//   *
//   * @param bloom the bloom filter to set the stored value to
//   */
//  public void setBF(BloomFilter bloom) {
//    this.bf = bloom;
//  }
//
//  /** Sets the value of the stored positivity rate.
//   *
//   * @param pr the value to set the positivity rate to
//   */
//  public void setPositivityRate(double pr) {
//    this.bfPositivityRate = pr;
//  }
//
//  /** Sets the value of the stored number of elements for a bloom filter.
//   *
//   * @param num the number to set the stored num of elts to
//   */
//  public void setNumElts(int num) {
//    this.bfNumElts = num;
//  }
//
//  /** Sets the value of the stored bloom filter map.
//   *
//   * @param bfMap a bloom filter map to set the value of the stored map to
//   */
//  public void setBfMap(Map<String, BloomFilter> bfMap) {
//    this.bfMap = bfMap;
//  }
//
//  /**
//   * Method to get the RequestHandler used for the api commands.
//   * @return the RequestHandler used for the api commands
//   */
//  public RequestHandler getHandler() {
//    return this.handler;
//  }
//
//  /**
//   * Method to set the value of stored list of DatabaseStudent objects.
//   * @param stuList new Student list to set the field to
//   */
//  public void setDbStudentList(List<DatabaseStudent> stuList) {
//    this.dbStudentList = stuList;
//  }
//
//  /**
//   * Method to return dbStudent list of DatabaseStudent objects.
//   * @return list of DatabaseStudent objects.
//   */
//  public List<DatabaseStudent> getDbStudentList() {
//    return this.dbStudentList;
//  }
//
//  /**
//   * Method to set the value of Database Proxy.
//   * @param proxy new proxy to set the Database proxy to
//   */
//  public void setProxy(DatabaseProxy proxy) {
//    this.proxy = proxy;
//  }
//
//  /**
//   * Method to get DatabaseProxy.
//   * @return DatabaseProxy field
//   */
//  public DatabaseProxy getProxy() {
//    return this.proxy;
//  }
}
