/** 
 * River.java 
 * 
 * The River class is used to create objects that hold the properties of the rivers along the Oregon Trail 
 * that the user will need to cross on their journey. This class also includes the options to cross the river: ford, float, or take a ferry.
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 3/25/24
 */
package MP3Package;

import java.util.Random;

public class River extends Destinations{
	
	// General Instance Variables
	private double depth;
	private double width;
	private double lossSeverity;
	private int flow;
	private Random rnd = new Random();
	
	
	/**
	 * River - constructor for the River class. passes distance, name, and 'false' as hasStore into the Destinations constructor
	 * @param distance - the distance from the start of the trail
	 * @param name -  the name of the river
	 * @param depth - the depth of the river
	 * @param width - the width of the river
	 * @param flow - the speed of the current of the river
	 */
	public River(int distance, String name) {
		super(distance, name, false);
		this.depth = rnd.nextDouble(1, 6);
		this.width = rnd.nextDouble(300, 700);
		this.flow = rnd.nextInt(1, 4);
	}
	
	/**
	 * getDepth - returns the depth of the river
	 * @return - the depth of the river
	 */
	public double getDepth() {
		return depth;
	}
	
	/**
	 * getFlow - returns the flow of the river
	 * @return - the flow of the river
	 */
	public int getFlow() {
		return flow;
	}
	
	/**
	 * getWidth - returns the width of the river
	 * @return - the width of the river
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * getLossSeverity - returns the lossSeverity after an attempt to cross has been made
	 * @return - the value of lossSeverity
	 */
	public double getLossSeverity() {
		return lossSeverity;
	}
	
	
	/**
	 * fordRiver - determines whether the wagon makes it across the river given the conditions of the river
	 * @return - true if the wagon makes it across, false otherwise
	 */
	public boolean fordRiver() {
		
		if(this.depth <= 2.5) {
			return true;
		}
		else{
			this.lossSeverity = this.depth;
			return false;
		}
	}
	
	/**
	 * floatRiver - determines whether the wagon makes it across the river given the conditions of the river
	 * @return - true if the wagon makes it across, false otherwise
	 */
	public boolean floatRiver() {
		if(flow > 2) {
			lossSeverity = this.depth;
			return false;
		}
		else return true;
	}
	
	/**
	 * takeFerry - determines the cost of the ferry, determined by the distance from the start of the trail
	 * @return - the cost of the ferry
	 */
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
