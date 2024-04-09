package MP3Package;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DestinationActivities{
	
	// Initialize variables for file reading
	private InputStreamReader reader = null;
	private Scanner in = null;
	
	// File path for csv with stranger quotes
	private String itemFile = "/csv/WagonDialogList.csv";
	
	
	/**
	 * talkToRandos - Chooses a random line from the csv file to display a quote taken from Margaret Frink's diary
	 */
	public void talkToRandos() {
		
		// Generate a new random number
		Random rnd = new Random();
		int line = rnd.nextInt(5) + 1;
		
		
		// Create new InputStreamReader
		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(itemFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}
		
		// Set the in Scanner to read in the InputStreamReader
		in = new Scanner(reader);
		
		
		while(in.hasNext()) {
			
			// Read in the first line from the csv
			Scanner itemData = new Scanner(in.nextLine());
			itemData.useDelimiter(", ");
			
			// Get the quote index
			int quote = itemData.nextInt();
			
			// Check if the random value equals the quote index
			if(quote == line) {
				
				// Display the quote when a matching index is found
				JOptionPane.showMessageDialog(null, itemData.next(), "Rando", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
