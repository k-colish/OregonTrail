/** 
 * Destinations.java
 * 
 * Represents destinations used in the Oregon Trail game, providing distance information
 * and store availability. This class also allows players to choose to stop and look around
 * at a destination.
 * 
 * This class is designed to be used with MP3.java to manage destination-related functionality.
 * 
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 4/9/24
 * 
 */

package MP3Package;

public class Destinations {
	//Initialize instance variables
	private int distance;        // Distance from the starting point
    private String name;         // Name of the destination
	
	/**
	 * Destinations - constructor for Destination objects. 
	 * @param distance - the distance from the starting point. 
	 * @param name - the name of the destination.
	 */
	public Destinations(int distance, String name) {
		this.distance = distance;
		this.name = name;
	}
	
	/**
	 * getDistance - gets the distance traveled from the starting point.
	 * @return - distance traveled from the starting point.
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * getName - gets the name of the destination. 
	 * @return - the name of the destination. 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * lookAround - gets if the player wants to stop and look around. (Currently not finished)
	 * @param choice - the choice the player makes
	 * @return - (For now) true if player selects "Y" or "y", otherwise returns false.
	 */
	public boolean lookAround(char choice) {
		
		if(choice == 'y' || choice == 'Y')
			return true;
		else
			return false;
	}
}
