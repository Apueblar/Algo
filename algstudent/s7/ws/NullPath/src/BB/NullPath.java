package BB;

import java.util.Random;

public class NullPath extends BranchAndBound {
	private int[][] weights;
	private int n;
	
	public final static int MAXWEIGHT = 99;
	public final static int MINWEIGHT = 10;
	public final static double P1 = 0.5;
	public final static double PINF = 0.2; // Prob of infinite weight
	public final static int INFINITE = Integer.MAX_VALUE;
	public final static int TOLERANCE = 99;
	
	public NullPath(int n) {
		this.n = n;
		this.weights = new int[n][n];
		generateWeights();

		this.rootNode = new NullPathNode(weights);
	}
	
	// Generate the random weight matrix according to the specified probabilities and ranges.
    private void generateWeights() {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // ignore self-loops
                if (rand.nextDouble() <= P1) {
                    weights[i][j] = rand.nextInt(MAXWEIGHT + 1 - MINWEIGHT) + MINWEIGHT;
                } else {
                    weights[i][j] = -(rand.nextInt(MAXWEIGHT + 1 - MINWEIGHT) + MINWEIGHT);
                }
            }
        }
    }
	
	public static void main(String[] args) {
		if (args.length < 1) {
            System.err.println("Usage: java BB.MyProblem <n>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
		NullPath np = new NullPath(n);
		np.branchAndBound(np.getRootNode());
		np.printSolutionTrace();
	}
}
