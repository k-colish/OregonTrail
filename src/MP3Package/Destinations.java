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

import java.io.InputStreamReader;
import java.util.Scanner;

public class Destinations {
	//Initialize instance variables
	private int distance;        // Distance from the starting point
    private String name;         // Name of the destination
    private boolean hasStore;    // Indicates whether the destination has a store
    private int temp;
    private double precip;
	
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
		
		InputStreamReader reader = null;
		Scanner in = null;
		String destFile = "/csv/Temperature.csv";

		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(destFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}

		// Create a InputStreamReader Scanner to read in the CSV file
		in = new Scanner(reader);
		
		while(in.hasNext()) {
			Scanner tempData = new Scanner(in.nextLine());
			
			String csvName = tempData.next();
			int totalTemp = 0;
			int tempCount = 0;
			if(csvName == this.name) {
				while(tempData.hasNextInt()) {
					totalTemp += tempData.nextInt();
					tempCount++;
				}
				this.temp = totalTemp / tempCount;
			}
		}
		
		destFile = "/csv/Precipitation.csv";
		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(destFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}
		
		// Create a InputStreamReader Scanner to read in the CSV file
				in = new Scanner(reader);
				
				while(in.hasNext()) {
					Scanner precipData = new Scanner(in.nextLine());
					
					String csvName = precipData.next();
					int totalPrecip = 0;
					int precipCount = 0;
					if(csvName == this.name) {
						while (precipData.hasNextInt()) {
							totalPrecip += precipData.nextInt();
						 precipCount++;
						}
						this.precip = totalPrecip / precipCount;
					}
				}
		
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
	 * getTemp - gets the average temperature of the destination
	 * @return temp - the average temperature of the destination
	 */
	public int getTemp() {
		return temp;
	}
	
	/**
	 * getPrecipitation - gets the average precipitation for the destination
	 * @return precip - the average precipitation for the destination
	 */
	public double getPrecipitation() {
		return precip;
	}
}
