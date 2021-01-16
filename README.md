Queens
======

    · · ♛ · · · · ·
    · · · · · ♛ · ·
    · · · · · · · ♛
    · ♛ · · · · · ·
    · · · ♛ · · · ·
    ♛ · · · · · · ·
    · · · · · · ♛ ·
    · · · · ♛ · · ·

Why
---
Let's learn Java.

What
----
Queens is a command line tool that prints a solution to the following problem:

Place `n` chess queens on an `n`-by-`n` chessboard such that no queen is
attacking any other, and additionally that no three queens lie on the same
line.

How
---
The command line solver can be run using the included [gradle][1] tooling. On
Unix-like systems, use the [solve](solve) wrapper script:

```console
$ ./solve --help
Usage: solve [-dhV] [-s=<solverName>] <sideLength>
Prints the solution, if any, to standard output.
      <sideLength>   The side length of the chessboard.
  -d, --debug        Print verbose logging to standard error.
  -h, --help         Show this help message and exit.
  -s, --solver=<solverName>
                     backtracking
  -V, --version      Print version information and exit.

$
```

The argument to `solve` is the side length of the chessboard, e.g. for a
standard 8x8 chessboard:

```console
$ ./solve 8

 · · ♛ · · · · ·
 · · · · · ♛ · ·
 · · · · · · · ♛
 · ♛ · · · · · ·
 · · · ♛ · · · ·
 ♛ · · · · · · ·
 · · · · · · ♛ ·
 · · · · ♛ · · ·

$
```

Some board dimensions do not have a solution, for example a 7x7 chessboard:

```console
$ ./solve 7
no solution

$
```

A recent installation of [Java][2] is required.

### Build
```console
$ ./gradlew build
```

### Test
```console
$ ./gradlew test
```

More
----
The code is organized to support multiple solving strategies. Currently,
only one solver is implemented: the backtracking solver. Other solvers that
come to mind are linear programming based, or boolean satisfiability based.

### Backtracking
The [backtracking solver][3] places queens one file at a time. When it
it encounters a "collision" (two queens are attacking each other, or three
lie on the same line), it moves the most recent queen to the next available
rank. If there are no more available ranks, it removes that queen and moves
the previous queen, etc. Either a solution is eventually reached, or the first
queen exhausts its available ranks, in which case there is no solution.

Files are kept track of automatically by virtue of the algorithm. Ranks are
kept track of using a bit vector, where each bit corresponds to a rank and is
occupied if there is a queen at that rank. Similarly, both forward and backward
flavors of diagonals are kept track of using bit vectors.

Lines that are not ranks, columns, or diagonals (i.e. lines whose slope is
none of 0, 1, -1, ∞, -∞) are kept track of using a custom data structure
that I call a [line stack][4]. When a queen is added to the chessboard, a
line is added to the line stack for each other queen on the chessboard. When
a queen is removed from the chessboard, it is always the most recent queen,
and so lines can be removed from the end only – hence "stack."

To determine whether a proposed queen lies on some existing line, each line
is checked individually, in order (i.e., it's a linear scan). This could be
improved by using some sort of space partitioning (e.g. "this line affects
only the third quadrant"), at the cost of adding to the complexity of line
insertions. I think that this is a worthwhile improvement, but I did not
implement it.

The algorithm consumes `O(n^2)` space, where `n` is the side length of the
chessboard. Memory consumption is dominated by the storage of lines.

The time complexity of the vanilla n-queens problem with backtracking is
`O(n!)`, but here we're doing an additional `~O(n^2)` to check line
collisions for each proposed queen. Suffice it to say, my laptop starts
chugging for `n > 30`.

### Linear Programming
At first I thought of this problem as an extension to the n-queens problem, but
now I think of it as an extension to the no-three-in-line problem. A chess
queen's attack directions are lines, so this queens problem is the
no-three-in-line problem with the added constraint that lines having any of the
slopes [0, 1, -1, ∞, -∞] can have _no more than **one**_ queen, rather than _no
more than **two**_.

This interpretation lends itself to a solution using a linear programming
solver. A queen contributes a "value" of one, and the problem can then be
expressed as a (very large) system of linear inequalities stating the maximum
number of queens allowed in each line.

David Eppstein wrote a [blog post][5] where he uses this technique to solve a
related problem.

I researched using the JNI [Google OR-Tools][6] or the pure Java
[WVLPSolver][7] libraries to prototype such a solution, but decided not to
implement one.

### Boolean Satisfiability
A "lower-level" rendering of the queens problem is to consider each chessboard
square's occupation by a queen to be a boolean variable. Then the problem is a
system of boolean predicates in these variables. There are many clever and
practically efficient SAT solvers available, but I didn't even bother to try to
use any, since I thought the number of predicates involved would be excessive.
The linear programming interpretation seemed more promising, and in the end a
custom-made backtracking solution seemed better fit to purpose than either.

[1]: https://gradle.org/
[2]: https://www.java.com/
[3]: backtracking/src/main/java/com/github/dgoffredo/queens/backtracking/Backtracking.java
[4]: backtracking/src/main/java/com/github/dgoffredo/queens/backtracking/LineStack.java
[5]: https://11011110.github.io/blog/2018/11/12/gurobi-vs-no.html
[6]: https://developers.google.com/optimization/introduction/java
[7]: https://win-vector.com/2012/11/23/yet-another-java-linear-programming-library/

