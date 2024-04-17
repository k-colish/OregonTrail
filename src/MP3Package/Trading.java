/** 
 * Trading.java 
 * 
 * Class to allow the user to trade while traveling the Oregon Trail. Randomly chooses two items, one that the trader wants and one the trader can give, 
 * that are used in the trade. The user can then choose whether they want to make the trade.
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 4/17/24
 */

package MP3Package;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Trading {
	private int tradingIndex;
	private int yourIndex;
	
	
	/**
	 * Trading - Constructor for Trading objects. Opens a JOptionPane with a trade opportunity, giving the user a choice on whether or not
	 * 			they want to trade.
	 * @param items - The ArrayList of all items that can be put in the wagon. (Subject to change)
	 */
	public Trading(ArrayList<Item> items) {
		Random rnd = new Random();
		
		tradingIndex = rnd.nextInt(items.size());
		yourIndex = rnd.nextInt(items.size());
		
		
		 
		Item traderItem = items.get(tradingIndex);
		Item yourItem = items.get(yourIndex);
		
		System.out.println("Trader has: " + items.get(tradingIndex).getName());
		System.out.println("Trader wants: " + items.get(yourIndex).getName());
		
		JOptionPane.showOptionDialog(null, "Someone would like to trade " + items.get(tradingIndex).getName() + " for " + items.get(yourIndex).getName() + ". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Yes", "No"}, -1);
		
	}

}
