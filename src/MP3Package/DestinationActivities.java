package MP3Package;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DestinationActivities{
	
	private InputStreamReader reader = null;
	private Scanner in = null;
	private String itemFile = "/csv/WagonDialoglist.csv";
	
	public void talkToRandos() {
		Random rnd = new Random();
		int line = rnd.nextInt(5) + 1;
		
		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(itemFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}
		in = new Scanner(reader);
		
		while(in.hasNext()) {
			Scanner itemData = new Scanner(in.nextLine());
			itemData.useDelimiter(", ");
			
			int quote = Integer.parseInt(itemData.next());
			
			if(quote == line) {
				JOptionPane.showMessageDialog(null, itemData.next(), "Rando", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void goToStore() {
		
	}
	
}
