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

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Trading {
	private int tradingIndex;
	private int yourIndex;
	
	/**
	 * Trading - Constructor for Trading objects. Opens a JOptionPane with a trade opportunity, giving the user a choice on whether or not
	 * 			they want to trade.
	 * @param items - The ArrayList of all items that can be put in the wagon. (Subject to change)
	 */
	public Trading(Wagon wagon) {
		Random rnd = new Random();
		
		System.out.println(wagon.getItems().size());
		tradingIndex = rnd.nextInt(wagon.getItems().size());
		yourIndex = rnd.nextInt(wagon.getItems().size());
		 
		Item traderItem = wagon.getItems().get(tradingIndex);
		Item yourItem = wagon.getItems().get(yourIndex);
		
		System.out.println("Trader has: " + wagon.getItems().get(tradingIndex).getName());
		System.out.println("Trader wants: " + wagon.getItems().get(yourIndex).getName());
		
		JOptionPane.showOptionDialog(null, "Someone would like to trade " +
				wagon.getItems().get(tradingIndex).getName() + " for " + wagon.getItems().get(yourIndex).getName() +
				". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, new Object[] {"Yes", "No"}, -1);
		
	}

}
