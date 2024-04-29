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

/**
* Notes from interview with Matt Valerio.
*
* make the CSV files more readable for others, layout how the SCV works.
* input validation for each area, "Defensive programming".
* Code maintenance, make sure the code is set-up correctly.
* Think about changing the CSV code that makes it all into one method.
* Couplings - what code needs changed if I change this code? Separate repeat activities to reduce coupling.
* Keep in mind Time v.s Memory - how fast something happens vs how much it takes to run it.
* Keep in mind hash maps for when we want data, such as our CSV file data.
* Define coding standards. (BRACKETS!), syntax matters for the functionality too.
*/

package MP3Package;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

public class OregonTrail {

	private JFrame frame;
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/AshHollow.JPG";
	
	// Initialization of an object of the Wagon class
	private Wagon wagon = new Wagon();
	
	private RandomEvents rndEvt = new RandomEvents(wagon);
	
	// ArrayList including all the items that can be added to the Wagon class object
	private ArrayList<Item> allItems = new ArrayList<Item>();
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(filename)));
	
	// Implement a clock travel repetition
	private javax.swing.Timer clock;
	
	private GregorianCalendar calendar = new GregorianCalendar(1850, 2, 30);
	private String pattern = "MMMMM d, yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
	private String date = dateFormatter.format(calendar.getTime());
	private JLabel Date = new JLabel(date);
	
	private JLabel TotalFood = new JLabel(Integer.toString(wagon.getTotalFood()));
	private JLabel landmarkLabel = new JLabel(Integer.toString(wagon.milesToLandmark()) + " miles");
	private JLabel milesLabel = new JLabel(Integer.toString(wagon.getMilesTraveled()) + " miles");
	private JPanel actionButtonPane = new JPanel();

	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OregonTrail window = new OregonTrail();
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
	public OregonTrail() {
		readFile();
		initialize();
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

		calendar.add(GregorianCalendar.DATE, 1);
		date = dateFormatter.format(calendar.getTime());
		Date.setText(date);
		
		// Instantiate and update list in RandomEvents object
		rndEvt = new RandomEvents(wagon);
		
		Destinations dest = wagon.atDestination();
		if(dest != null) {
			System.out.println(dest.getName());
			RiverPanel river = new RiverPanel((River) dest);
			frame.add(river);
			frame.setComponentZOrder(river, 0);
		}

		// Print out for debugging
		System.out.println("Clock Action Performed");
		int gameStatus = wagon.travel();
		// Check for sentinel value, this is true when all the people in the wagon are dead.
		if (gameStatus == -1) {

            // Display a loose message
			JOptionPane.showMessageDialog(null, "You Lose!", "LOSER", JOptionPane.ERROR_MESSAGE);
			clock.stop();
		} else {

//			// Checks to see if the player has made it to Paris, IL
//			if (wagon.getMilesTraveled() >= paris.getDistance() && (paris.getDistance() - wagon.getMilesTraveled() > -20)) {
//
//				// Display a message that the play has reached Paris
//				JOptionPane.showMessageDialog(null, "You made it to " + paris.getName() + "!");
//			}
//
//			// Checks to see if the player has made it to Springfield, IL
//			else if (wagon.getMilesTraveled() >= springfield.getDistance() && (springfield.getDistance() - wagon.getMilesTraveled()) > -20) {
//
//				// Display a message that the play has reached Springfield
//				JOptionPane.showMessageDialog(null, "You made it to " + springfield.getName() + "!", "You made it!", JOptionPane.INFORMATION_MESSAGE);
//			}
//			// Initialize the JOptionPane
//			pane = new JOptionPane();

			// Get the value from the JOptionPane, after the user selects one of the buttons
//			int res = pane.showOptionDialog(null, "<HTML>You have traveled at total of " + wagon.getMilesTraveled() + " miles.<br> You are " + milesToLandmark() + " miles from the next landmark!<br>You have " + wagon.getTotalFood() + " food remaining.<br>Do you want to stop traveling?", "Travel Status", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Yes", "No"}, -1);

			// If the selection equals 0, stop the clock and update weight labels, allowing the user to adjust pace and food rations
//			if (res == JOptionPane.YES_OPTION) {
//				System.out.println("Yes selected");
//				clock.stop();
//				TotalFoodLabel.setText("Total Food: " + wagon.getTotalFood());
//			}

			// If the selection equals 1, or if no selection is made, print debugging message and let the clock run
//			else {
//				System.out.println("No selected");
//			}
			TotalFood.setText(Integer.toString(wagon.getTotalFood()));
			landmarkLabel.setText(Integer.toString(wagon.milesToLandmark()));
			milesLabel.setText(Integer.toString(wagon.getMilesTraveled()) + " miles");
		}
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
			int tempAmount;
			String tempName = "";
			double tempPrice = 0;

			// Get the data from the CSV and assign to the temp values to make an Item object
			tempAmount = itemData.nextInt();
			tempName = itemData.next();
			tempPrice = itemData.nextDouble();

			// Debugging messages
			System.out.print("Amount: " + tempAmount);
			System.out.println("Name: " + tempName);
			System.out.println("Price: " + tempPrice);

			// Create a new Item object with the new information from the CSV file
				Item item = new Item(tempAmount, tempName, tempPrice);
				// Add the item to the ArrayList of all items
				allItems.add(item);
				wagon.addItem(item);
		}
		in.close();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
//		frame = new MainPanel(wagon, date);
		frame.setBounds(100, 100, 1000, 775);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(new Rectangle(0, 0, 0, 0));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("align 50% 100%", "[][][][][][][][][][][][][][][][][][][][]", "[][][][]"));

		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel, "cell 9 0,alignx right,growy");

		// Formatting for Date JLabel
		Date.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(Date, "cell 10 0,grow");

		JLabel lblNewLabel_1 = new JLabel("Food: ");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1, "cell 9 1,alignx right,growy");

		// Formatting for TotalFood JLabel
		TotalFood.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(TotalFood, "cell 10 1,grow");

		JLabel lblNewLabel_1_1 = new JLabel("Next Landmark: ");
		lblNewLabel_1_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1_1, "cell 9 2,grow");

		// Formatting for landmarkLabel JLabel
		landmarkLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(landmarkLabel, "cell 10 2,grow");

		JLabel lblNewLabel_2 = new JLabel("Miles Traveled: ");
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_2, "cell 9 3,alignx right,growy");

		// Formatting for milesLabel JLabel
		milesLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(milesLabel, "cell 10 3,grow");

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton travelButton = new JButton("Click to start traveling!");
		travelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!clock.isRunning()) {
					clock.start();
					travelButton.setText("Click to stop travel");
//					actionButtonPane.setEnabled(false);
					actionButtonPane.setVisible(false);
					frame.getContentPane().repaint();
				}
				else {
					clock.stop();
					travelButton.setText("Click to start traveling!");
					actionButtonPane.setVisible(true);
				}
			}
		});
		travelButton.setVerticalAlignment(SwingConstants.TOP);
		travelButton.setHorizontalAlignment(SwingConstants.CENTER);
		travelButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		travelButton.setForeground(Color.BLACK); //gitignore
		travelButton.setBackground(Color.WHITE);
		travelButton.setOpaque(true);
		panel_1.add(travelButton, BorderLayout.SOUTH);

		JLabel ImageJLabel = new JLabel("");
		ImageJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageJLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageJLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(ImageJLabel, BorderLayout.NORTH);
		ImageJLabel.setBounds(new Rectangle(frame.getWidth(), frame.getHeight() - 270));
		int width = ImageJLabel.getWidth();
		int height = ImageJLabel.getHeight();
		Image img = backgroundImage.getImage();
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageJLabel.setIcon(new ImageIcon(newImg));
		
		panel_1.add(actionButtonPane, BorderLayout.CENTER);
		actionButtonPane.setLayout(new GridLayout(0, 6, 0, 0));
		
		JButton scavengeButton = new JButton("Scavenge for Food");
		scavengeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rndEvt.scavenge();
				TotalFood.setText(Integer.toString(wagon.getTotalFood()));
			}
		});
		scavengeButton.setBackground(Color.WHITE);
		scavengeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		scavengeButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(scavengeButton);
		
		JButton restButton = new JButton("Rest");
		restButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int days = wagon.rest();
				calendar.add(GregorianCalendar.DATE, days);
				date = dateFormatter.format(calendar.getTime());
				Date.setText(date);
				TotalFood.setText(Integer.toString(wagon.getTotalFood()));
			}
		});
		restButton.setBackground(Color.WHITE);
		restButton.setHorizontalTextPosition(SwingConstants.CENTER);
		restButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(restButton);
		
		JButton tradeButton = new JButton("Trade");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trading trade = new Trading(wagon);
			}
		});
		tradeButton.setBackground(Color.WHITE);
		tradeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		tradeButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(tradeButton);
		
		JButton suppliesButton = new JButton("Check Supplies");
		suppliesButton.setBackground(Color.WHITE);
		suppliesButton.setHorizontalTextPosition(SwingConstants.CENTER);
		suppliesButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(suppliesButton);
		
		JButton paceButton = new JButton("Change Pace");
		paceButton.setBackground(Color.WHITE);
		paceButton.setHorizontalTextPosition(SwingConstants.CENTER);
		paceButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(paceButton);
		
		JButton rationsButton = new JButton("Change Rations");
		rationsButton.setBackground(Color.WHITE);
		rationsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		rationsButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		actionButtonPane.add(rationsButton);
		
		// Instantiate timer
		clock = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed( evt );
			}});
		
		
		
		


//		JLabel titleLabel = new JLabel("The Oregon Trail");
//		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		titleLabel.setFont(new Font("Vineta BT", Font.PLAIN, 17));
//		titleLabel.setBounds(250, 27, 267, 32);
//		loadWagonPanel.add(titleLabel);
//		
//		JButton RiverButton = new JButton("Cross River");
//		RiverButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
//		RiverButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				openPanel(panel);
//			}
//		});
//		RiverButton.setBounds(53, 32, 118, 23);
//		loadWagonPanel.add(RiverButton);
//		
//		StoreButton = new JButton("Buy Items");
//		StoreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
//		StoreButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.err.println("BUY ITEMS!"); //tell if its doing anything
//	                    Forts fortsPanel = new Forts(wagon);
//	                    openPanel(fortsPanel);
//	                }
//	            });
//		
//		StoreButton.setBounds(566, 11, 101, 23);
//		loadWagonPanel.add(StoreButton);
//		
//		TradeButton = new JButton("Trade");
//		TradeButton.setBounds(566, 61, 101, 23);
//		TradeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
//		loadWagonPanel.add(TradeButton);
//		TradeButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Trading trade = new Trading(allItems);
//				
//			}
//		});
//		
//		// Displays the total amount of food that is in the wagon. Initial value displayed is set to 0.
//		TotalFoodLabel = new JLabel("Total Food Weight: " + wagon.getTotalFood());
//		TotalFoodLabel.setForeground(Color.WHITE);
//		TotalFoodLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		TotalFoodLabel.setBounds(250, 550, 170, 16);
//		loadWagonPanel.add(TotalFoodLabel);
//		
//		// [1] R. P. Bouchard, "Building the Mathematical Models" in You Have Died of Dysentery, R. P. Bouchard, 2016, ch. 16
//		// "Filling" button sets the food consumption for each person to 3 lbs per person.
//		JRadioButton rdbtnNewRadioButton = new JRadioButton("Filling");
//		rdbtnNewRadioButton.setForeground(Color.WHITE);
//		rdbtnNewRadioButton.setOpaque(false);
//		rdbtnNewRadioButton.setBackground(new Color(240, 240, 240));
//		rdbtnNewRadioButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				wagon.setFoodConsumption(3);
//			}
//		});
//		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		rdbtnNewRadioButton.setBounds(100, 607, 80, 23);
//		loadWagonPanel.add(rdbtnNewRadioButton);
//		
//		// [1]
//		// "Meager" button sets the food consumption for each person to 2 lbs per person.
//		JRadioButton rdbtnMeager = new JRadioButton("Meager");
//		rdbtnMeager.setForeground(Color.WHITE);
//		rdbtnMeager.setOpaque(false);
//		rdbtnMeager.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				wagon.setFoodConsumption(2);
//			}
//		});
//		rdbtnMeager.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		rdbtnMeager.setBounds(179, 608, 80, 23);
//		loadWagonPanel.add(rdbtnMeager);
//		
//		// [1]
//		// "Bare Bones" button sets the food consumption for each person to 1 lbs per person.
//		JRadioButton rdbtnBareBones = new JRadioButton("Bare Bones");
//		rdbtnBareBones.setOpaque(false);
//		rdbtnBareBones.setForeground(Color.WHITE);
//		rdbtnBareBones.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				wagon.setFoodConsumption(1);
//			}
//		});
//		rdbtnBareBones.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		rdbtnBareBones.setBounds(264, 608, 100, 23);
//		loadWagonPanel.add(rdbtnBareBones);
//		
//		// An "Easter egg" work-in-progress feature for talking to strangers (Button is hidden below the travel! button)
//		JButton talkButton = new JButton("Talk to People");
//		talkButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activity.talkToRandos();
//			}});
//		
//		talkButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		talkButton.setBounds(525, 627, 142, 23);
//		loadWagonPanel.add(talkButton);
//		
//		JLabel consumptionLabel = new JLabel("Choose Food Consumption: ");
//		consumptionLabel.setForeground(Color.WHITE);
//		consumptionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		consumptionLabel.setBounds(100, 577, 197, 23);
//		loadWagonPanel.add(consumptionLabel);
//		
//		// Initializing a button group, so only one radio button can be selected at one time
//		ButtonGroup buttons = new ButtonGroup();
//		buttons.add(rdbtnBareBones);
//		buttons.add(rdbtnMeager);
//		buttons.add(rdbtnNewRadioButton);
//		
//		JLabel milesLabel = new JLabel("How many miles per day?");
//		milesLabel.setForeground(Color.WHITE);
//		milesLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		milesLabel.setBounds(467, 510, 216, 23);
//		loadWagonPanel.add(milesLabel);
//		
//		// Initialize and stateChange listener for a JSlider so the user can set the number of 
//		// miles per day. 
//		JSlider mileSlider = new JSlider(12, 20);
//		mileSlider.setOpaque(false);
//		mileSlider.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		wagon.setMilesPerDay(mileSlider.getValue());
//		mileSlider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				System.out.println(mileSlider.getValue());
//				wagon.setMilesPerDay(mileSlider.getValue());
//			}
//		});
//		mileSlider.setBounds(467, 540, 200, 35);
//		mileSlider.setLabelTable(mileSlider.createStandardLabels(1, 12));
//		mileSlider.setPaintLabels(true);
//		mileSlider.setForeground(Color.WHITE);
//		
//		loadWagonPanel.add(mileSlider);
//		
//		
//		// JLabel to display errors if the user either has added more than 2,400 lbs to the wagon,
//		// or if the user did not select a food consumption rate
//		JLabel ConsumptionErrorLabel = new JLabel("");
//		ConsumptionErrorLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		ConsumptionErrorLabel.setBounds(486, 600, 197, 16);
//		ConsumptionErrorLabel.setForeground(Color.WHITE);
//		loadWagonPanel.add(ConsumptionErrorLabel);
//		
//		
//		/* 
//		 * JButton for the user to select that calculates whether the user will make it
//		 * to Oregon with the amount of food, food consumption, and miles per day that were selected
//		 */
//		JButton travelButton = new JButton("Travel!");
//		travelButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				/* 
//				 * Checks if a button has been selected. Otherwise, tell the user to select
//				 * a consumption rate button.
//				 */
//				if(buttons.getSelection() == null) {
//					System.out.println("Select a consumption rate.");
//					ConsumptionErrorLabel.setText("Select a Consumption Rate");
//				}
//				/*	
//				 * Checks if the total amount of weight of items selected is over the maximum allowed (2,400).
//				 * If there is too much weight, tell the user that they have too much weight on the wagon.
//				 */
//				else if(wagon.getTotalFood() > 2400) {
//					ConsumptionErrorLabel.setText("Wagon cannot be over 2,400 lbs");
//				}
//				
//				// Starts the clock to start traveling
//				else {
//					System.out.println("Clock start");
//					clock.start();
//				}
//			}
//		});
//		travelButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		travelButton.setBounds(525, 578, 89, 23);
//		loadWagonPanel.add(travelButton);



	}
}
