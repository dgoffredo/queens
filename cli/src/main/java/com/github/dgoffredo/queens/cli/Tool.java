package com.github.dgoffredo.queens.cli;

import com.github.dgoffredo.queens.backtracking.Backtracking;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "solve", mixinStandardHelpOptions = true, version = "solver 1.0",
         description = "Prints the solution, if any, to standard output.")
class Tool implements Callable<Integer> {

    @Parameters(index = "0", description = "The side length of the chessboard.")
    private int sideLength;

    @Option(names = {"-s", "--solver"}, description = "backtracking")
    private String solverName = "backtracking";

    @Option(names = {"-d", "--debug"}, description = "Print verbose logging to standard error.")
    private boolean debug = false;

    @Override
    public Integer call() throws Exception {
        if (sideLength < 1) {
            System.err.printf("side length parameter must be positive, not %d\n", sideLength);
            return 1;
        }

        if (solverName != "backtracking") {
            System.err.printf("--solver must be \"backtracking\" (the default), not %s\n", solverName);
            return 2;
        }

        var solver = new Backtracking();
        solver.debug = debug;
        var solution = solver.solve(sideLength);

        if (solution == null) {
            System.out.println("no solution");
        }
        else {
            System.out.println(solution);
        }

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Tool()).execute(args);
        System.exit(exitCode);
    }
}