package repl;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.repl.REPL;
import org.junit.Assert;
import org.junit.Test;

public class REPLTest {

  @Test
  public void testErrorOutput() {
    Assert.assertEquals("ERROR: hello world\n", REPL.errorOutput("hello world"));
  }
}
