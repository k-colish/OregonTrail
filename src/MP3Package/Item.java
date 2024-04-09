/** Item.java
 * 
 * Class to be used with MP3.java to implement individual items that can be added to an object of the Wagon class. 
 * Each item has a unique name, a weight, and whether or not it is a consumable food item with nutritional value.
 * 
 * @author - Kaiden Colish
 * @version - 1.0.0 - 3/25/24
 */
package MP3Package;

public class Item {
	// Initialize instance variables
	private int weight;
	private String name;
	private boolean isFood;
	
	
	/**
	 *  Item - constructor for Item objects
	 * @param weight -  the weight of the item
	 * @param name - the name of the item
	 */
	public Item(int weight, String name) {
		this.weight = weight;
		this.name = name;
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
	 * getIsFood - gets whether the Item is a food or not
	 * @return isFood - true if the Item is food, false if the the Item is not food
	 */
	public boolean getIsFood() {
		return isFood;
	}
}
