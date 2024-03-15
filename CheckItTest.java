import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CheckItTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // Predicate tests
    @Test public void testPredicateTrue()
    {
        CheckIt.checkIt(true, true, false);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test public void testPredicateFalse()
    {
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // Clause tests
    @Test public void testClauseTrue()
    {
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test public void testClauseFalse()
    {
        CheckIt.checkIt(false, false, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // CACC tests, a is major
    @Test public void testCACC1()
    {
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test public void testCACC2()
    {
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // RACC tests, a is major
    @Test public void testRACC1()
    {
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test public void testRACC2()
    {
        CheckIt.checkIt(false, false, true);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

}
