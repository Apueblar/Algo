package BB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NullPathBB {
    public static final int START      = 0;
    public static final int MAX_WEIGHT = 99;
    public static final int MIN_WEIGHT = 10;
    public static final double P1   = 0.5;

    private final int n;
    private final int end;
    private final int[][] weights;

    private final int tol = 99;

    private final Heap<Node> ds;
    private Node bestNode;

    public NullPathBB(int n) {
        this.n       = n;
        this.end     = n - 1;
        this.weights = new int[n][n];
        this.ds      = new Heap<Node>(n*n*1000000);
        generateWeights();
    }

    private void generateWeights() {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int w = rand.nextInt(MAX_WEIGHT + 1 - MIN_WEIGHT) + MIN_WEIGHT;
                weights[i][j] = (rand.nextDouble() <= P1) ? w : -w;
            }
        }
    }

    public int[][] getWeights() {
        return weights;
    }

    public int getEnd() {
        return end;
    }

    private int initialValuePruneLimit() {
        return tol;
    }

    public void branchAndBound(Node root) {
        ds.insert(root);
        int pruneLimit = initialValuePruneLimit();

        while (!ds.empty() && ds.estimateBest().getHeuristicValue() < pruneLimit) {
            Node node = ds.extractBestNode();
            for (Node child : node.expand()) {
                if (child.isSolution()) {
                    int cost = child.getHeuristicValue();
                    if (Math.abs(cost) <= tol) {
                        bestNode = child;
                        return;
                    }
                    if (cost < pruneLimit) {
                        pruneLimit = cost;
                        bestNode   = child;
                    }
                } else if (child.getHeuristicValue() < pruneLimit) {
                    ds.insert(child);
                }
            }
        }

        if (bestNode != null) {
            System.out.println("Best solution: " + bestNode.getPath() +
                               ", Total cost: " + bestNode.getCost());
        } else {
            System.out.println("No solution found.");
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java BB.NullPathBB <n>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        NullPathBB np = new NullPathBB(n);

        List<Integer> path = new ArrayList<>();
        path.add(START);
        Node root = new Node(START, path, 0, np.getWeights(), np.getEnd());
        np.branchAndBound(root);
    }
}
