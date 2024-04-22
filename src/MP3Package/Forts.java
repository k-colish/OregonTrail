package MP3Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final ArrayList<JRadioButton> itemButtons = new ArrayList<>();
    private final JLabel moneyAmountLabel; // Label to display the current amount of money available
    private double moneyTotal = 1000; // Total amount of money available to spend at start
    private final double fortcost = 6.5; // Multiplier to calculate item cost based on the base cost (e.g., fort cost)
    private static Wagon wagon = null;

    /**
     * Constructs a new Forts instance.
     * Sets up the JFrame and initializes components.
     */
    public Forts(Wagon wagon) {
    	setLayout(null); // Set layout to null for absolute positioning

        moneyAmountLabel = new JLabel("Current money amount: " + moneyTotal);
        moneyAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        moneyAmountLabel.setBounds(10, 10, 300, 30);
        add(moneyAmountLabel);
        this.wagon = wagon;

        JButton buySelectedButton = new JButton("BUY");
        buySelectedButton.setBounds(150, 320, 100, 30);
        add(buySelectedButton);

        loadItemsFromCSV();
    }

    /**
     * Loads items from a CSV file and displays them as checkboxes with associated costs.
     */
    private void loadItemsFromCSV() {
                        JOptionPane pane = null;
                        
                        JRadioButton rbtnFood = new JRadioButton("Food" + " - $" + (0.40 + 0.4 * (wagon.getMilesTraveled() / 10000)));
                        rbtnFood.setBounds(10, 50, 200, 30);
                        add(rbtnFood);
                        rbtnFood.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (0.40 + 0.4 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result = Double.parseDouble(input);
                				System.out.println(result);
                				spendMoney(itemCost,result);
                			}
                		});
                        
                        JRadioButton rbtnOxen = new JRadioButton("Yoke of Oxen" + " - $" + (40.00 + 40.00 * (wagon.getMilesTraveled() / 10000)));
                        rbtnOxen.setBounds(10, 90, 200, 30);
                        add(rbtnOxen);
                        rbtnOxen.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (40.00 + 40.00 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result1 = 0;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result1 = Double.parseDouble(input);
                				result = 2*result1;
                				System.out.println(result);
                				spendMoney(itemCost,result1);
                			}
                		});
                        
                        JRadioButton rbtnClothing = new JRadioButton("Clothing" + " - $" + (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
                        rbtnClothing.setBounds(10, 130, 200, 30);
                        add(rbtnClothing);
                        rbtnClothing.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result = Double.parseDouble(input);
                				System.out.println(result);
                				spendMoney(itemCost,result);
                			}
                		});
                        
                        JRadioButton rbtnWagonWheel = new JRadioButton("Wagon Wheel" + " - $" + (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
                        rbtnWagonWheel.setBounds(10, 170, 200, 30);
                        add(rbtnWagonWheel);
                        rbtnWagonWheel.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result = Double.parseDouble(input);
                				System.out.println(result);
                				spendMoney(itemCost,result);
                			}
                		});
                        
                        JRadioButton rbtnWagonTongue = new JRadioButton("Wagon Tongue" + " - $" + (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
                        rbtnWagonTongue.setBounds(10, 210, 200, 30);
                        add(rbtnWagonTongue);
                        rbtnWagonTongue.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result = Double.parseDouble(input);
                				System.out.println(result);
                				spendMoney(itemCost,result);
                			}
                		});
                        
                        JRadioButton rbtnWagonAxle = new JRadioButton("Wagon Axle" + " - $" + (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
                        rbtnWagonAxle.setBounds(10, 250, 200, 30);
                        add(rbtnWagonAxle);
                        rbtnWagonAxle.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				double itemCost = (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000));
                				String input;
                				double result;
                				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
                				result = Double.parseDouble(input);
                				System.out.println(result);
                				spendMoney(itemCost,result);
                			}
                		});

                        
                     // Initializing a button group, so only one radio button can be selected at one time
                		ButtonGroup storeButtons = new ButtonGroup();
                		storeButtons.add(rbtnFood);
                		storeButtons.add(rbtnOxen);
                		storeButtons.add(rbtnClothing);
                		storeButtons.add(rbtnWagonWheel);
                		storeButtons.add(rbtnWagonTongue);
                		storeButtons.add(rbtnWagonAxle);
                		
                		
                		// Update UI on the Event Dispatch Thread
                        SwingUtilities.invokeLater(() -> {
                            revalidate(); // Refresh the layout
                            repaint();    // Repaint the JFrame
                        });	
                    }
    
    /**
     * Deducts the specified cost from the current money amount.
     * Updates the money amount label to reflect the remaining money.
     *
     * @param cost the cost of the item to be deducted
     */
    private void spendMoney(double cost, double result ) {
        if (moneyTotal >= cost * result) {
            moneyTotal -= cost * result ;
            moneyAmountLabel.setText("Current money amount: " + moneyTotal);
        } else {
            System.err.println("Insufficient funds to buy this item: " + cost * result);
        }
    }

    /**
     * Main method to start the Forts application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Forts fortsGame = new Forts(wagon);
            fortsGame.setVisible(true);
        });
    }
}
