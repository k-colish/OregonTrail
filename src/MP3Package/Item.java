/** 
 * Item.java
 * 
 * Class to be used with MP3.java to implement individual items that can be added to an object of the Wagon class. 
 * This class serves as the base class for various types of items that can be included in the wagon's inventory.
 * 
 * Represents an individual item that can be added to a wagon in the Oregon Trail simulation game.
 * Each item has a specific weight, name, and can be categorized as a consumable food item.
 *
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 3/25/24
 */

package MP3Package;

public class Item {
	// Initialize instance variables
    private int weight;       // The weight of the item in pounds
    private String name;      // The name item
    private boolean isFood;   // Indicates whether the item is a consumable food item
    private double price;     // The price or value of the item
	
	
	/**
	 *  Item - constructor for Item objects
	 * @param weight -  the weight of the item
	 * @param name - the name of the item
	 * @param price - the price of the item
	 */
	public Item(int weight, String name,double price) {
		this.weight = weight;
		this.name = name;
		this.price = price;
	}
	
	/**
	 * setIsFood - sets whether the Item object is a food item
	 * @param food - If food == 0, the Item is not a food. Any value greater than or equal to 1 will set the Item as a food item.
	 */
	public void setIsFood(int food) {
		if(food >= 1) isFood = true;
		else if(food == 0) isFood = false;
	}
	
	/**
	 * getWeight - gets the weight of the Item
	 * @return weight - the weight of the Item
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * getName - gets the name of the Item
	 * @return name - the name of the Item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getPrice - gets the price of the Item
	 * @return price - the price of the Item
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * getIsFood - gets whether the Item is a food or not
	 * @return isFood - true if the Item is food, false if the the Item is not food
	 */
	public boolean getIsFood() {
		return isFood;
	}
}
