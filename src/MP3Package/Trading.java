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
	public Trading(Wagon wagon, OregonTrail trail) {
		
		Random rnd = new Random();
		
		ArrayList <Item> items = wagon.getItems();
		
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
		
		
		
		
		
		System.out.println(traderItem.getAmount() + "Trader has: " + wagon.getItems().get(tradingIndex).getName());
		System.out.println(yourItem.getAmount() + "Trader wants: " + wagon.getItems().get(yourIndex).getName());
		
		//trading food for an item
		if(yourIndex==0 && tradingIndex!=2) {
			int tradingFoodForItem = JOptionPane.showOptionDialog(null, "Someone would like to trade 1 " +
					wagon.getItems().get(tradingIndex).getName() + " for " + randomFoodAmt + " " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(tradingFoodForItem == JOptionPane.YES_OPTION) {
				if(wagon.getTotalFood() >= randomFoodAmt) {
					wagon.addItemAmount(wagon.getItems().get(tradingIndex).getName(),1);
					wagon.changeTotalFood(-randomFoodAmt);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading item for food
		else if(yourIndex!=2 && tradingIndex==0) {
			int recievingFoodForItem = JOptionPane.showOptionDialog(null, "Someone would like to trade " + randomFoodAmt + " " +
					wagon.getItems().get(tradingIndex).getName() + " for 1 " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(recievingFoodForItem == JOptionPane.YES_OPTION) {
				if(yourItem.getAmount() > 1) {
					wagon.changeTotalFood(randomFoodAmt);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),1);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading clothing for an item
		else if(yourIndex==2 && tradingIndex!=0) {
			int tradingClothingForItem = JOptionPane.showOptionDialog(null, "Someone would like to trade 1 " +
					wagon.getItems().get(tradingIndex).getName() + " for " + randomClothingAmt + " " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(tradingClothingForItem == JOptionPane.YES_OPTION) {
				if(yourItem.getAmount() >= randomClothingAmt) {
					wagon.addItemAmount(wagon.getItems().get(tradingIndex).getName(),1);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),randomClothingAmt);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading item for clothing
		else if(yourIndex!=0 && tradingIndex ==2) {
			int recievingClothingForItem = JOptionPane.showOptionDialog(null, "Someone would like to trade " + randomClothingAmt + " " +
					wagon.getItems().get(tradingIndex).getName() + " for 1 " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(recievingClothingForItem == JOptionPane.YES_OPTION) {
				if(yourItem.getAmount() >= 1) {
					wagon.addItemAmount(wagon.getItems().get(tradingIndex).getName(),randomClothingAmt);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),1);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading clothing for food
		else if(yourIndex == 2 && tradingIndex == 0) {
			int tradingClothingForFood = JOptionPane.showOptionDialog(null, "Someone would like to trade " + randomFoodAmt + " " +
					wagon.getItems().get(tradingIndex).getName() + " for " + randomClothingAmt + " " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(tradingClothingForFood == JOptionPane.YES_OPTION) {
				if(yourItem.getAmount() >= randomClothingAmt) {
					wagon.changeTotalFood(randomFoodAmt);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),randomClothingAmt);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading food for clothing
		else if(yourIndex == 0 && tradingIndex == 2) {
			int recievingClothingForFood = JOptionPane.showOptionDialog(null, "Someone would like to trade " + randomClothingAmt + " " +
					wagon.getItems().get(tradingIndex).getName() + " for " + randomFoodAmt + " " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(recievingClothingForFood == JOptionPane.YES_OPTION) {
				if(wagon.getTotalFood() >= randomFoodAmt) {
					wagon.addItemAmount(wagon.getItems().get(tradingIndex).getName(),randomClothingAmt);
					wagon.changeTotalFood(-randomFoodAmt);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//trading an item for an item
		else {
			int ItemForItem = JOptionPane.showOptionDialog(null, "Someone would like to trade 1 " +
					wagon.getItems().get(tradingIndex).getName() + " for 1 " + wagon.getItems().get(yourIndex).getName() +
					". \nDo you want to trade?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] {"Yes", "No"}, -1);
			
			if(ItemForItem == JOptionPane.YES_OPTION) {
				if(yourItem.getAmount() >= 1) {
					wagon.addItemAmount(wagon.getItems().get(tradingIndex).getName(),1);
					wagon.removeItemAmount(wagon.getItems().get(yourIndex).getName(),1);
					
			}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the required items to make this trade",
		                    "NOPE", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		trail.updateLabels();
		
	}
}
