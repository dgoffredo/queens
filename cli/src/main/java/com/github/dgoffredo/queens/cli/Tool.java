package com.github.dgoffredo.queens.cli;

import com.github.dgoffredo.queens.solver.Chessboard;

public class Tool {
    public static void main(String[] args) {
        var board = new Chessboard(8);
        board.place(Chessboard.Piece.QUEEN, 1, 4);
        System.out.println(board.toString());
    }
}
