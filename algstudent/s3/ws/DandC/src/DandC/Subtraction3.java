package DandC;

/* Class that models T(n)=2 T(n-1)+O(1)
 * Params: a=2;b=1;k=0
 * The time complexity is quadratic O(2^n) 
 * and the waste of stack is O(n)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction3 {
	public static long rec3(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			cont++; // O(1)
			rec3(n - 1);
			rec3(n - 1);
		}
		return cont;
		/* a = 2 - b = 1 - k = 0 */
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		for (int n = 1; n <= 100; n++) {
			t1 = System.currentTimeMillis();

			cont = rec3(n);

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class