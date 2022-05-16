package edu.brown.cs.student.generator.commands;

import edu.brown.cs.student.generator.boards.BoardStorage;
import edu.brown.cs.student.interfaces.Quadrant;
import edu.brown.cs.student.interfaces.Target;
import edu.brown.cs.student.repl.REPLCommand;

import java.util.List;

public class PrintQuadrants implements REPLCommand {
  @Override
  public String commandExec(List<String> userInput) {

    Quadrant q = BoardStorage.quadrants[0];
    System.out.println(Quadrant.quadToString(q.getQuad()));
    for (Target t : q.getTargets()) System.out.println(t.targetToString());

    Quadrant r = Quadrant.rotate(q, 1);
    System.out.println(Quadrant.quadToString(r.getQuad()));
    for (Target t : r.getTargets()) System.out.println(t.targetToString());

    Quadrant r2 = Quadrant.rotate(q, 2);
    System.out.println(Quadrant.quadToString(r2.getQuad()));
    for (Target t : r2.getTargets()) System.out.println(t.targetToString());

    Quadrant r3 = Quadrant.rotate(q, 3);
    System.out.println(Quadrant.quadToString(r3.getQuad()));
    for (Target t : r3.getTargets()) System.out.println(t.targetToString());

    return "";
  }
}
