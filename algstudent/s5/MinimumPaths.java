package MinPath;

import java.util.Random;

public class MinimumPaths {
	final static double p1=0.5; 
	final static double p2=0.5; // Unused
	final static int minWeight=10; 
	final static int maxWeight=99;

	public static void basicAlgorithm(int n) {
		String[] v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		int[][] weights = new int[n][n];
		int[][] costs = new int[n][n];
		int[][] p = new int[n][n];

		fillInRandomWeights(weights); //weights for the example

		floyd(weights, costs, p);
	}

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	static void floyd(int[][] weights, int[][] costs, int[][] p) {
		int n = weights.length;
        // initialize costs and predecessor matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = weights[i][j];
                if (i == j)
                    p[i][j] = i;  // self loop
                else if (weights[i][j] != Integer.MAX_VALUE)
                    p[i][j] = i;  // direct edge exists, predecessor is i
                else
                    p[i][j] = -1; // no direct edge
            }
        }
        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE &&
                        costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        p[i][j] = p[k][j];
                    }
                }
            }
        }
	}

	private static void fillInRandomWeights(int[][] weights) {
		Random p = new Random();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				if (p.nextDouble() < p1) {
					weights[i][j] = p.nextInt(minWeight, maxWeight+1);
				} else
					weights[i][j] = Integer.MAX_VALUE;
			}
		}
	}
}