/** MP3.java
 * 
 * Simple educational Oregon Trail game, to test if you will be able to make it 
 * to Oregon, depending on the amount of food and other items are
 * added to the wagon. User is able to click check boxes to add items to the wagon, 
 * set the amount of food that is consumed by each of the 4 people, and then 
 * choose the number of miles to travel per day. This is to help the user
 * experiment with the amount of food that people during the Oregon Trail era 
 * would have needed to take with them.
 * 
 * @author - Kaiden Colish
 * @version - 1.0.0 - 3/25/24
 */

package MP3Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import java.awt.Color;

public class MP3 {

	private JFrame frame;
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/Ash Hollow.JPG";
	private JLabel ImageLabel;
	
	// Initialization of an object of the Wagon class
	private Wagon wagon = new Wagon();
	
	// ArrayList including all of the JCheckBoxes of the items to be displayed
	private ArrayList<JCheckBox> labels = new ArrayList<JCheckBox>(); 
	
	// ArrayList including all of the items that can be added to the Wagon class object
	private ArrayList<Item> allItems = new ArrayList<Item>();
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource(filename));
	
	private DestinationActivities activity = new DestinationActivities();
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
		// Process csv file for list of items
		readFile();
		initialize();
		
		// Sets a JLabel for the background image
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(0, 0, 734, 661);
		frame.getContentPane().add(ImageLabel);
		ImageLabel.setIcon(backgroundImage);
		
		JButton btnNewButton = new JButton("Travel!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.talkToRandos();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(525, 627, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	/**
	 * readFile - reads from a csv file with a list of the items that can be added to the wagon,
	 *  including their weight, name, and whether they are a food item. This method generates 
	 *  an Item object for each line in the csv file, and adds them to the ArrayList of all items.
	 *  
	 *  @param - none
	 *  @return - none
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
		
		// Create a InputStreamReader Scanner to read in the csv file
		in = new Scanner(reader);
		
		while(in.hasNext()) {
			
			// Create a new Scanner with ", " as the delimiter
			Scanner itemData = new Scanner(in.nextLine());
			itemData.useDelimiter(", ");
			
			// Instantiate temporary variables for item info
			String tempName = "";
			int tempWeight = 0;
			int isFood;
			
			// Get the data from the csv and assign to the temp values to make an Item object
			tempWeight = itemData.nextInt();		
			tempName = itemData.next();
			isFood = itemData.nextInt();
			
			// Debugging messages
			System.out.println("Weight: " + tempWeight);
			System.out.println("Name: " + tempName);
			System.out.println("Is Food: " + isFood);
			
			if(isFood >= 1) {
				FoodItem food = new FoodItem(tempWeight, tempName);
				allItems.add(food);
				food.setIsFood(isFood);
			}
			
			// Create a new Item object with the new information from the csv file
			else {
				Item item = new Item(tempWeight, tempName);
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
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("The Oregon Trail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Vineta BT", Font.PLAIN, 17));
		lblNewLabel.setBounds(250, 27, 267, 32);
		frame.getContentPane().add(lblNewLabel);
		
		
		// Displays the total weight of items on the wagon. Initial value displayed is set as 0
		JLabel TotalWeightLabel = new JLabel("Total Weight: " + wagon.calculateTotalWeight());
		TotalWeightLabel.setForeground(Color.WHITE);
		TotalWeightLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TotalWeightLabel.setBounds(100, 550, 150, 16);
		frame.getContentPane().add(TotalWeightLabel);
		
		// Displays the total weight of food items that are on the wagon. Initial value displayed is set to 0.
		JLabel TotalFoodLabel = new JLabel("Total Food Weight: " + wagon.calculateTotalWeight());
		TotalFoodLabel.setForeground(Color.WHITE);
		TotalFoodLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TotalFoodLabel.setBounds(250, 550, 170, 16);
		frame.getContentPane().add(TotalFoodLabel);
		
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
		frame.getContentPane().add(rdbtnNewRadioButton);
		
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
		frame.getContentPane().add(rdbtnMeager);
		
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
		frame.getContentPane().add(rdbtnBareBones);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Food Consumption: ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(100, 577, 197, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		// Initializing a button group, so only one radio button can be selected at one time
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnBareBones);
		buttons.add(rdbtnMeager);
		buttons.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("How many miles per day?");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(467, 510, 216, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
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
		
		frame.getContentPane().add(slider);
		
		
		// JLabel to display errors if the user either has added more than 2,400 lbs to the wagon,
		// or if the user did not select a food consumption rate
		JLabel ConsumptionErrorLabel = new JLabel("");
		ConsumptionErrorLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ConsumptionErrorLabel.setBounds(486, 616, 197, 16);
		ConsumptionErrorLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(ConsumptionErrorLabel);
		
		
		/* 
		 * JButton for the user to select that calculates whether or not the user will make it
		 * to Oregon with the amount of food, food consumption, and miles per day that were selected
		 */
		JButton btnNewButton = new JButton("Travel!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Win message that is displayed when the user has enough food
				String winMessage = "You made it to Oregon in " + wagon.getTotalDays() + " days!";
				// Lose message when the user did not select enough food to make it to Oregon
				String loseMessage = "You did not make it to Oregon.";
				
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
				
				/*
				 * Runs calculation to see if the user packed enough food in the wagon, given
				 * the set food consumption rate and miles traveled per day.
				 * 
				 * Displays a win message if there is sufficient food, and a lose message 
				 * if there isn't enough food.
				 */
				else {
					ConsumptionErrorLabel.setText("");
					if(wagon.travel()) {
						JOptionPane.showMessageDialog(null, winMessage, "You Win!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, loseMessage, "You Lose!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(525, 578, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
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
				frame.getContentPane().add(label);
				label.setOpaque(false);
				label.setForeground(Color.WHITE);
				labels.add(label);
		}
	}
}
