package com.github.dgoffredo.queens.solver;

public interface Solver {
    // Return a solution to the n-queens no-three-in-line problem, where "n" is
    // the specified sideLength. Return null if there is no solution.
    Chessboard solve(int sideLength);
}
