package BB;

public class NullPathTimes {
    public static void main(String[] args) {
        int startSize = 20;
        int increment = 5;
        int maxSize = 100;
        int trials = 100;

        System.out.println("GraphSize\tAverageTime (ms)\tTotal Time (ms)");
        
        for (int n = startSize; n <= maxSize; n += increment) {
            long totalTime = 0;

            for (int i = 0; i < trials; i++) {
            	
            	NullPath np = new NullPath(n);
                long startTime = System.nanoTime();
                np.branchAndBound(np.getRootNode());
                long endTime = System.nanoTime();
                System.out.print(i);
                totalTime += (endTime - startTime);
            }
            System.out.println("");

            double avgTimeMs = totalTime / 1e6 / trials;
            double totalTimeMs = totalTime / 1e6;
            String line = String.format("%d\t\t%.3f\t\t%.3f", n, avgTimeMs, totalTimeMs);
            System.out.println(line);
        }
    }
}

