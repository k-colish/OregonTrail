/**
 * Destinations.java
 * 
 * Class to be used with MP3.java that deals with getting distances from destinations and checks to see if a destination has a store.
 * This class also checks to see if the user would like to stop and look around at a location.
 * 
 * @author - Kaiden Colish
 * @version - 1.0.0 4/9/24
 * 
 */
package MP3Package;

public class Destinations {
	//Initialize instance variables
	private int distance;
	private String name;
	private boolean hasStore;
	
	/**
	 * Destinations - constructor for Destination objects. 
	 * @param distance - the distance from the starting point. 
	 * @param name - the name of the destination. 
	 * @param hasStore - is true if the destination has a store, is false if destination does not have a store. 
	 */
	public Destinations(int distance, String name, boolean hasStore) {
		this.distance = distance;
		this.name = name;
		this.hasStore = hasStore;
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
	 * lookAround - gets if the player wants to stop and look around. 
	 * @param choice - the choice the player makes
	 * @return - true if player selects "Y" or "y", otherwise returns false.
	 */
	public boolean lookAround(char choice) {
		
		if(choice == 'y' || choice == 'Y') {
			return true;
		}
		else{
			return false;
		}
	}
}
