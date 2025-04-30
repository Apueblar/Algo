package MinPath;

import java.util.Random;

public class MinimumPaths {

    final static double p1 = 0.5;
    final static int minWeight = 10;
    final static int maxWeight = 99;

    public String[] v;            // node labels
    public int[][] weights;       // adjacency matrix
    public int[][] costs;         // shortest-path cost matrix
    public int[][] p;             // predecessor matrix

    public static final int INFINITE = Integer.MAX_VALUE;
    public static final int EMPTY = -1;

    public MinimumPaths(int n) {
        v = new String[n];
        for (int i = 0; i < n; i++) {
            v[i] = "NODE" + i;
        }
        weights = new int[n][n];
        costs = new int[n][n];
        p = new int[n][n];
    }

    public MinimumPaths(String[] v, int[][] weights) {
        this.v = v;
        this.weights = weights;
        int n = weights.length;
        this.costs = new int[n][n];
        this.p = new int[n][n];
    }

    public void basicAlgorithm() {
        fillInRandomWeights();
        floyd();
    }

    /**
     * Floyd-Warshall all-pairs shortest paths
     */
    public void floyd() {
        initializeFloyd();
        int n = weights.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (costs[i][k] == INFINITE) continue;
                for (int j = 0; j < n; j++) {
                    if (costs[k][j] == INFINITE) continue;
                    int throughK = costs[i][k] + costs[k][j];
                    if (throughK < costs[i][j]) {
                        costs[i][j] = throughK;
                        p[i][j] = p[k][j];
                    }
                }
            }
        }
    }

    /**
     * Initialize costs = weights, set diagonal 0, set predecessors
     */
    private void initializeFloyd() {
        int n = weights.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    costs[i][j] = 0;
                    p[i][j] = EMPTY;
                } else if (weights[i][j] != INFINITE) {
                    costs[i][j] = weights[i][j];
                    p[i][j] = i;
                } else {
                    costs[i][j] = INFINITE;
                    p[i][j] = EMPTY;
                }
            }
        }
    }

    /**
     * Randomly fill adjacency matrix
     */
    private void fillInRandomWeights() {
        Random rnd = new Random();
        int n = weights.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    weights[i][j] = 0;
                } else if (rnd.nextDouble() < p1) {
                    weights[i][j] = rnd.nextInt(minWeight, maxWeight + 1);
                } else {
                    weights[i][j] = INFINITE;
                }
            }
        }
    }

    /**
     * Print minimal path and cost from origin to destination
     */
    public void printFloydPath(int origin, int destination) {
        int n = weights.length;
        if (origin < 0 || origin >= n) {
            throw new IllegalArgumentException("printFloydPath: origin out of bounds");
        }
        if (destination < 0 || destination >= n) {
            throw new IllegalArgumentException("printFloydPath: destination out of bounds");
        }
        System.out.print("FROM " + v[origin] + " TO " + v[destination] + " = ");
        if (costs[origin][destination] == INFINITE) {
            System.out.println("THERE IS NO PATH");
        } else {
            // reconstruct path
            printPath(origin, destination);
            System.out.println();
            System.out.println("MINIMUM COST = " + costs[origin][destination]);
        }
        System.out.println("**************");
    }

    /**
     * Recursive path printer: prints sequence of nodes
     */
    private void printPath(int i, int j) {
        if (i == j) {
            System.out.print(v[i]);
        } else if (p[i][j] == EMPTY) {
            // direct edge
            System.out.print(v[i] + " --> " + v[j]);
        } else {
            int k = p[i][j];
            printPath(i, k);
            System.out.print(" --> " + v[j]);
        }
    }

    /**
     * Utility: print any integer matrix (INF for infinite)
     */
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == INFINITE) System.out.print("    Inf");
                else System.out.printf("%7d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}