package NP;

public class NullPathTimes {
    public static void main(String[] args) {

        int startSize = 20;
        int increment = 5;
        int maxSize = 1000000;
        int trials = 1000;
        
        System.out.println("GraphSize\tAverageTime (ms)");
        
        for (int n = startSize; n <= maxSize; n += increment) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                // Create a new instance.
                NullPath np = new NullPath(n);
                long startTime = System.nanoTime();
                np.findNullPath();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            double avgTimeMs = (totalTime / trials) / 1e6; // Convert nanoseconds to milliseconds
            System.out.println(n + "\t\t" + avgTimeMs);
        }
    }
}

