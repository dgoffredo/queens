package com.github.dgoffredo.queens.cli;

import com.github.dgoffredo.queens.backtracking.Backtracking;

public class Tool {
    public static void main(String[] args) {
        var solver = new Backtracking();
        // solver.debug = true;
        for (int n = 4; true; ++n) {
            System.out.println(n);
            System.out.println(solver.solve(n));
            System.out.println();
        }
    }
}
