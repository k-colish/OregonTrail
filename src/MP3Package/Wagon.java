/** 
 * Wagon.java
 * 
 * Simulates a wagon used in the Oregon Trail game to carry items and food supplies.
 * This class tracks the total weight of items and food in the wagon, calculates food consumption,
 * and manages travel-related calculations such as distance traveled and days spent.
 * 
 * This class provides methods to add and remove items from the wagon, set travel parameters,
 * and perform travel calculations based on the set parameters.
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 3/25/24
 */

package MP3Package;

import javax.swing.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Wagon {
	// Initialize instance variables
	private int totalFood = 100;      	   // Total of all food in the wagon
    private int foodConsumption = 3;           // Food consumption rate per person per day
    private int milesPerDay = 20;               // Distance traveled per day in miles
    private int totalDays;                 // Total number of days required to reach the destination
    private int daysTraveled;              // Number of days the wagon has been traveling
    private int totalFoodUsed = 0;         // Total food consumed during travel
    private int milesTraveled = 0;         // Total miles traveled
	RandomEvents events;
    
	// Initialize ArrayList of Item object that contains all the items that have been added to the wagon
	private ArrayList<Item> Items = new ArrayList<Item>();


	// Initialize ArrayList of Destinations objects that contains all the Destinations that can be visited
	private ArrayList<Destinations> destinations = new ArrayList<>();


	public Wagon()
	{
		InputStreamReader reader = null;
		Scanner in = null;
		String itemFile = "/csv/Destinations.csv";

		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(itemFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}

		// Create a InputStreamReader Scanner to read in the CSV file
		in = new Scanner(reader);

		while(in.hasNext()) {
			// Create a new Scanner with ", " as the delimiter
			Scanner destData = new Scanner(in.nextLine());
			destData.useDelimiter(",");

			String tempName = "";
			int distance = 0;
			boolean hasStore = false;

			tempName = destData.next();
			distance = destData.nextInt();
			if(destData.nextInt() == 1) {
				hasStore = true;
			}

			Destinations destination = new Destinations(distance, tempName, hasStore);
			destinations.add(destination);
		}

		in.close();
		events = new RandomEvents(this);
	}

	/**
	 *  addItem - Takes an Item object and adds it to the ArrayList of items that are in the wagon.
	 * @param item - the item to be added to the wagon.
	 */
	public void addItem(Item item)
	{
		Items.add(item);
		System.out.println("Item added");

		// Prints out the list of items in the wagon for debugging purposes
		for(int i = 0; i < Items.size(); i++) {
			System.out.print(Items.get(i).getName() + ", ");
		}
		System.out.println();
	}

	public void addItemAmount(String itemName, int amount) {
		for(int i = 0; i < Items.size(); i++) {
			if(itemName == Items.get(i).getName()) {
				Items.get(i).addAmount(amount);
			}
		}
	}
	
	public void removeItemAmount(String itemName, int amount) {
		for(int i = 0; i < Items.size(); i++) {
			if(itemName == Items.get(i).getName()) {
				Items.get(i).removeAmount(amount);
			}
		}
	}

	public int sizeItems() {return Items.size();}

	public ArrayList<Item> getItems() {return Items;}
	
	/**
	 * setMilesPerDay - sets the miles traveled per day for travel calculations
	 * @param miles - the number of miles to be traveled each day
	 */
	public void setMilesPerDay(int miles) {milesPerDay = miles;}
	
	/**
	 * getMilesPerDay - gets the number of miles traveled per day
	 * @return - milesPerDay - the number of miles traveled per day as an int
	 */
	public int getMilesPerDay() {return milesPerDay;}
	
	/**
	 * setFoodConsumption - sets the food consumption rate for travel calculations
	 * @param food - the rate at which each person consumes food each day
	 */
	public void setFoodConsumption(int food) {foodConsumption = food;}
	
	/**
	 * getFoodConsumption - gets the amount of food each person consumes each day
	 * @return -  foodConsumption - the rate at which food is consumed as an int
	 */
	public int getFoodConsumption() {return foodConsumption;}
	
	/**
	 * getTotalFood - calculates the total amount of food that has been added to the wagon
	 * @return - totalFood - the total amount of food that has been added to the wagon
	 */
	public int getTotalFood()
	{
		if(totalFood < totalFoodUsed) {
			totalFoodUsed = totalFood;
		}
		System.out.println(totalFoodUsed);
		return totalFood - totalFoodUsed;
	}

	public void changeTotalFood(int amount)
	{
		if ((totalFood + amount )< 0)
			totalFood = 0;
		else
		 totalFood += amount;
	}
	
	/**
	 * getTotalDays - calculates the total number of days it will take to reach Oregon
	 * @return totalDays -  the number of days it takes to reach Oregon
	 */
	public int getTotalDaysTraveled() {return daysTraveled;}
	
	/**
	 * getMilesTraveled - gets the number of miles traveled. 
	 * @return - the number of miles traveled. 
	 */
	public int getMilesTraveled() {return milesTraveled;}
	
	public Destinations getNextLandmark() {
		for(int i = 0; i < destinations.size(); i++) {
			if(milesTraveled >= destinations.get(i).getDistance() &&
					milesTraveled < destinations.get(i + 1).getDistance()) {return destinations.get(i + 1);}
		}
		return destinations.get(1);
	}
	

	public int addDays(int x) {
			
			daysTraveled += x;
			return daysTraveled;
			
		}
	
	/**
	 * travel - keeps track of the number of days the party has been traveling for. 
	 * Also determines the amount of food consumed by the party and if the party has enough food to survive
	 * @return - the number of days traveled
	 */
	public int travel() {
		// Calculate the food consumed per day, (4 people * consumption rate)
		int foodPerDay = 4 * foodConsumption;
		events.allEvents();

		daysTraveled++;
		
		// Calculate the total food  using the food used per day times the number of days to travel.
		totalFoodUsed += foodPerDay;
		
		milesTraveled += milesPerDay; // Update total miles traveled
		
		return daysTraveled;
	}
	
	/**
	 * milesToLandmark - calculates the miles to the next landmark
	 * @return - the distance to the next landmark
	 */
	public int milesToLandmark() {
		for(int i = 0; i < destinations.size(); i++) {
			if(milesTraveled >= destinations.get(i).getDistance() && milesTraveled <
					destinations.get(i + 1).getDistance()) {
				return destinations.get(i + 1).getDistance() - milesTraveled;
			}
		}
		return 0;
	}
	
	public int rest() {
		People names = new People();
		Health med = new Health(names, this, events);
		RandomEvents rnd = new RandomEvents(this);
		int days = 0;
		Integer[] options = {0,1,2,3,4,5,6,7,8,9,10};
		do {
			days = (int) JOptionPane.showInputDialog(null, "How many days would you like to rest?", "Resting", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		}while(!(days >= 0 && days <= 10));
		addDays(days);
		for (int i = 0; i < days; i++)
		{
			med.restNeeds();
			rnd.restEvents();
		}
		return days;
	}
	
	public Destinations atDestination() {
		int nextMiles = milesTraveled + milesPerDay;
		Destinations dest = null;
		for(int i = 0; i < destinations.size(); i++) {
			if(nextMiles >= destinations.get(i).getDistance() && nextMiles < destinations.get(i).getDistance() + 19) {
				milesTraveled = destinations.get(i).getDistance();
				dest = destinations.get(i);
			}
		}
		return dest;
	}
}
