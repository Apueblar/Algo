package labs.examples.dandc.closest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class ClosestPoints {
	private List<Point> points; //class to work with points
	
	public ClosestPoints() {
		this.points = new ArrayList<Point>();
	}
	  
	public void loadPoints(String name) {
		String line = "";
		StringTokenizer pair; //to get the two parts of each line (x and y coordinate of one point)
		BufferedReader reader = null; //to read each line of the file
		try {
			reader = new BufferedReader(new FileReader(name));
			int n = Integer.parseInt(reader.readLine()); //in the first line of the file we have the number of points 
			for (int i=0; i<n; i++) {
				line = reader.readLine();
				pair = new StringTokenizer(line);
				Point point = new Point(Integer.parseInt(pair.nextToken()), Integer.parseInt(pair.nextToken()));
				points.add(point); //points between 0..999999
			}
			reader.close();
		} catch(Exception e) { 
			 System.out.println(e.getMessage()); 
		}
	}
	
	public void loadRandomPoints(int n) {
		Random r = new Random();
		for (int i=0; i<n; i++) {
			int x = r.nextInt(1_000_000);
			int y = r.nextInt(1_000_000);
			points.add(new Point(x, y)); 
		}
	}
	  
	public void printPoints() {
		System.out.println("\n*******************************");
		System.out.println("SIZE OF THE CLOUD OF POINTS = " + points.size());
		System.out.println("POINTS ARE:");
		for (Point point : points)
			System.out.println(point);
		System.out.println();
	}
	
	//SOLUTION USING BRUTE FORCE - O(n^2)
	public Pair bruteForce() {
		return this.bruteForce(this.points);
	}
	
	private Pair bruteForce(List<Point> points) {
		Pair closestPoints = new Pair(points.get(0), points.get(1)); //the 2 first points by default
		closestPoints.setDistance(calculateDistance(points.get(0), points.get(1)));
		
		for (int i=0; i<points.size(); i++) {
			Point p1 = points.get(i);
			for (int j=0; j<points.size(); j++) {
				if (i != j) { //if it is not the same point
					Point p2 = points.get(j);
					double distance = calculateDistance(p1, p2);
					if (distance < closestPoints.getDistance())
						closestPoints.update(p1, p2, distance);
				}
			}
		}
		
		return closestPoints;
	}

	//CALCULATION OF THE DISTANCE BETWEEN 2 POINTS
	private double calculateDistance(Point p1, Point p2) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	//SOLUTION USING DIVIDE AND CONQUER
	public Pair divideAndConquer() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
