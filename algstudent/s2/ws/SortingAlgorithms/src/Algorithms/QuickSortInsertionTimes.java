package Algorithms;

public class QuickSortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		String opcion = arg[0];

		for (int n = 16000000; n <= 1000000000; n *= 2) {
			v = new int[n];

			if (opcion.compareTo("ordered") == 0)
				Vector.sorted(v);
			else if (opcion.compareTo("reverse") == 0)
				Vector.reverseSorted(v);
			else
				Vector.randomSorted(v);

			t1 = System.currentTimeMillis();

			QuicksortInsertion.quicksortInsertion(v, 1000);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
