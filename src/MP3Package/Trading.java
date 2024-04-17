package MP3Package;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Trading {
	private int tradingIndex;
	private int yourIndex;
	
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
