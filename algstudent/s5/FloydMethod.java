package MinPath;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//ITS TIME O(n^3)
public class FloydMethod {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	public void floyd(int[][] weights, int[][] costs, int[][] p) {
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

	public void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] steps, int source, int target) {
        if (costs[source][target] == Integer.MAX_VALUE) {
            System.out.println("THERE IS NO PATH");
        } else {
            path(v, p, source, target);
            System.out.println();
        }
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	public void path(String[] v, int[][] steps, int i, int j) {
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
}