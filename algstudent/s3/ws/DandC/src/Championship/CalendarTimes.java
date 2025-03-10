package Championship;

import java.util.*;

public class CalendarTimes {

    public static void main(String[] args) {
        // Number of participants will start at 2 and double each time
        int maxSize = 32;  // Set an upper limit for testing (can be adjusted)
        
        // Test with participant numbers: 2, 4, 8, 16, 32...
        for (int n = 2; n <= maxSize; n *= 2) {
            // Generate random participants
            List<String> participants = generateRandomParticipants(n);
            
            // Measure time taken to generate the schedule
            long startTime = System.nanoTime();
            
            // Generate the schedule
            Calendar.generateSchedule(participants);
            
            long endTime = System.nanoTime();
            long duration = endTime - startTime;  // Time in nanoseconds
            
            // Print the results
            System.out.println("For " + n + " participants, time taken: " + duration + " nanoseconds.");
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
