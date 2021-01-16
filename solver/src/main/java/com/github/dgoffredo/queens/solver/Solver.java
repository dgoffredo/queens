package com.github.dgoffredo.queens.solver;

// Solver is the functional interface for all implementations of solutions to
// the extended n-queens problem.
public interface Solver {
    // Return a solution to the n-queens no-three-in-line problem, where "n" is
    // the specified sideLength. Return null if there is no solution.
    Chessboard solve(int sideLength);
}
