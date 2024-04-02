/** Wagon.java
 * 
 * Class to be used with MP3.java, that simulates adding items to a wagon. This class tracks the total weight of all items
 * and food items that have been added to the wagon. This class also calculates whether there is enough food to make
 * it to Oregon, depending on the food consumption rate and miles traveled per day, as set by the user.
 * 
 * @author - Kaiden Colish
 * @version - 1.0.0 - 3/25/24
 */
package MP3Package;


import java.util.ArrayList;
;

public class Wagon {
	// Initialize instance variables
	private int totalFoodWeight = 0;
	private int totalWeight = 0;
	private int foodConsumption;
	private int milesPerDay;
	private int totalDays;
	// Initialize ArrayList of Item object that contains all of the items that have been added to the wagon
	private ArrayList<Item> Items = new ArrayList<Item>();
	
	// Initialize ArrayList of FoodItem objects that contains all of the FoodItems that have been added to the wagon
	private ArrayList<FoodItem> food = new ArrayList<>();
	
	
	/**
	 *  addItem - Takes an Item object and adds it to the ArrayList of items that are in the wagon.
	 * @param item - the item to be added to the wagon.
	 */
	public void addItem(Item item) {
		Items.add(item);
		System.out.println("Item added");
		
		// If the item is a FoodItem, add it to the food ArrayList
		if(item.getIsFood()) {
			food.add( (FoodItem) item);
			System.out.println("Food item added");
		}
		// Prints out the list of items in the wagon for debugging purposes
		for(int i = 0; i < Items.size(); i++) {
			System.out.print(Items.get(i).getName() + ", ");
		}
		System.out.println();
	}
	
	/**
	 *  removeItem - Takes and item and finds the matching item that is in the wagon, and removes it from the ArrayList of
	 *  items in the wagon
	 * @param item - the item to be removed from the wagon
	 */
	public void removeItem(Item item) {
		for(int i = 0; i < Items.size(); i++) {
			final int j = i; // Make compiler happy

			// Loop through items that are in the wagon, comparing the name of each item with the item passed to find a match
			if(item.getName() == Items.get(j).getName()) {
				// When a match is found, remove the item from the ArrayList
				Items.remove(j);
				
				// Check if the item is a food item and remove it from the food ArrayList
				if(item.getIsFood()) {
					food.remove( (FoodItem) item);
				}
			}
		}
		System.out.println("Item removed");
		
		// Prints out the list of items in the wagon for debugging purposes
		for(int i = 0; i < Items.size(); i++) {
			System.out.print(Items.get(i).getName() + ", ");
		}
		System.out.println();
	}
	
	/**
	 *  setMilesPerDay - sets the miles traveled per day for travel calculations
	 * @param miles - the number of miles to be traveled each day
	 */
	public void setMilesPerDay(int miles) {
		milesPerDay = miles;
	}
	
	/**
	 *  getMilesPerDay - gets the number of miles traveled per day
	 * @return - milesPerDay - the number of miles traveled per day as an int
	 */
	public int getMilesPerDay() {
		return milesPerDay;
	}
	
	/**
	 * setFoodConsumption - sets the food consumption rate for travel calculations
	 * @param food - the rate at which each person consumes food each day
	 */
	public void setFoodConsumption(int food) {
		foodConsumption = food;
	}
	
	/**
	 * getFoodConsumption - gets the amount of food each person consumes each day
	 * @return -  foodConsumption - the rate at which food is consumed as an int
	 */
	public int getFoodConsumption() {
		return foodConsumption;
	}
	
	/**
	 * calculateTotalWeight - calculates the total weight of all items that have been added to the wagon
	 * @return -  totalWeight - the total weight of all items
	 */
	public int calculateTotalWeight() {
		totalWeight = 0;
		for(int i = 0; i < Items.size(); i++) {
			totalWeight += Items.get(i).getWeight();
		}
		return totalWeight;
	}
	
	/**
	 * getTotalFoodWeight - calculates the total weight of all food items that have been added to the wagon
	 * @return - totalFoodWeight - the total weight of all food items
	 */
	public int getTotalFoodWeight() {
		totalFoodWeight = 0;
		for(int i = 0; i < food.size(); i++) {
			totalFoodWeight += food.get(i).getWeight();
		}
		return totalFoodWeight;
	}
	
	/**
	 * getTotalDays - calculates the total number of days it will take to reach Oregon
	 * @return totalDays -  the number of days it takes to reach Oregon
	 */
	public int getTotalDays() {
		// Divide the distance (2,200 miles) by the miles traveled per day 
		totalDays = 2200 / milesPerDay;
		
		// If there is any remainder, add another day to reach Oregon
		if(2200 % milesPerDay > 0) totalDays++;
		return totalDays;
	}
	
	/**
	 * travel - calculates whether there is enough food to make it to Oregon given the food
	 * consumption rate and total days to travel.
	 * @return - false if the total food required is greater than the total food on the wagon, and true otherwise.
	 */
	public boolean travel() {
		// Calculate the food consumed per day, (4 people * consumption rate)
		int foodPerDay = 4 * foodConsumption;
		
		// Calculate the total food used using the food used per day times the number of days to travel.
		int totalFoodUsed = foodPerDay * this.getTotalDays();
		
		// If the total food used is greater than the amount of food on the wagon, return false (lose condition)
		if(totalFoodUsed > totalFoodWeight) {
			return false;
		}
		
		// Otherwise, return true (win condition)
		else return true;
	}
}
