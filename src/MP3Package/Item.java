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
	private int amount;	  // The amount of Item
    private String name;      // The name item
    private double price;     // The price or value of the item
	
	
	/**
	 *  Item - constructor for Item objects
	 * @param name - the name of the item
	 * @param price - the price of the item
	 */
	public Item(int amount, String name, double price) {
		this.amount = amount;
		this.name = name;
		this.price = price;
	}
	
	/**
	 * getName - gets the name of the Item
	 * @return name - the name of the Item
	 */
	public String getName() {return name;}
	
	/**
	 * getPrice - gets the price of the Item
	 * @return price - the price of the Item
	 */
	public double getPrice() {return price;}
	
	/**
	 * getAmount - gets the amount of an item you have
	 * @returns amount - the amount of an item you have
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * addAmount - adds an amount to the total number of the item
	 * @param x - the amount being added
	 * @return the new total of the item
	 */
	public int addAmount(int x) {
		amount += x;
		return amount;
	}
	
	/**
	 * removeAmount - removes an amount from the total number of the item
	 * @param x - the amount being removed
	 * @return the new total of the item
	 */
	public int removeAmount(int x) {
		amount -= x;
		if(amount < 0) {
			amount = 0;
		}
		return amount;
	}
}
