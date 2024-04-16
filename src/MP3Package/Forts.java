package MP3Package;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;

public class Forts extends JFrame {

    private String itemFile = "/csv/WagonDialoglist.csv"; // Example CSV file name (change as needed)
    private ArrayList<JCheckBox> itemCheckBoxes = new ArrayList<>(); // List to hold item checkboxes

    public Forts() {
        // Set up the JFrame
        setTitle("Fort");
        setSize(400, 400); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when close button clicked
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JLabel moneyAmountLabel = new JLabel("Current money amount: ");
        moneyAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        moneyAmountLabel.setBounds(10, 10, 300, 30);
        getContentPane().add(moneyAmountLabel);
        
        // Button to buy selected items
        JButton buySelectedButton = new JButton("BUY");
        buySelectedButton.setBounds(150, 320, 100, 30);
        getContentPane().add(buySelectedButton);
        buySelectedButton.addActionListener(e -> buySelectedItems()); // Add action listener
        
        // Load items from CSV and display checkboxes
        loadItemsFromCSV();
    }

    private void loadItemsFromCSV() {
        // Open the CSV file for reading using an InputStreamReader
        try {
            // Obtain InputStream for the CSV file
            InputStream inputStream = getClass().getResourceAsStream(itemFile);
            if (inputStream == null) {
                System.err.println("CSV file not found: " + itemFile);
                return;
            }

            // Use BufferedReader to read the CSV file line by line
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int yOffset = 50; // Initial y-offset for positioning checkboxes
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Trim leading and trailing whitespace from the line
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                // Parse each line of CSV (assuming comma-separated values)
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String itemName = parts[0].trim(); // Item name (first part)
                    String quantityStr = parts[1].trim(); // Quantity as string (second part)

                    // Validate and parse quantity as integer
                    int quantity;
                    try {
                        quantity = Integer.parseInt(quantityStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid quantity format for item: " + itemName);
                        continue; // Skip this item if quantity is invalid
                    }

                    // Create JCheckBox for the item and add to JFrame
                    JCheckBox itemCheckBox = new JCheckBox(itemName + ": " + quantity);
                    itemCheckBox.setBounds(10, yOffset, 300, 30); // Set position
                    getContentPane().add(itemCheckBox); // Add JCheckBox to the JFrame
                    itemCheckBoxes.add(itemCheckBox); // Add to list

                    yOffset += 40; // Increase y-offset for next checkbox
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }

            bufferedReader.close(); // Close the reader
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    private void buySelectedItems() {
        // Handle buying selected items
        System.out.println("Buying selected items:");
        for (JCheckBox checkBox : itemCheckBoxes) {
            if (checkBox.isSelected()) {
                String itemName = checkBox.getText().split(":")[0].trim();
                System.out.println("- " + itemName);
                // Add logic here to process the purchase of the selected item
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of Forts
        Forts fortsGame = new Forts();
        fortsGame.setVisible(true);
    }
}
