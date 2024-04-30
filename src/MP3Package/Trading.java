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
	private int randomFoodAmt;
	private int randomClothingAmt;
	
	/**
	 * Trading - Constructor for Trading objects. Opens a JOptionPane with a trade opportunity, giving the user a choice on whether or not
	 * 			they want to trade.
	 * @param item 
	 * @param items - The ArrayList of all items that can be put in the wagon. (Subject to change)
	 */
	public Trading(Wagon wagon) {
		Random rnd = new Random();
		
		System.out.println(wagon.getItems().size());
		tradingIndex = rnd.nextInt(wagon.getItems().size());
		yourIndex = rnd.nextInt(wagon.getItems().size());
		
		System.out.println(tradingIndex);
		System.out.println(yourIndex);
		
		if(tradingIndex == yourIndex) {
			tradingIndex += 1;
		}
		if(tradingIndex > 5) {
			tradingIndex = tradingIndex - 2;
		}
		
		if(tradingIndex == 0 || yourIndex == 0) {
			randomFoodAmt = rnd.nextInt(20,50);
		}
		if(tradingIndex == 2 || yourIndex ==2) {
			randomClothingAmt = rnd.nextInt(1,4);
		}
		
		System.out.println("Random food amt is: " + randomFoodAmt);
		
		 
		Item traderItem = wagon.getItems().get(tradingIndex);
		Item yourItem = wagon.getItems().get(yourIndex);
		
		
		
		System.out.println("Trader has: " + wagon.getItems().get(tradingIndex).getName());
		System.out.println("Trader wants: " + wagon.getItems().get(yourIndex).getName());
		
		int res = JOptionPane.showOptionDialog(null, "Someone would like to trade " +
				wagon.getItems().get(tradingIndex).getName() + " for " + wagon.getItems().get(yourIndex).getName() +
				". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, new Object[] {"Yes", "No"}, -1);
		if(res == JOptionPane.YES_OPTION) {
			if(randomFoodAmt < wagon.getTotalFood()) {
				if(tradingIndex == 0) {
					wagon.changeTotalFood(randomFoodAmt);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),randomFoodAmt);
				}
				if(yourIndex == 0) {
					wagon.changeTotalFood(-randomFoodAmt);
					wagon.addItemAmount(wagon.getItems().get(yourIndex).getName(),randomFoodAmt);
				}
			}
			
		}
	}
}
