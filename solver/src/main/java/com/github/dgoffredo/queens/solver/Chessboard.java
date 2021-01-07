package com.github.dgoffredo.queens.solver;

// This class represents a chessboard of arbitrary square dimension. Its
// intended use is displaying a chess position as a string.
public class Chessboard {
    public enum Square {
        EMPTY,
        QUEEN
    };

    private Square[][] squares;

    // Create an empty chessboard having the specified sideLength, e.g.
    // new Chessboard(8) is an 8x8 chessboard.
    public Chessboard(int sideLength) {
        assert sideLength > 0 : sideLength;

        squares = new Square[sideLength][sideLength];
    }

    // Set the board square at the specified file and the specified rank to
    // contain the specified piece, or to empty. If the square already has a
    // piece on it, then it will be replaced. The behavior is undefined unless
    // file and rank denote a square on the board.
    public void place(Square piece, int file, int rank) {
        assert file >= 0 : file;
        assert file < squares.length : file;
        assert rank >= 0 : rank;
        assert rank < squares.length : rank;
        
        squares[file][rank] = piece;
    }

    // Return a string containing a human-readable unicode graphical
    // representation of this chessboard.
    public String toString() {
        // TODO
        return "<unimplemented>";
    }
}
