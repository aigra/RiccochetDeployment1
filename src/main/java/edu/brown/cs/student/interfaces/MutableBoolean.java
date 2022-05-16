package edu.brown.cs.student.interfaces;

public class MutableBoolean {
  boolean myBoolean;

  public MutableBoolean(boolean b) {
    myBoolean = b;
  }

  public void setBoolean(boolean b) {
    myBoolean = b;
  }

  public boolean getBoolean() {
    return myBoolean;
  }
}
