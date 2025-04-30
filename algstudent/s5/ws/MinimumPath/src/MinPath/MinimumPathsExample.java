package MinPath;

// MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
// TIME COMPLEXITY: O(n^3)
public class MinimumPathsExample {

    public static void main(String[] args) {
        final int n = 5;
        // Node labels
        String[] v = {"NODO0", "NODO1", "NODO2", "NODO3", "NODO4"};

        // Build weight matrix for example graph
        int[][] weights = new int[n][n];
        fillInWeights(weights);

        // Display input graph
        System.out.println("WEIGHT MATRIX:");
        MinimumPaths.printMatrix(weights);

        // Compute shortest paths
        MinimumPaths mp = new MinimumPaths(v, weights);
        mp.floyd();

        // Display results
        System.out.println("MINIMUM COST MATRIX:");
        MinimumPaths.printMatrix(mp.costs);

        System.out.println("PREDECESSOR MATRIX:");
        MinimumPaths.printMatrix(mp.p);

        // Print all pairs
        System.out.println("MINIMUM PATHS FOR ALL NODE PAIRS:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                System.out.print("FROM " + v[i] + " TO " + v[j] + " = ");
                if (mp.costs[i][j] == MinimumPaths.INFINITE) {
                    System.out.println("THERE IS NO PATH");
                } else {
                    printPath(mp, i, j);
                    System.out.println();
                    System.out.println("MINIMUM COST=" + mp.costs[i][j]);
                }
                System.out.println("**************");
            }
        }
    }

    /**
     * Recursively prints the path from i to j using mp.p
     */
    private static void printPath(MinimumPaths mp, int i, int j) {
        int[][] p = mp.p;
        String[] v = mp.v;
        if (i == j) {
            System.out.print(v[i]);
        } else if (p[i][j] == MinimumPaths.EMPTY) {
            // direct
            System.out.print(v[i] + "-->" + v[j]);
        } else {
            int k = p[i][j];
            printPath(mp, i, k);
            System.out.print("-->" + v[j]);
        }
    }

    /**
     * Load the example graph weights (INFINITE for no edge)
     */
    static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = MinimumPaths.INFINITE;
		w[0][1] = 19;
		w[2][1] = 91;
		w[2][3] = 14;
		w[3][0] = 27;
		w[3][1] = 67;
		w[3][3] = 71;
	}
}
