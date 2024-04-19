package MP3Package;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Forts class represents a GUI application for managing items with associated costs.
 * Users can view items from a CSV file, select items to purchase, and update the displayed money amount accordingly.
 * 
 * @author Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version 1.0.0 - 4/16/24
 */
public class Forts extends JPanel {

    String itemFile = "/csv/FortStock.csv";
    private final ArrayList<JCheckBox> itemCheckBoxes = new ArrayList<>(); // List to hold checkboxes for each item loaded from the CSV file
    private final JLabel moneyAmountLabel; // Label to display the current amount of money available
    private double moneyTotal = 1000; // Total amount of money available to spend at start
    private final double fortcost = 6.5; // Multiplier to calculate item cost based on the base cost (e.g., fort cost)

    /**
     * Constructs a new Forts instance.
     * Sets up the JFrame and initializes components.
     */
    public Forts() {
    	setLayout(null); // Set layout to null for absolute positioning

        moneyAmountLabel = new JLabel("Current money amount: " + moneyTotal);
        moneyAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        moneyAmountLabel.setBounds(10, 10, 300, 30);
        add(moneyAmountLabel);

        JButton buySelectedButton = new JButton("BUY");
        buySelectedButton.setBounds(150, 320, 100, 30);
        add(buySelectedButton);
        buySelectedButton.addActionListener(e -> buySelectedItems());

        loadItemsFromCSV();
    }

    /**
     * Loads items from a CSV file and displays them as checkboxes with associated costs.
     */
    private void loadItemsFromCSV() {
        // Path to the CSV file containing item names and costs
        try (InputStream inputStream = getClass().getResourceAsStream(itemFile);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            int yOffset = 50;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String itemName = parts[0].trim();
                        int cost = 0;

                        try {
                            cost = Integer.parseInt(parts[1].trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid cost format for item: " + itemName);
                            continue; // Skip this item if cost is invalid
                        }

                        JCheckBox itemCheckBox = new JCheckBox(itemName + " - $" + cost * fortcost);
                        itemCheckBox.setBounds(10, yOffset, 200, 30);
                        add(itemCheckBox);

                        itemCheckBoxes.add(itemCheckBox);

                        yOffset += 40;
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                }
            }

            // Update UI on the Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                revalidate(); // Refresh the layout
                repaint();    // Repaint the JFrame
            });

        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Handles the action of buying selected items.
     * Deducts the cost of selected items from the total money amount.
     */
    private void buySelectedItems() {
        System.out.println("Buying selected items:");
        for (JCheckBox checkBox : itemCheckBoxes) {
            if (checkBox.isSelected()) {
                String itemInfo = checkBox.getText();
                String[] parts = itemInfo.split(" - \\$"); // Split using " - $" as separator
                if (parts.length == 2) {
                    String itemName = parts[0];
                    double itemCost = Double.parseDouble(parts[1]);
                    System.out.println("- " + itemName);

                    spendMoney(itemCost); // Deduct the item cost from moneyTotal
                }
            }
        }
    }

    /**
     * Deducts the specified cost from the current money amount.
     * Updates the money amount label to reflect the remaining money.
     *
     * @param cost the cost of the item to be deducted
     */
    private void spendMoney(double cost) {
        if (moneyTotal >= cost) {
            moneyTotal -= cost;
            moneyAmountLabel.setText("Current money amount: " + moneyTotal);
        } else {
            System.err.println("Insufficient funds to buy this item: " + cost);
        }
    }

    /**
     * Main method to start the Forts application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Forts fortsGame = new Forts();
            fortsGame.setVisible(true);
        });
    }
}
