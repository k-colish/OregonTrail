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
	private int amount;	  // The ammount of Item
    private String name;      // The name item
    private double price;     // The price or value of the item
	
	
	/**
	 *  Item - constructor for Item objects
	 * @param name - the name of the item
	 * @param price - the price of the item
	 */
	public Item(int ammount, String name,double price) {
		this.amount = ammount;
		this.name = name;
		this.price = price;
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
	 * getAmmount - gets the ammount of an item you have
	 * @returns ammount - the ammount of an item you have
	 */
	public int getAmount() {
		return amount;
	}
	public int setAmount(int x) {return amount + x;}
}
