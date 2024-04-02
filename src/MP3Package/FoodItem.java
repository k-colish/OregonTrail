/**
 * FoodItem.java
 * 
 * The FoodItem class is a subclass of the Item class, and is used to differentiate
 * between items that are food and items that are not food
 * 
 * @author - Kaiden Colish
 * @version - 1.0.0
 */
package MP3Package;

public class FoodItem extends Item{
	
	/**
	 * FoodItem - the constructor for the FoodItem class. Calls the Item class constructor to create
	 * a new item
	 * @param weight - the weight of the item
	 * @param name - the name of the item
	 */
	public FoodItem(int weight, String name) {
		super(weight, name);
		
	}
}
