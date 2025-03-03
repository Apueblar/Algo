package labs.examples.dandc.closest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClosestPointsTest {
	@Test
	public void testCase01() {
		ClosestPoints closest = new ClosestPoints();
		closest.loadPoints("src/main/java/labs/examples/dandc/closest/case1.txt");
		closest.printPoints();
    	
    	Pair pairBruteForce = closest.bruteForce(); //closest points calculated using brute force
    	Pair pairDAndC = closest.divideAndConquer(); //closest points calculated using divide and conquer
    	
    	System.out.println("Solution with brute force: " + pairBruteForce);
    	System.out.println("Solution with divide and conquer: " + pairDAndC);
    	
    	assertEquals(String.valueOf(pairBruteForce.getDistance()), 
    			String.valueOf(pairDAndC.getDistance())); 
	}
	
	@Test
	public void testCase02() {
		ClosestPoints closest = new ClosestPoints();
		closest.loadPoints("src/main/java/labs/examples/dandc/closest/case2.txt");
		closest.printPoints();
    	
    	Pair pairBruteForce = closest.bruteForce(); //closest points calculated using brute force
    	Pair pairDAndC = closest.divideAndConquer(); //closest points calculated using divide and conquer
    	
    	System.out.println("Solution with brute force: " + pairBruteForce);
    	System.out.println("Solution with divide and conquer: " + pairDAndC);
    	
    	assertEquals(String.valueOf(pairBruteForce.getDistance()), 
    			String.valueOf(pairDAndC.getDistance()));  
	}
	
	@Test
	public void testCase03() {
		ClosestPoints closest = new ClosestPoints();
		closest.loadPoints("src/main/java/labs/examples/dandc/closest/case3.txt");
		closest.printPoints();
    	
    	Pair pairBruteForce = closest.bruteForce(); //closest points calculated using brute force
    	Pair pairDAndC = closest.divideAndConquer(); //closest points calculated using divide and conquer
		
    	System.out.println("Solution with brute force: " + pairBruteForce);
    	System.out.println("Solution with divide and conquer: " + pairDAndC);
    	
    	assertEquals(String.valueOf(pairBruteForce.getDistance()), 
    			String.valueOf(pairDAndC.getDistance()));  
	}
	
	@Test
	public void testCase04() {
		ClosestPoints closest = new ClosestPoints();
		closest.loadPoints("src/main/java/labs/examples/dandc/closest/case4.txt");
		closest.printPoints();
    	
    	Pair pairBruteForce = closest.bruteForce(); //closest points calculated using brute force
    	Pair pairDAndC = closest.divideAndConquer(); //closest points calculated using divide and conquer
		
    	System.out.println("Solution with brute force: " + pairBruteForce);
    	System.out.println("Solution with divide and conquer: " + pairDAndC);
    	
    	assertEquals(String.valueOf(pairBruteForce.getDistance()), 
    			String.valueOf(pairDAndC.getDistance()));  
	}
	
	@Test
	public void testCase05() {
		for (int n=10; n<Integer.MAX_VALUE; n*=2) {
			ClosestPoints closest = new ClosestPoints();
			closest.loadRandomPoints(n);
			
			long t1, t2, t3, t4;
			
			t1 = System.currentTimeMillis();
			Pair pairBruteForce = closest.bruteForce(); //closest points calculated using brute force
			t2 = System.currentTimeMillis();
			
			t3 = System.currentTimeMillis();
	    	Pair pairDAndC = closest.divideAndConquer(); //closest points calculated using divide and conquer
	    	t4 = System.currentTimeMillis();
	    	    	
	    	System.out.println("n=" + n + " - Solution with brute force: " + pairBruteForce + " in " + (t2-t1) + "ms");
	    	System.out.println("n=" + n + " - Solution with divid&conqu: " + pairDAndC + " in " + (t4-t3) + "ms\n");
	    	
	    	assertEquals(String.valueOf(pairBruteForce.getDistance()), 
	    			String.valueOf(pairDAndC.getDistance()));  
		} 
	}

}
