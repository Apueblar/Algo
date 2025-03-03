package labs.examples.dandc.closest;

/**
 * Class to keep the value of each of the points (pair x and y)
 */
public class Point { 
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x; //x coordinate
		this.y = y; //y coordinate
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public String toString() {  
		return String.format("(%d, %d)", x, y);  
	}
} 
