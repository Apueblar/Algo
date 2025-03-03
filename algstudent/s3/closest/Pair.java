package labs.examples.dandc.closest;

public class Pair {
	private Point p1;
	private Point p2;
	private double distance;
	
	public Pair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public void update(Point p1, Point p2, double distance) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = distance;
	}
	
	public String toString() {
		return String.format("%s - %s = %.4f", p1, p2, distance);
	}
}
