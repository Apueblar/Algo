package DandC;

public class Division5 {
	public static long rec5 (int n)
	{
     long cont = 0;
	 if (n<=0) cont++;
	 else
	  { 
		cont++;
	    rec5(n/2);
	    rec5(n/2);
	    rec5(n/2);
	    rec5(n/2);
	  }
	 return cont;   
	}
	
	public static void main (String arg []) 
	{
		 long t1,t2,cont = 0;	 
		 for (int n=1000;n<=10000000;n*=2)
		 {
			  t1 = System.currentTimeMillis ();
			  for(int i = 0; i<1;i++) {
				  cont = rec5(n);
			  }
			  
			  t2 = System.currentTimeMillis ();
			
			  System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);	
		 }  // for
	} // main
}
