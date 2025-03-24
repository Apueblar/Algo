package MinPath;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPathsExample {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths

	public static void main(String arg[]) {
		int n = 5; //nodes of example graph
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];

		fillInWeights(weights); //weights for the example
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);

		floyd(weights, costs, p);

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different nodes):");
		System.out.println();
		for (int source = 0; source <= n-1; source++)
			for (int target = 0; target <= n-1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					minimumPath(v, weights, costs, p, source, target);
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
				}

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

	static void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] steps, int source, int target) {
        if (costs[source][target] == Integer.MAX_VALUE) {
            System.out.println("THERE IS NO PATH");
        } else {
            path(v, p, source, target);
            System.out.println();
        }
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static void path(String[] v, int[][] steps, int i, int j) {
		if (p[i][j] == -1) {
            System.out.print("THERE IS NO PATH");
            return;
        }
        if (i == j) {
            System.out.print(v[i]);
        } else if (p[i][j] == i) {
            System.out.print(v[i] + "-->" + v[j]);
        } else {
            path(v, p, i, p[i][j]);
            System.out.print("-->" + v[j]);
        }
	}

	/* load the example cost matrix */
	static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = Integer.MAX_VALUE;
		w[0][1] = 19;
		w[0][2]=  10;
		w[1][2]=  20;
		w[2][1] = 19;
		w[2][3] = 14;
		w[3][0] = 27;
		w[3][1] = 67;
	    w[3][2]=  21;
		w[4][1]=  80;
	}
	
	/* print the cost matrix */
	static void printMatrix(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(a[i][j] != Integer.MAX_VALUE ? String.format("%8s", a[i][j]) : "     Inf");
			System.out.println();
		}
		System.out.println();
	}

}