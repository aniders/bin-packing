package com.aniders.knpsack.solver.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aniders.knpsack.solver.dto.Problem;
import com.aniders.knpsack.solver.dto.Solution;
import com.aniders.knpsack.solver.service.impl.KnapsackSolverImpl;

public class KnapSackSolverTest {

	@Test
	public void knapsackTest() {
		int[] values = { 360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147, 78, 256, 63, 17, 120,
				164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15, 26, 78, 210, 36, 85, 189, 274, 43, 33,
				10, 19, 389, 276, 312 };
		int[] weights = { 7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0, 42, 47, 52, 32, 26,
				48, 55, 6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0, 79, 20, 65, 52, 13 };
		int capacity = 850;

		Solution result = new KnapsackSolverImpl().solve(new Problem(capacity, weights, values));

		int[] check = new int[] { 0, 1, 3, 4, 6, 10, 11, 12, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 29, 30, 31, 32,
				34, 38, 39, 41, 42, 44, 47, 48, 49 };
		for (int i = 0; i < result.getItems().length; i++) {
			int r = result.getItems()[i];
			int c = check[i];
			assertTrue("Knapsack problem. expected=" + c + " got=" + r, r == c);
		}
	}
	
	@Test
	public void knapsackTest2() {
		Solution result = new KnapsackSolverImpl().solve(new Problem(60, new int[] {10, 20, 33}, new int[] {10, 3, 30}));
		
		int[] check = new int[] { 0, 2 };
		for (int i = 0; i < result.getItems().length; i++) {
			int r = result.getItems()[i];
			int c = check[i];
			assertTrue("Knapsack problem. expected=" + c + " got=" + r, r == c);
		}
	}

}
