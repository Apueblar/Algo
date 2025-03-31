package NP;

import java.util.*;

public class NullPath {
    private int n;
    private int[][] weights;
    public final static int maxWeight = 99;
    public final static int minWeight = 10;
    
    public final static int start = 0;
    public int end;
    
    public final static double p1 = 0.5;
    
    private int weight;

    private int tolerance = 99; // [-tol, tol]
    private boolean found = false;
    private List<Integer> solution = new ArrayList<>();

    public NullPath(int n) {
        this.n = n;
        this.end = n-1;
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

    // Backtracking method to find a NullPath.
    public void findNullPath() {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        path.add(start);
        visited[start] = true;
        backtrack(start, path, 0, visited);
        /*
        if (found) {
            System.out.println("NullPath found: " + solution + " , with weight = " + weight);
        } else {
            System.out.println("No valid NullPath found.");
        }
        */
    }

    // Recursive backtracking function.
    private void backtrack(int current, List<Integer> path, int currentCost, boolean[] visited) {
        if (found) {return;} // stop if a solution has been found
        if (path.size() == n && current == end) { // If it has passed through all the nodes
            if (Math.abs(currentCost) <= tolerance) { // Check if the total cost is within the allowed tolerance.
                solution = new ArrayList<>(path);
                found = true;
                weight = currentCost;
            }
            return;
        }
        
        // Prune
        int remaining_nodes = n - path.size();
	     // If currentCost is positive, subtracting the maximum negative contribution.
	     if (currentCost > tolerance && currentCost - remaining_nodes * maxWeight > tolerance) { return; }
	
	     // If currentCost is negative, adding the maximum positive contribution.
	     if (currentCost < -tolerance && currentCost + remaining_nodes * maxWeight < -tolerance) { return; }

        // Try next possible node.
        for (int next = 0; next < n; next++) {
            if (!visited[next]) {
                visited[next] = true;
                path.add(next);
                int newCost = currentCost + weights[current][next];
                backtrack(next, path, newCost, visited);
                path.remove(path.size() - 1);
                visited[next] = false;
            }
        }
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java NullPath <number of nodes>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        NullPath np = new NullPath(n);
        np.findNullPath();
    }
}

