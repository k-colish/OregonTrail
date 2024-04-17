package MP3Package;

import java.util.ArrayList;
import java.util.Random;

public class Trading {
	
	public Trading(ArrayList<Item> items) {
		Random rnd = new Random();
		//int traderIndex = 0;
		//int yourIndex = 0;
		
		int traderIndex = rnd.nextInt(items.size()) + 1;
		int yourIndex = rnd.nextInt(items.size()) + 1;
		
		
		 
		Item traderItem = items.get(traderIndex);
		Item yourItem = items.get(yourIndex);
		
		System.out.print("Random is: " + items.get(traderIndex));
		
		
		
	}

}
