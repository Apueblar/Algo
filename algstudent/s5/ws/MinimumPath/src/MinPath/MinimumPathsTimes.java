package MinPath;

public class MinimumPathsTimes {
    
    public static void main(String[] args) {
        long t1, t2;
        
        int reps = 1;

        // Increase the size n doubling each time, starting from 200,
        // until n exceeds 1,000,000,000 (or until the execution time becomes too long).
        for (int n = 200; n <= 1000000000; n *= 2) {
            t1 = System.currentTimeMillis();
            
            for (int i = 0; i < reps; i++) {
            	MinimumPaths mp = new MinimumPaths(n);
            	mp.basicAlgorithm();
            }
            
            t2 = System.currentTimeMillis();
            
            System.out.println(n + "\t" + ((double)t2 - t1)/reps + " mS\t" + (t2 - t1) + " mS ref");
        }
    }
}
