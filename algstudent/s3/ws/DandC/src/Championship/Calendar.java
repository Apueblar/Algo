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
    public static void generateSchedule(List<String> participants) {
        int n = participants.size();
        int numDays = n - 1;  // If n is a power of 2, n - 1 days are needed; otherwise n days will be used.

        // If the number of participants is not a power of two, adjust the number of days to be n
        if ((n & (n - 1)) != 0) {  // This checks if n is not a power of two
            numDays = n;  // If not a power of 2, we use n days
        }

        // Initialize a schedule matrix
        String[][] schedule = new String[n][n];
        
        prepareForLoop(schedule, participants);

        // Generate the round-robin pairings
        generateRoundRobin(participants, schedule, 0, n - 1, numDays);

        // Print the header for the table
        System.out.println("PLAYER/OPPONENT   " + getDayHeaders(numDays));

        // Print the pairing schedule
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(schedule[i][j] + "\t"); // Opponent for each day
            }
            System.out.println();
        }
    }
    
 // Helper method to generate the headers for the table
    private static String getDayHeaders(int numDays) {
        StringBuilder header = new StringBuilder();
        for (int i = 1; i <= numDays; i++) {
            header.append("DAY ").append(i).append("\t");
        }
        return header.toString();
    }
    
    private static void prepareForLoop(String[][] schedule, List<String> participants) {
		for (int i = 0; i < participants.size(); i++) {
			schedule[i][0] = participants.get(i);
		}
	}

    // Method to generate the pairings using Divide and Conquer strategy
    private static void generateRoundRobin(List<String> participants, String[][] schedule, int start, int end, int numDays) {
    	int participantNum = end - start + 1;
        if (participantNum == 2) {
        	schedule[start][end-start] = participants.get(end);
        	schedule[end][end-start] = participants.get(start);
        } else {
        	int mid = (start + end) / 2;
        	generateRoundRobin(participants, schedule, start, mid, numDays);
        	generateRoundRobin(participants, schedule, mid+1, end, numDays);
        	

            
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
        String filePath = "C:\\Users\\uo299874\\Desktop\\Alg\\Algo\\algstudent\\s3\\ws\\DandC\\src\\Championship\\" + filename; // Full path to the file
        
        List<String> participants = readParticipants(filePath);

        // Ensure the number of participants is valid (must be a power of two)
        int n = participants.size();
        if (n == 0 || (n & (n - 1)) != 0) {
            System.out.println("Error: The number of participants must be a power of two.");
            return;
        }

        // Generate the schedule
        generateSchedule(participants);
    }
}
