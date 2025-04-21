package BB;

import java.util.ArrayList;
import java.util.List;

public class NullPathTimesBB {
    public static void main(String[] args) {
        int startSize = 20;
        int increment = 5;
        int maxSize = 100;
        int trials = 100;

        System.out.println("GraphSize\tAverageTime (ms)");

        for (int n = startSize; n <= maxSize; n += increment) {
            long totalTime = 0;

            for (int i = 0; i < trials; i++) {
                // Create a new instance with random weights
                NullPathBB np = new NullPathBB(n);

                // Prepare root node using getter for end
                List<Integer> path = new ArrayList<>();
                path.add(NullPathBB.start);
                Node root = new Node(NullPathBB.start, path, 0, np.getWeights(), np.getEnd());

                long startTime = System.nanoTime();
                np.branchAndBound(root);
                long endTime = System.nanoTime();

                totalTime += (endTime - startTime);
            }

            double avgTimeMs = totalTime / 1e6 / trials;
            System.out.printf("%d\t\t%.3f%n", n, avgTimeMs);
        }
    }
}

