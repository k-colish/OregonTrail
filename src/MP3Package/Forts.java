package MP3Package;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Forts extends JFrame {

    private String itemFile = "/csv/Fort Stock.csv";
    private ArrayList<JCheckBox> itemCheckBoxes = new ArrayList<>();
    private JLabel moneyAmountLabel;
    private int moneyTotal = 100; 

    public Forts() {
        setTitle("Fort");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        moneyAmountLabel = new JLabel("Current money amount: " + moneyTotal);
        moneyAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        moneyAmountLabel.setBounds(10, 10, 300, 30);
        getContentPane().add(moneyAmountLabel);

        JButton buySelectedButton = new JButton("BUY");
        buySelectedButton.setBounds(150, 320, 100, 30);
        getContentPane().add(buySelectedButton);
        buySelectedButton.addActionListener(e -> buySelectedItems());

        loadItemsFromCSV();
    }

    private void loadItemsFromCSV() {
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

                        // Create checkbox and label for the item with cost
                        JCheckBox itemCheckBox = new JCheckBox(itemName + " - $" + cost);
                        itemCheckBox.setBounds(10, yOffset, 200, 30);
                        getContentPane().add(itemCheckBox);

                        itemCheckBoxes.add(itemCheckBox); // Add checkbox to list

                        yOffset += 40; // Increase vertical spacing
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


    private void buySelectedItems() {
        System.out.println("Buying selected items:");
        for (JCheckBox checkBox : itemCheckBoxes) {
            if (checkBox.isSelected()) {
                String itemName = checkBox.getText().split(":")[0];
                System.out.println("- " + itemName);
                spendMoney(checkBox);
            }
        }
    }

    private void spendMoney(JCheckBox checkBox) {
        String[] itemInfo = checkBox.getText().split(":");
        if (itemInfo.length == 2) {
            int cost = Integer.parseInt(itemInfo[1].trim());
            moneyTotal -= cost;
           moneyAmountLabel.setText("Current money amount: " + moneyTotal);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Forts fortsGame = new Forts();
            fortsGame.setVisible(true);
        });
    }
}
