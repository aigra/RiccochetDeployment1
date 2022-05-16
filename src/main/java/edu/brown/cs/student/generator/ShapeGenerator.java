package edu.brown.cs.student.generator;


import edu.brown.cs.student.interfaces.TargetShape;

import java.util.Arrays;
import java.util.Collections;

public class ShapeGenerator {
  private TargetShape[] shapes = TargetShape.values();
  private int index = 0;

  public ShapeGenerator() {
    Collections.shuffle(Arrays.asList(shapes));
  }

  public TargetShape GetTargetShape() {
    int prevIndex = index;
    index++;
    try {
      return shapes[prevIndex];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Tried to make duplicate target");
      return shapes[0];
    }
  }
}
