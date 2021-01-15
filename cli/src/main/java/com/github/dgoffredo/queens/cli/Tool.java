package com.github.dgoffredo.queens.cli;

import com.github.dgoffredo.queens.backtracking.Backtracking;
import com.github.dgoffredo.queens.solver.Chessboard;

public class Tool {
    public static void main(String[] args) {
        /*
        var board = new Chessboard(8);
        board.place(Chessboard.Piece.QUEEN, 1, 4);
        System.out.println(board.toString());

        var solver = new Backtracking();
        solver.solve(10);
        */
        var solver = new Backtracking();
        // solver.debug = true;
        for (int n = 4; true; ++n) {
            System.out.println(n);
            System.out.println(solver.solve(n));
            System.out.println();
        }
    }
}
