package com.github.dgoffredo.queens.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineStackTest {
    @Test public void testBreathing() {
        // Queens will be placed from left to right.
        //
        //    4 · · · · ·
        //    3 · · · ♛ ·
        //    2 ♛ · · · ·
        //    1 · · ♛ · ·
        //    0 · · · · ♛
        //      0 1 2 3 4
        //
        // The first queen (file 0, rank 2) does not lie on any line.
        // The second queen (file 2, rank 1) does not either.
        // Nor does the third (file 3, rank 3).
        // The fourth queen (file 4, rank 0) lies on the same line as the first
        // two.
        var lines = new LineStack();

        assertFalse(lines.intersects(0, 2));
        lines.include(0, 2);

        assertFalse(lines.intersects(2, 1));
        lines.include(2, 1);

        assertFalse(lines.intersects(3, 3));
        lines.include(3, 3);

        assertTrue(lines.intersects(4, 0));
    }
}
