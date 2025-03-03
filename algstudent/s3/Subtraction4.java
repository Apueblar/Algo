package DandC;

public class Subtraction4 {
	public static long rec4(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					cont++;
				}
			}
			rec4(n - 1);
		}
		return cont;
	}
	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		
		for (int n = 100; n <= 100000000; n*=2) {
			t1 = System.currentTimeMillis();
			
			for (int i = 0; i < 1; i++) { // To repeat for a better time check
				cont = rec4(n);
			}
			

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main/ main
}
