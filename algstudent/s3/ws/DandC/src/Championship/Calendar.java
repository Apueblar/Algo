package Championship;

import java.io.*;
import java.util.*;

public class Calendar {

    // Method to read participants from a file with the given path
    public static List<String> readParticipants(String filename) throws IOException {
        List<String> participants = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // The first line contains the number of participants
            reader.readLine(); // Skip the first line, as it's just the count

            // Read the participant names
            while ((line = reader.readLine()) != null) {
                participants.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            throw e;
        }
        return participants;
    }

    // Method to generate the schedule (Divide and Conquer strategy)
    public static void generateSchedule(List<String> participants, String[][] schedule) {
        int n = participants.size();
        // If the number of participants is not a power of two, adjust the number of days to be n
        if ((n & (n - 1)) != 0) {  // This checks if n is not a power of two
            
        }
        
        prepareForLoop(schedule, participants);

        // Generate the round-robin pairings
        generateRoundRobin(participants, schedule, 0, n - 1);
    }
    
    private static void prepareForLoop(String[][] schedule, List<String> participants) {
		for (int i = 0; i < participants.size(); i++) {
			schedule[i][0] = participants.get(i);
		}
	}

    // Method to generate the pairings using Divide and Conquer strategy
    private static void generateRoundRobin(List<String> participants, String[][] schedule, int start, int end) {
        int participantNum = end - start + 1;
        if (participantNum != 1) {
            int mid = (start + end) / 2;
            generateRoundRobin(participants, schedule, start, mid);
            
            int m = mid - start + 1; // number of participants in left half
            
            // Fill cross-match rounds for left-group teams.
            // These rounds start at index (m-1) in the schedule.
            for (int i = start; i <= mid; i++) {
                for (int j = 0; j < m; j++) {  // j goes from 0 to m-1 (the extra rounds)
                    // Change here: assign opponent from right half using cyclic rotation
                    schedule[i][j + m] = participants.get(mid + 1 + ((i - start + j) % m));
                }
            }
            
            generateRoundRobin(participants, schedule, mid + 1, end);
            
            // Fill cross-match rounds for right-group teams.
            for (int i = mid + 1; i <= end; i++) {
                for (int j = 0; j < m; j++) {  // j goes from 0 to m-1 (the extra rounds)
                    // Change here: assign opponent from left half using cyclic rotation
                    schedule[i][j + m] = participants.get(start + (((i - (mid + 1)) - j + m) % m));
                }
            }
        }
    }

    /*
     * Player	1
     * p1		p2
     * p2		p1
     * 
     * 
     * Player	1	2	3
     * p1		p2	p3	p4	
     * p2		p1	p4	p3
     * p3		p4	p1	p2
     * p4		p3	p2	p1
     */

    public static void main(String[] args) throws IOException {
        // Check if the user provided the file name argument
        if (args.length != 1) {
            System.out.println("Usage: java Championship.Calendar fileName.txt");
            return;
        }

        // Read the participants from the specified file
        String filename = args[0];
        String filePath = "src/Championship/" + filename; // Full path to the file
        
        List<String> participants = readParticipants(filePath);

        // Ensure the number of participants is valid (must be a power of two)
        int n = participants.size();
        if (n == 0 || (n & (n - 1)) != 0) {
            System.out.println("Error: The number of participants must be a power of two.");
            return;
        }
        
        String[][] schedule = new String[n][n];

        // Generate the schedule
        generateSchedule(participants, schedule);
        
        printSchedule(schedule);
    }

    private static void printSchedule(String[][] schedule) {
        // Now, use the total number of rounds, which is schedule[0].length
        System.out.println("PLAYER/OPPONENT" + getDayHeaders(schedule[0].length-1));
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[i].length; j++) {
                System.out.print(schedule[i][j] + "\t");
                if (j == 0) { System.out.print("\t"); }
            }
            System.out.println();
        }
    }
	
	// Helper method to generate the headers for the table
    private static String getDayHeaders(int numDays) {
        StringBuilder header = new StringBuilder();
        for (int i = 1; i <= numDays; i++) {
            header.append("\tDAY ").append(i);
        }
        return header.toString();
    }
}
