import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for expression parsing.
 */

public class evaluationTest {

    /**
     * Checking functionality of evaluate method.
     */
    @Test
    public void expreval() {
        evaluation tester = new evaluation();
        assertEquals(383.0, tester.expreval("113 + (90 * (10 - 7))"), 1);
        assertEquals(0, tester.expreval("(6 - 2) / (9 - 2 * (5 - 3)) * 3"), 1);
    }

    /**
     * Checking accuracy for operators precedences.
     */
    @Test
    public void precedence() {
        evaluation tester = new evaluation();

        assertFalse(tester.precedence('(',')'));
        assertFalse(tester.precedence('*','-'));

        assertTrue(tester.precedence('+', '-'));
        assertTrue(tester.precedence('+', '/'));
    }

    /**
     * Tests for applyOp method.
     */
    @Test
    public void arithmetic() {
        evaluation tester = new evaluation();

        assertEquals(10, tester.arithmetic('+', 5, 5));
        assertEquals(14, tester.arithmetic('*', 7, 2));

        assertNotEquals(10, tester.arithmetic('-', 10, 10));
        assertNotEquals(1, tester.arithmetic('/', 21, 7));
    }
}