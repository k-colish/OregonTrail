/** 
 * MP3.java 
 * 
 * Simple educational Oregon Trail game, to test if you will be able to make it 
 * to Oregon, depending on the amount of food and other items are
 * added to the wagon. User is able to click check boxes to add items to the wagon, 
 * set the amount of food that is consumed by each of the 4 people, and then 
 * choose the number of miles to travel per day. This is to help the user
 * experiment with the amount of food that people during the Oregon Trail era 
 * would have needed to take with them.
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 3/25/24
 */

package MP3Package;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;

public class MP3 {

	private JFrame frame;
	
	JButton StoreButton;
	JButton TradeButton;
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/Ash Hollow.JPG";
	private JLabel ImageLabel;
	
	// Initialization of an object of the Wagon class
	private Wagon wagon = new Wagon();
	
	// ArrayList including all the JCheckBoxes of the items to be displayed
	private ArrayList<JCheckBox> labels = new ArrayList<JCheckBox>(); 
	
	// ArrayList including all the items that can be added to the Wagon class object
	private ArrayList<Item> allItems = new ArrayList<Item>();
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource(filename));
	
	// Implement a clock travel repetition
	private javax.swing.Timer clock;
	
	private RiverPanel panel = new RiverPanel(new River(200, "Kansas River", 3.5, 300, 2));;
	
	// Initialize a null OptionPane for use with the clock
	private JOptionPane pane = null;
	
	// Initialize the labels for weight labels, so they can be updated after the user wants to stop traveling
	private JLabel TotalFoodLabel = null;
	private JLabel TotalWeightLabel = null;
	
	// Create a DestinationActivites object (Currently not used)
	private DestinationActivities activity = new DestinationActivities();
	
	// Instantiate Destinations objects for each of the landmarks
	// cheviot is the start location
	private Destinations cheviot = new Destinations(0, "Cheviot, Ohio", true);
	
	// paris is the first landmark on the trail
	private Destinations paris = new Destinations(180, "Paris, Illinois", true);
	
	// springfield is the second landmark (last for this version)
	private Destinations springfield = new Destinations(280, "Springfield, Illinois", true);
	private JPanel loadWagonPanel = new JPanel();
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				    e.printStackTrace();
				}
				try {
					MP3 window = new MP3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MP3() {
		// Process CSV file for list of items
		readFile();
		initialize();
		
		// Sets a JLabel for the background image
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(0, 0, 734, 661);
		loadWagonPanel.add(ImageLabel);
		ImageLabel.setIcon(backgroundImage);
	}
	
	public  void openPanel(JPanel panelOpen) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panelOpen);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}
	
	
	/**
	 * clockActionPerformed - displays the JOptionPane with current run information AFTER the previous JOptionPane is closed
	 * @param e - ActionEvent
	 */
	public void clockActionPerformed(ActionEvent e) {
		
		// Print out for debugging
		System.out.println("Clock Action Performed");
		
		// Check for sentinel value, this is true when all of the people in the wagon are dead.
		if(wagon.travel() == -1) {
			
			// Display a lose message
			JOptionPane.showMessageDialog(null, "You Lose!", "LOSER", JOptionPane.ERROR_MESSAGE); clock.stop();
		}
		else {
			
			// Checks to see if the player has made it to Paris, IL
			if(wagon.getMilesTraveled() >= paris.getDistance() && (paris.getDistance() - wagon.getMilesTraveled() > -20)) {
				
				// Display a message that the play has reached Paris
				JOptionPane.showMessageDialog(null, "You made it to " + paris.getName() + "!");
			}
			
			// Checks to see if the player has made it to Springfield, IL
			else if(wagon.getMilesTraveled() >= springfield.getDistance() && (springfield.getDistance() - wagon.getMilesTraveled()) > -20) {
				
				// Display a message that the play has reached Springfield
				JOptionPane.showMessageDialog(null, "You made it to " + springfield.getName() + "!", "You made it!", JOptionPane.INFORMATION_MESSAGE);
			}
			// Initialize the JOptionPane
			pane = new JOptionPane();
			
			// Get the value from the JOptionPane, after the user selects one of the buttons
			int res = pane.showOptionDialog(null, "<HTML>You have traveled at total of " + wagon.getMilesTraveled() + " miles.<br> You are " + milesToLandmark() + " miles from the next landmark!<br>You have " + wagon.getTotalFoodWeight() + " food remaining.<br>Do you want to stop traveling?", "Travel Status", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Yes", "No"}, -1);								
			
			// If the selection equals 0, stop the clock and update weight labels, allowing the user to adjust pace and food rations
			if(res == JOptionPane.YES_OPTION) {
				System.out.println("Yes selected");
				clock.stop();
				TotalFoodLabel.setText("Total Food Weight: " + wagon.getTotalFoodWeight());
				TotalWeightLabel.setText("Total Weight: " + wagon.calculateTotalWeight());
			}
			
			// If the selection equals 1, or if no selection is made, print debugging message and let the clock run
			else{
				System.out.println("No selected");
			}
		}
	}
	
	
	/**
	 * milesToLandmark - calculates the miles to the next landmark
	 * @return - the distance to the next landmark
	 */
	public int milesToLandmark() {
		
		// If travel distance is less than distance to paris from start, return the difference
		if(paris.getDistance() > wagon.getMilesTraveled())
			return paris.getDistance() - wagon.getMilesTraveled();
		
		// else return the difference between distance traveled and distance to Springfield from start
		else return springfield.getDistance() - wagon.getMilesTraveled();
	}
	
	/**
	 * readFile - reads from a CSV file with a list of the items that can be added to the wagon,
	 *  including their weight, name, and whether they are a food item. This method generates 
	 *  an Item object for each line in the CSV file, and adds them to the ArrayList of all items.
	 */
	public void readFile() {
		InputStreamReader reader = null;
		Scanner in = null;
		String itemFile = "/csv/Items.csv";
		
		try {
			reader = new InputStreamReader(this.getClass().getResourceAsStream(itemFile));
		}
		catch(Exception e) {
			System.out.print("Could not open file ");
		}
		
		// Create a InputStreamReader Scanner to read in the CSV file
		in = new Scanner(reader);
		
		while(in.hasNext()) {
			
			// Create a new Scanner with ", " as the delimiter
			Scanner itemData = new Scanner(in.nextLine());
			itemData.useDelimiter(", ");
			
			// Instantiate temporary variables for item info
			String tempName = "";
			int tempWeight = 0;
			int isFood;
			double tempPrice = 0;
			
			// Get the data from the CSV and assign to the temp values to make an Item object
			tempWeight = itemData.nextInt();		
			tempName = itemData.next();
			isFood = itemData.nextInt();
			tempPrice = itemData.nextDouble();
			
			// Debugging messages
			System.out.println("Weight: " + tempWeight);
			System.out.println("Name: " + tempName);
			System.out.println("Is Food: " + isFood);
			
			if(isFood >= 1) {
				FoodItem food = new FoodItem(tempWeight, tempName, tempPrice);
				allItems.add(food);
				food.setIsFood(isFood);
			}
			
			// Create a new Item object with the new information from the CSV file
			else {
				Item item = new Item(tempWeight, tempName, tempPrice);
				// Add the item to the ArrayList of all items
				allItems.add(item);
				
				// Set whether the item is a food item
				item.setIsFood(isFood);
			}
			
		}
		in.close();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		loadWagonPanel.setLayout(null);
		openPanel(loadWagonPanel);
		
		// Instantiate timer
		clock = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed( evt );
				}
				});
		
		JLabel lblNewLabel = new JLabel("The Oregon Trail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Vineta BT", Font.PLAIN, 17));
		lblNewLabel.setBounds(250, 27, 267, 32);
		loadWagonPanel.add(lblNewLabel);
		
		JButton RiverButton = new JButton("Cross River");
		RiverButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		RiverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPanel(panel);
			}
		});
		RiverButton.setBounds(53, 32, 118, 23);
		loadWagonPanel.add(RiverButton);
		
		StoreButton = new JButton("Buy Items");
		StoreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		StoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("BUY ITEMS!"); //tell if its doing anything 
				System.err.println("BUY ITEMS!"); //tell if its doing anything FOR MAC
	                    Forts fortsPanel = new Forts();
	                    openPanel(fortsPanel);
	                }
	            });
		
		StoreButton.setBounds(566, 11, 101, 23);
		loadWagonPanel.add(StoreButton);
		
		TradeButton = new JButton("Trade");
		TradeButton.setBounds(566, 61, 101, 23);
		TradeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		loadWagonPanel.add(TradeButton);
		TradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trading trade = new Trading(allItems);
				
			}
		});
		
		// Displays the total weight of items on the wagon. Initial value displayed is set as 0
		TotalWeightLabel = new JLabel("Total Weight: " + wagon.calculateTotalWeight());
		TotalWeightLabel.setForeground(Color.WHITE);
		TotalWeightLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TotalWeightLabel.setBounds(100, 550, 150, 16);
		loadWagonPanel.add(TotalWeightLabel);
		
		// Displays the total weight of food items that are on the wagon. Initial value displayed is set to 0.
		TotalFoodLabel = new JLabel("Total Food Weight: " + wagon.getTotalFoodWeight());
		TotalFoodLabel.setForeground(Color.WHITE);
		TotalFoodLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TotalFoodLabel.setBounds(250, 550, 170, 16);
		loadWagonPanel.add(TotalFoodLabel);
		
		// [1] R. P. Bouchard, "Building the Mathematical Models" in You Have Died of Dysentry, R. P. Bouchard, 2016, ch. 16
		// "Filling" button sets the food consumption for each person to 3 lbs per person.
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Filling");
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setBackground(new Color(240, 240, 240));
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setFoodConsumption(3);
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(100, 607, 71, 23);
		loadWagonPanel.add(rdbtnNewRadioButton);
		
		// [1]
		// "Meager" button sets the food consumption for each person to 2 lbs per person.
		JRadioButton rdbtnMeager = new JRadioButton("Meager");
		rdbtnMeager.setForeground(Color.WHITE);
		rdbtnMeager.setOpaque(false);
		rdbtnMeager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setFoodConsumption(2);
			}
		});
		rdbtnMeager.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnMeager.setBounds(179, 608, 71, 23);
		loadWagonPanel.add(rdbtnMeager);
		
		// [1]
		// "Bare Bones" button sets the food consumption for each person to 1 lbs per person.
		JRadioButton rdbtnBareBones = new JRadioButton("Bare Bones");
		rdbtnBareBones.setOpaque(false);
		rdbtnBareBones.setForeground(Color.WHITE);
		rdbtnBareBones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setFoodConsumption(1);
			}
		});
		rdbtnBareBones.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnBareBones.setBounds(264, 608, 100, 23);
		loadWagonPanel.add(rdbtnBareBones);
		
		// An "easter egg" work-in-progress feature for talking to strangers (Button is hidden below the travel! button)
		JButton btnNewButton = new JButton("Talk to People");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.talkToRandos();
			}
		});
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(525, 627, 142, 23);
		loadWagonPanel.add(btnNewButton);
		
		
		JLabel lblNewLabel_1 = new JLabel("Choose Food Consumption: ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(100, 577, 197, 23);
		loadWagonPanel.add(lblNewLabel_1);
		
		// Initializing a button group, so only one radio button can be selected at one time
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnBareBones);
		buttons.add(rdbtnMeager);
		buttons.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("How many miles per day?");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(467, 510, 216, 23);
		loadWagonPanel.add(lblNewLabel_2);
		
		// Initialize and stateChange listener for a JSlider so the user can set the number of 
		// miles per day. 
		JSlider slider = new JSlider(12, 20);
		slider.setOpaque(false);
		slider.setFont(new Font("Times New Roman", Font.BOLD, 14));
		wagon.setMilesPerDay(slider.getValue());
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(slider.getValue());
				wagon.setMilesPerDay(slider.getValue());
			}
		});
		slider.setBounds(467, 540, 200, 26);
		slider.setLabelTable(slider.createStandardLabels(1, 12));
		slider.setPaintLabels(true);
		slider.setForeground(Color.WHITE);
		
		loadWagonPanel.add(slider);
		
		
		// JLabel to display errors if the user either has added more than 2,400 lbs to the wagon,
		// or if the user did not select a food consumption rate
		JLabel ConsumptionErrorLabel = new JLabel("");
		ConsumptionErrorLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ConsumptionErrorLabel.setBounds(486, 616, 197, 16);
		ConsumptionErrorLabel.setForeground(Color.WHITE);
		loadWagonPanel.add(ConsumptionErrorLabel);
		
		
		/* 
		 * JButton for the user to select that calculates whether or not the user will make it
		 * to Oregon with the amount of food, food consumption, and miles per day that were selected
		 */
		JButton btnNewButton1 = new JButton("Travel!");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 
				 * Checks if a button has been selected. Otherwise, tell the user to select
				 * a consumption rate button.
				 */
				if(buttons.getSelection() == null) {
					System.out.println("Select a consumption rate.");
					ConsumptionErrorLabel.setText("Select a Consumption Rate");
				}
				/*	
				 * Checks if the total amount of weight of items selected is over the maximum allowed (2,400).
				 * If there is too much weight, tell the user that they have too much weight on the wagon.
				 */
				else if(wagon.calculateTotalWeight() > 2400) {
					ConsumptionErrorLabel.setText("Wagon cannot be over 2,400 lbs");
				}
				
				// Starts the clock to start traveling
				else {
					System.out.println("Clock start");
					clock.start();
				}
			}
		});
		btnNewButton1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton1.setBounds(525, 578, 89, 23);
		loadWagonPanel.add(btnNewButton1);
		
		
		
		/*
		 * Loop for generating JCheckboxes for each item that is able to be added to the wagon
		 */
		for(int i = 0; i < allItems.size(); i++) {
				final int j = i; // Make Compiler Happy
				
				// Create a new JCheckbox, using i as the index of the items ArrayList and getting that item's name and weight for the text.
				JCheckBox label = new JCheckBox(allItems.get(i).getName() + ": weighs " + allItems.get(i).getWeight() + " lbs");
				label.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						/*
						 *  When the check box is checked, add the item to the wagon using the addItem() method from the Wagon class
						 *  Then update the total weight label, adding that items weight to the total.
						 *  
						 *  Also check if the item is a food item, if it is, update the total food weight displayed.
						 */
						if(label.isSelected()) {
							// Add the corresponding item to the array list after the check box is selected
							wagon.addItem(allItems.get(j));
							
							// Set the total weight label to display the total weight of all items
							TotalWeightLabel.setText("Total Weight: ");
							TotalWeightLabel.setText(TotalWeightLabel.getText() + wagon.calculateTotalWeight());
							
							// If the item is a food item, update the total food weight label with the weight of all food items
							if(allItems.get(j).getIsFood()) {
								TotalFoodLabel.setText("Total Food Weight: ");
								TotalFoodLabel.setText(TotalFoodLabel.getText() + wagon.getTotalFoodWeight());
							}
						}
						/*
						 * When the check box is unchecked, remove the item from the wagon, using the removeItem method from the Wagon class.
						 * 
						 * Similarly to when the check box is checked, update the total weight labels accordingly.
						 */
						else if(!label.isSelected()) {
							// Remove the corresponding item to the array list after the check box is unselected
							wagon.removeItem(allItems.get(j));
							
							// Set the total weight label to display the total weight of all items
							TotalWeightLabel.setText("Total Weight: ");
							TotalWeightLabel.setText(TotalWeightLabel.getText() + wagon.calculateTotalWeight());
							
							// If the item is a food item, update the total food weight label with the weight of all food items
							if(allItems.get(j).getIsFood()) {
								TotalFoodLabel.setText("Total Food Weight: ");
								TotalFoodLabel.setText(TotalFoodLabel.getText() + wagon.getTotalFoodWeight());
							}
						}
					}
				});
				label.setFont(new Font("Times New Roman", Font.BOLD, 14));
				
				// Logic for x and y coordinates for the JCheckboxes, since they don't appear in Design, using i for the spacing calculations.
				label.setBounds(121, 70 + i*30, 300, 23);
				if(i > 15) {
					label.setBounds(121 + 300, 70 + (i-15)*30, 300, 23);
				}
				loadWagonPanel.add(label);
				label.setOpaque(false);
				label.setForeground(Color.WHITE);
				labels.add(label);
		}
	}
}
