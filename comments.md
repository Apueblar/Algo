Quite good overall.

* Divide and Conquer is a organizational mess. Could use some cleaning up. (DONE)
* There are some problems when path building in Floyd. I did a try with the following matrix: (The thing I found is that my code works with Integer.MAX_VALUE not with 10000000, if you try now with INFINITE as i prepared it should work)
```
static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = 10000000;
		w[0][1] = 19;
		w[2][1] = 91;
		w[2][3] = 14;
		w[3][0] = 27;
		w[3][1] = 67;
		w[3][3] = 71;
	}
```
The costs matrix is alright, but it returns:
```
**************
FROM NODE2 TO NODE1 = NODE2-->NODE3-->NODE1
MINIMUM COST=60
**************
```
Instead of the expected:
```
**************
FROM NODE2 TO NODE1 = NODE2-->NODE3-->NODE0-->NODE1
MINIMUM COST=60
**************
```
