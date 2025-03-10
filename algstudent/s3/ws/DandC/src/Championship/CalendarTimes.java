package Championship;

import java.util.*;

public class CalendarTimes {

    public static void main(String[] args) {
        // Number of participants will start at 2 and double each time
    	long t1,t2,i = 0;
		for (int n=2;n<=10000000;n*=2)
		{
			List<String> participants = generateRandomParticipants(n);
			
			int repes = 10;
			t1 = System.currentTimeMillis ();
			
			for (int reps = 0; reps < repes; reps++) {
				String[][] schedule = new String[n][n];
	            Calendar.generateSchedule(participants, schedule);
			}
            
			t2 = System.currentTimeMillis ();
			
			i++;
			System.out.println ("n^"+i+ "**TIME="+ ((double)t2-t1)/repes + "**REAL="+(t2-t1));
		}
    }

    // Method to generate random participants
    public static List<String> generateRandomParticipants(int n) {
        List<String> participants = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            participants.add("Player" + i);
        }
        return participants;
    }
}
