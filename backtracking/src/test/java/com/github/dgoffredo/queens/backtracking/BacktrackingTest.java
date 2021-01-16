package com.github.dgoffredo.queens.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.dgoffredo.queens.solver.Chessboard;

public class BacktrackingTest {
    private static String stringify(Chessboard chessboard) {
        if (chessboard == null) {
            return null;
        }
        return chessboard.toString();
    }

    @Test public void testAFewSolutions() {
        var solver = new Backtracking();
        StringBuilder expected;
        String actual;

        actual = stringify(solver.solve(4));
        expected = new StringBuilder();
        expected.append("\n · ♛ · ·");
        expected.append("\n · · · ♛");
        expected.append("\n ♛ · · ·");
        expected.append("\n · · ♛ ·");
        expected.append("\n");
        assertEquals(expected.toString().length(), actual.length());
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(5));
        assertNull(actual);
        
        actual = stringify(solver.solve(6));
        assertNull(actual);
        
        actual = stringify(solver.solve(7));
        assertNull(actual);
        
        actual = stringify(solver.solve(8));
        expected = new StringBuilder();
        expected.append("\n · · ♛ · · · · ·");
        expected.append("\n · · · · · ♛ · ·");
        expected.append("\n · · · · · · · ♛");
        expected.append("\n · ♛ · · · · · ·");
        expected.append("\n · · · ♛ · · · ·");
        expected.append("\n ♛ · · · · · · ·");
        expected.append("\n · · · · · · ♛ ·");
        expected.append("\n · · · · ♛ · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(9));
        expected = new StringBuilder();
        expected.append("\n · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · ♛ ·");
        expected.append("\n · · ♛ · · · · · ·");
        expected.append("\n · · · · · · ♛ · ·");
        expected.append("\n · · · · · · · · ♛");
        expected.append("\n · ♛ · · · · · · ·");
        expected.append("\n · · · · ♛ · · · ·");
        expected.append("\n ♛ · · · · · · · ·");
        expected.append("\n · · · ♛ · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(10));
        expected = new StringBuilder();
        expected.append("\n · · · · · ♛ · · · ·");
        expected.append("\n · · ♛ · · · · · · ·");
        expected.append("\n · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · ♛");
        expected.append("\n · · · · · · · ♛ · ·");
        expected.append("\n · · · ♛ · · · · · ·");
        expected.append("\n · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · · ♛ ·");
        expected.append("\n ♛ · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(11));
        expected = new StringBuilder();
        expected.append("\n · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · · ♛ · ·");
        expected.append("\n · · · · ♛ · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · ♛ · · · ·");
        expected.append("\n · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · ·");
        expected.append("\n ♛ · · · · · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(12));
        expected = new StringBuilder();
        expected.append("\n · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · · · · · ♛");
        expected.append("\n · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · ·");
        expected.append("\n · · · · · · · · · ♛ · ·");
        expected.append("\n · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · ·");
        expected.append("\n ♛ · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(13));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · · ♛ · · ·");
        expected.append("\n · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · · · · · ♛ · · · ·");
        expected.append("\n · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · · · ♛ ·");
        expected.append("\n · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(14));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · · · · · · · ♛");
        expected.append("\n · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ ·");
        expected.append("\n · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · ♛ · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(15));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · ♛ · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(16));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · · · · · · ♛ · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(17));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ · · · ·");
        expected.append("\n · · · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(18));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · ♛ · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · ♛ · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(19));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · ♛ · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛ · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · · · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
        
        actual = stringify(solver.solve(20));
        expected = new StringBuilder();
        expected.append("\n · · · · · · · · · · · ♛ · · · · · · · ·");
        expected.append("\n · · · · · · · · ♛ · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · ♛ · · · · · · · · ·");
        expected.append("\n · · · · · ♛ · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · · · ♛");
        expected.append("\n · · · · · · · · · ♛ · · · · · · · · · ·");
        expected.append("\n · · · · ♛ · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · ♛ · ·");
        expected.append("\n · · · · · · · · · · · · · ♛ · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · · · · ♛ ·");
        expected.append("\n · · · · · · · · · · · · · · · · ♛ · · ·");
        expected.append("\n · · · · · · ♛ · · · · · · · · · · · · ·");
        expected.append("\n · · · ♛ · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · ♛ · · · · · · · · · · · ·");
        expected.append("\n · · ♛ · · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · ♛ · · · · ·");
        expected.append("\n · · · · · · · · · · · · ♛ · · · · · · ·");
        expected.append("\n · ♛ · · · · · · · · · · · · · · · · · ·");
        expected.append("\n · · · · · · · · · · · · · · · ♛ · · · ·");
        expected.append("\n ♛ · · · · · · · · · · · · · · · · · · ·");
        expected.append("\n");
        assertEquals(expected.toString(), actual);
    }
}
