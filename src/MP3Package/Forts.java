package MP3Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Forts class represents a GUI application for managing items with associated costs.
 * Users can view items from a CSV file, select items to purchase, and update the displayed money amount accordingly.
 * 
 * @author Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version 1.0.0 - 4/16/24
 */
public class Forts extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArrayList<JRadioButton> itemButtons = new ArrayList<>();
    private final JLabel moneyAmountLabel; // Label to display the current amount of money available
    private static double moneyTotal = 1000; // Total amount of money available to spend at start
    private Wagon wagon = null;
    private Forts panel = null;

    /**
     * Constructs a new Forts instance.
     * Sets up the JFrame and initializes components.
     */
    public Forts(Wagon wagon, FortPanel fort, OregonTrail trail) {
    	setLayout(null); // Set layout to null for absolute positioning

    	// Sets up the labels for how much money you have when shopping
        moneyAmountLabel = new JLabel("Current money amount: " + moneyTotal);
        moneyAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        moneyAmountLabel.setBounds(10, 10, 300, 30);
        add(moneyAmountLabel);
        
        // sets the index of each item in the array list to a variable to be processed by the addItemAmount method
        this.wagon = wagon;
        ArrayList <Item> items = wagon.getItems();
        int oxen = 1;
        int clothing = 2;
        int wagonWheel = 3;
        int wagonTongue = 4;
        int wagonAxle = 5;
        
        // initialize the panel for the store
        panel = this;
		JOptionPane pane = null;
		
		
		/*
		 *  The following sections create the buttons to be clicked for when you want to purchase an item. 
		 *  An action event is related to each Option Pane that appears when an item is selected which adds 
		 *  items to the wagon and updates labels accordingly. 
		 *  
		 */
		String foodCost = String.format("Food - $%.2f", (0.40 + 0.40 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnFood = new JRadioButton(foodCost);
		rbtnFood.setBounds(10, 50, 200, 30);
		add(rbtnFood);
		rbtnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double itemCost = (0.40 + 0.40 * (wagon.getMilesTraveled() / 10000));
				String input;
				double result;
				input = JOptionPane.showInputDialog("How many of this item would you like to buy?");
				result = Double.parseDouble(input);
				System.out.println(result);
				spendMoney(wagon, itemCost, result);
				int newResult = (int) result;
				wagon.changeTotalFood(newResult);
				trail.updateLabels();
			}
		});

		String oxenCost = String.format("Yoke of Oxen - $%.2f", (40.00 + 40.00 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnOxen = new JRadioButton(oxenCost);
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
				result = 2 * result1;
				System.out.println(result);
				spendMoney(wagon, itemCost, result1);
				int newResult = (int) result;
				wagon.addItemAmount(wagon.getItems().get(oxen).getName(), newResult);
				trail.updateLabels();
			}
		});

		String clothingCost = String.format("Clothing - $%.2f", (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnClothing = new JRadioButton(clothingCost);
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
				spendMoney(wagon, itemCost, result);
				int newResult = (int) result;
				wagon.addItemAmount(wagon.getItems().get(clothing).getName(), newResult);
				trail.updateLabels();
			}
		});

		String wheelCost = String.format("Wagon Wheel - $%.2f", (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnWagonWheel = new JRadioButton(wheelCost);
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
				spendMoney(wagon, itemCost, result);
				int newResult = (int) result;
				wagon.addItemAmount(wagon.getItems().get(wagonWheel).getName(), newResult);
				trail.updateLabels();
			}
		});
		
		String tongueCost = String.format("Wagon Tongue - $%.2f", (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnWagonTongue = new JRadioButton(tongueCost);
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
				spendMoney(wagon, itemCost, result);
				int newResult = (int) result;
				wagon.addItemAmount(wagon.getItems().get(wagonTongue).getName(), newResult);
				trail.updateLabels();
			}
		});
		
		String axleCost = String.format("Wagon Axle - $%.2f", (10.00 + 10.00 * (wagon.getMilesTraveled() / 10000)));
		JRadioButton rbtnWagonAxle = new JRadioButton(axleCost);
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
				spendMoney(wagon, itemCost, result);
				int newResult = (int) result;
				wagon.addItemAmount(wagon.getItems().get(wagonAxle).getName(), newResult);
				trail.updateLabels();
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
		
		// Button that sends you back to the home menu when you are done shopping
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				comp.getParent().getParent().getParent().add(fort, BorderLayout.CENTER);
				comp.getParent().getParent().remove(panel);
				win.revalidate();
			}
		});
		doneButton.setMargin(new Insets(4, 14, 0, 14));
		doneButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		doneButton.setBounds(175, 303, 95, 30);
		add(doneButton);
    }

    /**
     * Deducts the specified cost from the current money amount.
     * Updates the money amount label to reflect the remaining money.
     *
     * @param cost the cost of the item to be deducted
     */
    private void spendMoney(Wagon wagon, double cost, double result ) {
        if (moneyTotal >= cost * result) {
        	this.wagon= wagon;
        	ArrayList <Item> items = wagon.getItems();
        	
        	
        	
            moneyTotal -= cost * result ;
            String moneyAmount = String.format("Current money amount: %.2f", moneyTotal);
            
            moneyAmountLabel.setText(moneyAmount);
        } else {
            System.err.println("Insufficient funds to buy this item: " + cost * result);
        }
    }
}
