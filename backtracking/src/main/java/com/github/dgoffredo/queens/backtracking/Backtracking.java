package com.github.dgoffredo.queens.backtracking;

import java.util.Arrays;
import java.util.BitSet;

import com.github.dgoffredo.queens.solver.Chessboard;
import com.github.dgoffredo.queens.solver.Solver;

// The backtracking solver places queens one file at a time. When it
// can no longer place a queen, it moves the previous queen to the next
// available rank. If it can't move that one, then it moves the previous, etc.
// until either it has found a solution or exhausted half of the ranks in the
// first file.
public class Backtracking implements Solver {
    // rankOccupancy says whether any queen occupies a rank. The index into the
    // BitSet is the rank index.
    private BitSet rankOccupancy = new BitSet(); // true means occupied

    // A forward diagonal is one that resembles a forward slash when viewed
    // from White's point of view. They are lines of the form
    //
    // rank = file + k
    //
    // where k is between -(n - 1) and (n - 1), where n is the side length of
    // the chessboard. The index into the BitSet is the value of k + (n - 1). A
    // bit is set (true) if there's a queen on that diagonal.
    private BitSet forwardDiagonalOccupancy = new BitSet();

    // A backward diagonal is one that resembles a backslash when viewed from
    // White's point of view. They are lines of the form
    //
    // rank = k - file
    //
    // where k is between -(n - 1) and (n - 1), where n is the side length of
    // the chessboard. The index into the BitSet is the value of k + (n - 1). A
    // bit is set (true) if there's a queen on that diagonal.
    private BitSet backwardDiagonalOccupancy = new BitSet();

    // lines keeps track of lines through queens
    private LineStack lines = new LineStack();

    // queens stores the rank index of the queen at a particular file index. -1
    // means "no queen (yet)."
    private int[] queens; // :: file -> rank

    // debugging indicates whether this object will print verbose logging to
    // standard error.
    public boolean debug = false;

    // (re)initialize this object to an empty chessboard having the specified
    // sideLength.
    private void reset(int sideLength) {
        rankOccupancy.clear();
        forwardDiagonalOccupancy.clear();
        backwardDiagonalOccupancy.clear();
        lines.clear();
        queens = new int[sideLength];
        Arrays.fill(queens, -1);
    }

    public Chessboard solve(int sideLength) {
        reset(sideLength);

        int file = 0;
        files:
        for (;;) {
            if (debug) {
                System.err.println("================");
                System.err.printf("top of loop, file: %d\n", file);
                System.err.flush();

                System.err.println(toChessboard());
                System.err.println("");
                System.err.println(lines.toString());
                System.err.println("");
            }

            if (file == sideLength) {
                // We progressed to the end of the chessboard. We've found a
                // solution.
                return toChessboard();
            }

            int rank = queens[file] + 1;
            removeQueen(file);
            // looping over ranks
            for (;;) {
                rank = nextAvailableRank(rank);
                if (debug) {
                    System.err.printf("inner loop, rank: %d\n", rank);
                    System.err.flush();
                }

                if (rank == sideLength) {
                    if (debug) {
                        System.err.printf("file %d doesn't have any available ranks. Backtracking...\n", file);
                    }
                    --file;
                    continue files;
                }
                // If we've checked half of the ranks in the first file, then by
                // symmetry the rest are the same, so we're not going to find a
                // solution.
                else if (file == 0 && rank == (sideLength + 1) / 2) {
                    return null;
                }
                else if (available(file, rank)) {
                    if (debug) {
                        System.err.printf("(%d, %d) is available\n", file, rank);
                    }
                    break;
                }
                else {
                    ++rank;
                }
            } 

            // We found an available rank for this file. Place a queen, update
            // bookkeeping, and go to the next file.
            addQueen(file, rank);
            ++file;
        }
        // unreachable
    }

    private void removeQueen(int file) {
        int rank = queens[file];
        if (debug) {
            System.err.printf("remove queen file %d rank %d\n", file, rank);
            System.err.flush();
        }
        if (rank != -1) {
            rankOccupancy.clear(queens[file]);
            forwardDiagonalOccupancy.set((rank - file) + (queens.length - 1), false);
            backwardDiagonalOccupancy.set((rank + file) + (queens.length - 1), false);
            lines.removeRecentThrough(file, rank);
            queens[file] = -1;
        }
    }

    private void addQueen(int file, int rank) {
        queens[file] = rank;
        rankOccupancy.set(rank);
        forwardDiagonalOccupancy.set((rank - file) + (queens.length - 1));
        backwardDiagonalOccupancy.set((rank + file) + (queens.length - 1));
        lines.include(file, rank);
    }

    private boolean available(int file, int rank) {
        // This function assumes that the rank and file are available, because
        // of the way the solver works. Here we need check only the two
        // diagonal directions and the other lines.
        assert !rankOccupancy.get(rank);

        // See the documentation of forwardDiagonalOccupancy for an explanation
        // of this index.
        if (forwardDiagonalOccupancy.get((rank - file) + (queens.length - 1))) {
            // the forward diagonal is already occupied by a queen
            return false;
        }
        
        // See the documentation of backwardDiagonalOccupancy for an
        // explanation of this index.
        if (backwardDiagonalOccupancy.get((rank + file) + (queens.length - 1))) {
            // the backward diagonal is already occupied by a queen
            return false;
        }

        return !lines.intersects(file, rank);
    }

    private Chessboard toChessboard() {
        var board = new Chessboard(queens.length);
        for (int i = 0; i < queens.length; ++i) {
            if (queens[i] != -1) {
                board.place(Chessboard.Piece.QUEEN, i, queens[i]);
            }
        }
        return board;
    }

    private int nextAvailableRank(int rank) {
        if (rank == -1) {
            return rankOccupancy.nextClearBit(0);
        }

        return rankOccupancy.nextClearBit(rank);
    }
}
