package MP3Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RandomEvents {
    private final ArrayList<String> items = new ArrayList<String>();

    public RandomEvents() {
        loadItemsFromCSV("/csv/Items.csv");
    }

    public void loadItemsFromCSV(String itemFilePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(itemFilePath));

            for (String line : lines) {
                // Trim any leading or trailing whitespace from the line
                String itemName = line.trim();

                // Add the item name to the list of items
                items.add(itemName);
            }
        } catch (IOException e) {
            // Handle IOException (e.g., if there's an issue reading the file)
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public ArrayList<String> getItems() {
        return items;
    }

}
