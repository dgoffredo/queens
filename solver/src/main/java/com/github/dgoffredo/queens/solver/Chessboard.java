package com.github.dgoffredo.queens.solver;

// This class represents a chessboard of arbitrary square dimension. Its
// intended use is displaying a one-color chess position as a string.
public class Chessboard {
    // This enum represents the content of a square on the chessboard.
    public enum Piece {
        EMPTY,
        QUEEN;

        @Override
        public String toString() {
            if (this == EMPTY) {
                    return "·";
            } else {
                assert this == QUEEN;
                return "♛";
            }
        }
    }

    private Piece[][] squares; // [file][rank]

    // Create an empty chessboard having the specified sideLength, e.g.
    // new Chessboard(8) is an 8x8 chessboard.
    public Chessboard(int sideLength) {
        assert sideLength > 0 : sideLength;

        squares = new Piece[sideLength][sideLength];
        for (int file = 0; file < sideLength; ++file) {
            for (int rank = 0; rank < sideLength; ++rank) {
                squares[file][rank] = Piece.EMPTY;
            }
        }
    }

    // Set the board square at the specified file and the specified rank to
    // contain the specified piece, or to empty. If the square already has a
    // piece on it, then it will be replaced. The behavior is undefined unless
    // file and rank denote a square on the board.
    public void place(Piece piece, int file, int rank) {
        assert file >= 0 : file;
        assert file < squares.length : file;
        assert rank >= 0 : rank;
        assert rank < squares.length : rank;
        
        squares[file][rank] = piece;
    }

    // Return a string containing a human-readable unicode graphical
    // representation of this chessboard.
    public String toString() {
        var n = squares.length;
        var builder = new StringBuilder();

        for (int rank = n - 1; rank >= 0; --rank) {
            builder.append("\n");
            for (int file = 0; file < n; ++file) {
                builder.append(" ");
                builder.append(squares[file][rank].toString());
            }
        }
        builder.append("\n");

        return builder.toString();
    }
}
