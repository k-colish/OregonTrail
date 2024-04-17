package MP3Package;

import java.util.Random;

public class River extends Destinations{
	
	private double depth;
	private double width;
	private double lossSeverity;
	private int flow;
	
	public River(int distance, String name, double depth, double width, int flow) {
		super(distance, name, false);
		this.depth = depth;
		this.width = width;
		this.flow = flow;
	}
	
	public double getDepth() {
		return depth;
	}
	
	public int getFlow() {
		return flow;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getLossSeverity() {
		return lossSeverity;
	}
	
	public boolean fordRiver() {
		
		if(this.depth <= 2.5) {
			return true;
		}
		else{
			this.lossSeverity = this.depth - 2.5;
			return false;
		}
	}
	
	public boolean floatRiver() {
		Random rnd = new Random();
		int chanceToFail = rnd.nextInt(10) + 1;
		if(chanceToFail == 1) {
			lossSeverity = this.depth - 2.5;
			return false;
		}
		else return true;
	}
	
	public double takeFerry() {
		if(this.getDistance() < 800) {
			return 5;
		}
		else if(this.getDistance() < 1400) {
			return 8;
		}
		else {
			return 10;
		}
	}
}
