package com.github.dgoffredo.queens.backtracking;

import java.util.ArrayList;

// TODO document
public class LineStack {

    // a square on a chessboard
    static class Square {
        public int file;
        public int rank;
    }

    // A line connects two squares: a "tail" to a "head" (imagine an arrow).
    static class Line {
        public Square tail;
        public Square head;
    }

    private ArrayList<Line> lines = new ArrayList<Line>();
    private ArrayList<Square> squares = new ArrayList<Square>();

    // Return whether the chessboard square at the specified file and rank lies
    // on any of this object's lines.
    public boolean intersects(int file, int rank) {
        // This class assumes that chessboard squares added using the "include"
        // method never share a file. This way, the slope of all lines between
        // squares is assumed to be defined, where the slope of the line
        // passing through two squares A and B is:
        //
        //     rank(B) - rank(A)
        //     -----------------
        //     file(B) - file(A)
        //
        // A third square, C, lies on that line iff
        //
        //     rank(C) - rank(W)         rank(B) - rank(A)
        //     -----------------    =    -----------------    
        //     file(C) - file(W)         file(B) - file(A)
        //
        // where W is either of A or B (doesn't matter which).
        //
        // For the line connecting A and B, the right hand side of the equation
        // is a constant; say, K. Then C lies on the line A B iff
        //
        //     rank(C) - rank(W)    =    K * (file(C) - file(W))
        //
        // Keeping to the integers, let K = X / Y. Then C lies on the line A B
        // iff
        //
        //     Y * (rank(C) - rank(W))    =    X * (file(C) - file(W))

        for (Line line : lines) {
            // To use the terminology above: line.head is B, line.tail is A,
            // and the parameters to this method are C. W is B, arbitrarily.
            var δRank = line.head.rank - line.tail.rank;
            var δFile = line.head.file - line.tail.file;
            
            if (δFile * (rank - line.head.rank) == δRank * (file - line.head.file)) {
                return true;
            }
        }

        return false;
    }

    // Return whether the last line in this object has a head whose coordinates
    // are the specified file and rank. Return false if this object is empty.
    private boolean lastHeadEquals(int file, int rank) {
        if (lines.isEmpty()) {
            return false;
        }

        var last = lines.get(lines.size() - 1);
        return last.head.file == file && last.head.rank == rank;
    }
    
    // Remove all lines passing through the chessboard square having the
    // specified file and rank.
    public void removeRecentThrough(int file, int rank) {
        // This data structure is intended for use in a backtracking algorithm.
        // Lines will be removed only when we backtrack, and we'll always be
        // removing lines that we just added. So, we can pop elements off of
        // the back of the ArrayList and stop when we encounter a line whose
        // head isn't (file, rank).
        while (lastHeadEquals(file, rank)) {
            assert !lines.isEmpty();
            lines.remove(lines.size() - 1);
        }

        assert !squares.isEmpty();
        assert squares.get(squares.size() - 1).file == file;
        assert squares.get(squares.size() - 1).rank == rank;

        squares.remove(squares.size() - 1);
    }

    // Add lines between all previously added squares and the specified square
    // (file, rank).
    public void include(int file, int rank) {
        var square = new Square();
        square.file = file;
        square.rank = rank;

        for (Square existing : squares) {
            var line = new Line();
            line.tail = existing;
            line.head = square;

            lines.add(line);
        }

        squares.add(square);
    }
}
