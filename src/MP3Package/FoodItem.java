/** 
 * FoodItem.java
 * 
 * The FoodItem class represents a type of item that is edible and can be used as food.
 * It is a subclass of the Item class and adds functionality to differentiate between food items
 * and non-food items.
 * 
 * This class allows for the creation of specific items that are categorized as food,
 * providing methods to manage food-related attributes such as weight, name, and price.
 * 
 * 
 * @authors - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0
 */

package MP3Package;

public class FoodItem extends Item{
	
	/**
	 * FoodItem - the constructor for the FoodItem class. Calls the Item class constructor to create
	 * a new item
	 * @param weight - the weight of the food item in pounds
     * @param name - the name or description of the food item
     * @param price - the price or value of the food item
	 * 
	 */
	public FoodItem(int weight, String name, double price) {
		super(weight, name, price);
		
	}
}
