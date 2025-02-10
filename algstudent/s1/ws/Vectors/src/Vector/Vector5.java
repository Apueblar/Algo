package Vector;

public class Vector5 {
	static int[]v;
	static int[]w;
	
	public static void main(String arg []) {
		long repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		
		for (int n=10000; n<=Integer.MAX_VALUE; n*=2){ //n is increased *5   
			  v = new int[n];
			  Vector1.fillIn(v);
			  w = new int[n];
			  Vector1.fillIn(w);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     Vector1.maximum(v, w);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%.5f milliseconds NTIMES=%d\n", n, ((double)(t2-t1) / repetitions), repetitions);	
		}
	}
}
