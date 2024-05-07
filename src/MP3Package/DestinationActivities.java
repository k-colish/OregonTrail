/**
 * DestinationActivities.java
 * 
 * Manages activities related to interactions with random characters or events during the Oregon Trail game.
 * This class allows players to engage in dialogue with random characters by displaying quotes from a CSV file.
 * 
 * This class is designed to be used with MP3.java to handle various destination activities.
 * 
 * @author Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version 1.0.0 - 4/9/24
 */

package MP3Package;

import javax.swing.*;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class DestinationActivities{
	
	// Initialize variables for file reading
	private InputStreamReader reader = null;
	private Scanner in = null;
	
	// File path for CSV with stranger quotes
	private String itemFile = "/csv/WagonDialogList.csv";
	
	
	/**
	 * talkToRandos - Chooses a random line from the CSV file to display a quote taken from Margaret Frink's diary
	 * Displays the chosen quote using a dialog window. 
	 */
	public void talkToRandos() {
		
		// Generate a new random number to select a line from the CSV
		Random rnd = new Random();
		int line = rnd.nextInt(9) + 1; // Randomly choose a line number (1 to 9)
		
		
		// Open the CSV file for reading using an InputStreamReader
		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(itemFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}
		
		// Set the in Scanner to read in the InputStreamReader
		in = new Scanner(reader);
		
		// Iterate through lines in the CSV file
		while(in.hasNext()) {
			
			// Read in the first line from the CSV
			Scanner itemData = new Scanner(in.nextLine());
			itemData.useDelimiter(", ");
			
			// Get the quote index
			int quote = itemData.nextInt();
			String q = itemData.next();
			
				// Message dialogs like to show long strings in one huge line across the screen.
				// Let's instead parse this string and add newline characters using a StringBuilder,
				// to split it up into a sizable paragraph.
				StringBuilder splitMessage = new StringBuilder();
				int charCount = 0;
				
		        for (int i = 0; i < q.length(); i++) {
		            char c = q.charAt(i);
		            splitMessage.append(c);
		            charCount++;
		            
		            // Only add new lines for spaces, which all avoid cutting into words
		            if (charCount >= 50 && c != ' ') {
		                int lastSpaceIndex = splitMessage.lastIndexOf(" ");
		                if (lastSpaceIndex != -1) {
		                	splitMessage.replace(lastSpaceIndex, lastSpaceIndex + 1, "\n");
		                    charCount = splitMessage.length() - lastSpaceIndex;
		                }
		            // If the character is a newline, reset charCount
		            } else if (c == '\n') {
		                charCount = 0;
		            }
		        }
				
				String title = "Dialogue";
			
			
			// Check if the random value equals the quote index
			if(quote == line) {
				
				// Display the quote when a matching index is found
				JOptionPane.showMessageDialog(null, splitMessage.toString(), "Rando", JOptionPane.ERROR_MESSAGE);
			}
			
			// Close the Scanner for the current line
            itemData.close();
		}
		
		// Close the Scanner for the CSV file
        in.close();
	}
}
