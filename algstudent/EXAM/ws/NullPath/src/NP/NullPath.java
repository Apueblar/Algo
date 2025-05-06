package NP;

import java.util.*;

public class NullPath {
    private int n;
    private int[][] weights;
    public static final int maxWeight = 99;
    public static final int minWeight = 10;

    public static final int start = 0;
    public int end;

    public static final double p1 = 0.5;

    private int weight;

    // Asymmetric tolerance bounds
    private int minTolerance = -75;
    private int maxTolerance = 50;
    private boolean found = false;
    private List<Integer> solution = new ArrayList<>();

    public NullPath(int n) {
        this.n = n;
        this.end = n - 1;
        weights = new int[n][n];
        generateWeights();
    }

    // Generate the random weight matrix according to the specified probabilities and ranges.
    private void generateWeights() {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // ignore self-loops
                if (rand.nextDouble() <= p1) {
                    weights[i][j] = rand.nextInt(maxWeight + 1 - minWeight) + minWeight;
                } else {
                    weights[i][j] = -(rand.nextInt(maxWeight + 1 - minWeight) + minWeight);
                }
            }
        }
    }

    // Backtracking method to find a NullPath with alternating edge signs and tolerance bounds.
    public void findNullPath() {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        path.add(start);
        visited[start] = true;
        backtrack(start, path, 0, visited, 0);
        
        for (int i = 0; i < n; i++) {
        	System.out.print(String.format("%2d -> ", i));
        	for (int j = 0; j < n; j++) {
        		System.out.print(String.format("%s%2d ", weights[i][j] < 0 ? "-":" ",Math.abs(weights[i][j])));
        	}
        	System.out.println();
        }
        
        if (found) {
        	int prev = -1;
            for (int i : solution) {
            	if (prev != -1) {
            		int cost = weights[prev][i];
            		System.out.println(String.format("%2d -(%d)-> %2d", prev, cost, i));
            	}
            	prev = i;
            }
            System.out.println("NullPath found: " + solution + " , with weight = " + weight);
        } else {
            System.out.println("No valid NullPath found.");
        }
    }

    /**
     * Recursive backtracking function.
     * @param current     The current node.
     * @param path        The current path of visited nodes.
     * @param currentCost The accumulated cost so far.
     * @param visited     Boolean array marking visited nodes.
     * @param lastSign    Sign of the previous edge (+1, -1, or 0 for start).
     */
    private void backtrack(int current, List<Integer> path, int currentCost, boolean[] visited, int lastSign) {
    	if (found) {return;} // stop if a solution has been found

        // Check for solution
        if (path.size() == n && current == end) {
            if (currentCost >= minTolerance && currentCost <= maxTolerance) {
                solution = new ArrayList<>(path);
                found = true;
                this.weight = currentCost;
            }
            return;
        }

        // Prune based on tolerance bounds and maximum possible future contributions
        int remaining = n - path.size();
        if (currentCost > maxTolerance && currentCost - remaining * maxWeight > maxTolerance) {
            return;
        }
        if (currentCost < minTolerance && currentCost + remaining * maxWeight < minTolerance) {
            return;
        }

        // Try next possible node, enforcing alternating signs
        for (int next = 0; next < n; next++) {
            if (!visited[next]) {
                int w = weights[current][next];
                int sign = (w >= 0) ? 1 : -1;
                // Enforce alternation of sign
                if (lastSign != 0 && sign == lastSign) {continue;}

                visited[next] = true;
                path.add(next);
                backtrack(next, path, currentCost + w, visited, sign);
                path.remove(path.size() - 1);
                visited[next] = false;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java NullPath <number of nodes>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        NullPath np = new NullPath(n);
        np.findNullPath();
    }
}

