package s0;

import java.util.ArrayList;

public class JavaA1 {
	public JavaA1() {
		int n = 10000;
		
		for(int i=0; i<7; i++) {
			long t1 = System.currentTimeMillis();
			ArrayList primes = primeList(n);
			long t2 = System.currentTimeMillis();
			int total = (int) (t2-t1);
			System.out.println("n =" + n + "***" + "time =" + total + " milliseconds");
			n *= 2;
		}		
	}

	private ArrayList primeList(int n) {
		ArrayList primes = new ArrayList();
		for(int i =2; i<n+1;i++) {
			if(primoA1(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	private boolean primoA1(int n) {
		boolean p = true;
		
		for(int i = 2; i<n;i++) {
			if(n%i ==0) {
				p = false;
			}
		}
		return p;
	}
}
