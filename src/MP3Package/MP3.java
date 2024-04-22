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

import java.awt.Font;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

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
	private String filename = "/Images/AshHollow.JPG";
	private JLabel ImageLabel;
	
	// Initialization of an object of the Wagon class
	private Wagon wagon = new Wagon();
	
	// ArrayList including all the JCheckBoxes of the items to be displayed
	private ArrayList<JCheckBox> labels = new ArrayList<JCheckBox>(); 
	
	// ArrayList including all the items that can be added to the Wagon class object
	private ArrayList<Item> allItems = new ArrayList<Item>();
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(filename)));
	
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
		readFile();
		initialize();
		
		// Sets a JLabel for the background image
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(0, 0, 734, 661);
		loadWagonPanel.add(ImageLabel);
		ImageLabel.setIcon(backgroundImage);

	}
	
	public void openPanel(JPanel panelOpen) {
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

		// Check for sentinel value, this is true when all the people in the wagon are dead.
		if (wagon.travel() == -1) {

			// Display a lose message
			JOptionPane.showMessageDialog(null, "You Lose!", "LOSER", JOptionPane.ERROR_MESSAGE);
			clock.stop();
		} else {

			// Checks to see if the player has made it to Paris, IL
			if (wagon.getMilesTraveled() >= paris.getDistance() && (paris.getDistance() - wagon.getMilesTraveled() > -20)) {

				// Display a message that the play has reached Paris
				JOptionPane.showMessageDialog(null, "You made it to " + paris.getName() + "!");
			}

			// Checks to see if the player has made it to Springfield, IL
			else if (wagon.getMilesTraveled() >= springfield.getDistance() && (springfield.getDistance() - wagon.getMilesTraveled()) > -20) {

				// Display a message that the play has reached Springfield
				JOptionPane.showMessageDialog(null, "You made it to " + springfield.getName() + "!", "You made it!", JOptionPane.INFORMATION_MESSAGE);
			}
			// Initialize the JOptionPane
			pane = new JOptionPane();

			// Get the value from the JOptionPane, after the user selects one of the buttons
			int res = pane.showOptionDialog(null, "<HTML>You have traveled at total of " + wagon.getMilesTraveled() + " miles.<br> You are " + milesToLandmark() + " miles from the next landmark!<br>You have " + wagon.getTotalFood() + " food remaining.<br>Do you want to stop traveling?", "Travel Status", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Yes", "No"}, -1);

			// If the selection equals 0, stop the clock and update weight labels, allowing the user to adjust pace and food rations
			if (res == JOptionPane.YES_OPTION) {
				System.out.println("Yes selected");
				clock.stop();
				TotalFoodLabel.setText("Total Food: " + wagon.getTotalFood());
			}

			// If the selection equals 1, or if no selection is made, print debugging message and let the clock run
			else {
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
			int tempAmmount;
			String tempName = "";
			double tempPrice = 0;
			
			// Get the data from the CSV and assign to the temp values to make an Item object	
			tempAmmount = itemData.nextInt();
			tempName = itemData.next();
			tempPrice = itemData.nextDouble();
			
			// Debugging messages
			System.out.print("Ammount: " + tempAmmount);
			System.out.println("Name: " + tempName);
			System.out.println("Price: " + tempPrice);
			
			// Create a new Item object with the new information from the CSV file
				Item item = new Item(tempAmmount, tempName, tempPrice);
				// Add the item to the ArrayList of all items
				allItems.add(item);
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
				}});
		
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
		
		// Displays the total ammount of food that is in the wagon. Initial value displayed is set to 0.
		TotalFoodLabel = new JLabel("Total Food Weight: " + wagon.getTotalFood());
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
		rdbtnNewRadioButton.setBounds(100, 607, 80, 23);
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
		rdbtnMeager.setBounds(179, 608, 80, 23);
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
		JButton talkButton = new JButton("Talk to People");
		talkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.talkToRandos();
			}});
		
		talkButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		talkButton.setBounds(525, 627, 142, 23);
		loadWagonPanel.add(talkButton);
		
		JLabel consumptionLabel = new JLabel("Choose Food Consumption: ");
		consumptionLabel.setForeground(Color.WHITE);
		consumptionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		consumptionLabel.setBounds(100, 577, 197, 23);
		loadWagonPanel.add(consumptionLabel);
		
		// Initializing a button group, so only one radio button can be selected at one time
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnBareBones);
		buttons.add(rdbtnMeager);
		buttons.add(rdbtnNewRadioButton);
		
		JLabel milesLabel = new JLabel("How many miles per day?");
		milesLabel.setForeground(Color.WHITE);
		milesLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		milesLabel.setBounds(467, 510, 216, 23);
		loadWagonPanel.add(milesLabel);
		
		// Initialize and stateChange listener for a JSlider so the user can set the number of 
		// miles per day. 
		JSlider mileSlider = new JSlider(12, 20);
		mileSlider.setOpaque(false);
		mileSlider.setFont(new Font("Times New Roman", Font.BOLD, 14));
		wagon.setMilesPerDay(mileSlider.getValue());
		mileSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(mileSlider.getValue());
				wagon.setMilesPerDay(mileSlider.getValue());
			}
		});
		mileSlider.setBounds(467, 540, 200, 35);
		mileSlider.setLabelTable(mileSlider.createStandardLabels(1, 12));
		mileSlider.setPaintLabels(true);
		mileSlider.setForeground(Color.WHITE);
		
		loadWagonPanel.add(mileSlider);
		
		
		// JLabel to display errors if the user either has added more than 2,400 lbs to the wagon,
		// or if the user did not select a food consumption rate
		JLabel ConsumptionErrorLabel = new JLabel("");
		ConsumptionErrorLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ConsumptionErrorLabel.setBounds(486, 600, 197, 16);
		ConsumptionErrorLabel.setForeground(Color.WHITE);
		loadWagonPanel.add(ConsumptionErrorLabel);
		
		
		/* 
		 * JButton for the user to select that calculates whether or not the user will make it
		 * to Oregon with the amount of food, food consumption, and miles per day that were selected
		 */
		JButton travelButton = new JButton("Travel!");
		travelButton.addActionListener(new ActionListener() {
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
				else if(wagon.getTotalFood() > 2400) {
					ConsumptionErrorLabel.setText("Wagon cannot be over 2,400 lbs");
				}
				
				// Starts the clock to start traveling
				else {
					System.out.println("Clock start");
					clock.start();
				}
			}
		});
		travelButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		travelButton.setBounds(525, 578, 89, 23);
		loadWagonPanel.add(travelButton);
		
		
		
	}
}
