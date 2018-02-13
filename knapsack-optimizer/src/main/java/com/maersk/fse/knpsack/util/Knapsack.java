package com.maersk.fse.knpsack.util;

/*import java.util.ArrayList;

import com.google.ortools.algorithms.KnapsackSolver;
import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
*/
public class Knapsack {
/*
    static {
        System.loadLibrary("jniortools");
    }

    private static void solve() {
        KnapsackSolver solver = new KnapsackSolver(KnapsackSolver.SolverType.KNAPSACK_MULTIDIMENSION_BRANCH_AND_BOUND_SOLVER, "test");
        final long[] profits = { 360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147, 78, 256, 63, 17, 120, 164, 432,
                35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15, 26, 78, 210, 36, 85, 189, 274, 43, 33, 10, 19, 389, 276, 312 };

        final long[][] weights = { { 7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0, 42, 47, 52, 32, 26, 48, 55, 6,
                29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0, 79, 20, 65, 52, 13 } };

        final long[] capacities = { 850 };

        solver.init(profits, weights, capacities);
        final long computedWeight = solver.solve();
        ArrayList<Integer> paked_items = new ArrayList<>();
        ArrayList<Long> packed_weights = new ArrayList<>();
        for (int i = 0; i < weights[0].length; i++) {
            if (solver.bestSolutionContains((int) weights[0][i])) {
                paked_items.add(i);
                packed_weights.add(weights[0][i]);
            }
        }

        // packed_weights = [weights[0][i] for i in packed_items]

        System.out.println("paked_items " + paked_items);

        System.out.println("packed_weights " + packed_weights);
        System.out.println("computed " + computedWeight);
    }

    public static Solution solve(Problem problem) {
        long startTime = System.currentTimeMillis();
        KnapsackSolver solver = new KnapsackSolver(KnapsackSolver.SolverType.KNAPSACK_MULTIDIMENSION_BRANCH_AND_BOUND_SOLVER, "test");

        final long[] profits = problem.getValues();
        final long[][] weights = { problem.getWeights() };
        final long[] capacities = { problem.getCapacity() };

        solver.init(profits, weights, capacities);
        final long computedWeight = solver.solve();
        ArrayList<Integer> paked_items = new ArrayList<>();
        ArrayList<Long> packed_weights = new ArrayList<>();
        for (int i = 0; i < weights[0].length; i++) {
            if (solver.bestSolutionContains((int) weights[0][i])) {
                paked_items.add(i);
                packed_weights.add(weights[0][i]);
            }
        }

        System.out.println("paked_items " + paked_items);

        System.out.println("packed_weights " + packed_weights);
        System.out.println("computed " + computedWeight);
        long endTime = System.currentTimeMillis();
        Integer[] arr = new Integer[paked_items.size()];
        arr = paked_items.toArray(arr);
        return new Solution(arr, endTime - startTime);
    }

    public static void main(String[] args) throws Exception {
        Knapsack.solve();
    }
    */
}
