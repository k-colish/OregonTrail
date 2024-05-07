/** 
 * MP3.java 
 *
 * Simple educational Oregon Trail game, to test if you are able to make it
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
* Make the CSV files more readable for others, layout how the SCV works.
* Input validation for each area, "Defensive programming".
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

public class OregonTrail {

	private JFrame frame;
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/AshHollow.JPG";
	
	// Initialization of an object of the Wagon class
	private People people = new People();
	private Wagon wagon = new Wagon(people);

	// Initialize RandomEvents object
	private RandomEvents rndEvt = new RandomEvents(wagon, people);
	private boolean canScavenge = false;
	
	// ArrayList including all the items that can be added to the Wagon class object
	private ArrayList<Item> allItems = new ArrayList<Item>();
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(filename)));
	
	// Implement a clock travel repetition
	private javax.swing.Timer clock;
	
	// Variable for the date
	private GregorianCalendar calendar = new GregorianCalendar(1850, 2, 30);
	private String pattern = "MMMMM d, yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
	private String date = dateFormatter.format(calendar.getTime());
	private JLabel dateLabel = new JLabel(date);
	private JLabel healthLabel;
	
	// Instantiating labels, buttons, and panels
	private JLabel weatherLabel = new JLabel("Cool");
	private JLabel totalFood = new JLabel(Integer.toString(wagon.getTotalFood()));
	private JLabel landmarkLabel = new JLabel(Integer.toString(wagon.milesToLandmark()) + " miles");
	private JLabel milesLabel = new JLabel(Integer.toString(wagon.getMilesTraveled()) + " miles");
	private JPanel actionButtonPane = new JPanel();
	private JButton travelButton = new JButton("Click to start traveling!");
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();

	private RiverPanel riverPanel = null;
	private FortPanel fortPanel = null;
	
	// Initialize objects for people and their healths
	private Health healths = new Health(people, wagon, rndEvt);
	private Weather weather = new Weather();
	
	private OregonTrail trail = this;
	
	private GameEnd GameEnd = new GameEnd(wagon, healths);

	

	

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
		//people.setNames();
	}
	
		public void updateLabels() {
		dateLabel.setText(date);
		totalFood.setText(Integer.toString(wagon.getTotalFood()));
		landmarkLabel.setText(Integer.toString(wagon.milesToLandmark()));
		milesLabel.setText(Integer.toString(wagon.getMilesTraveled()) + " miles");
		healthLabel.setText(healths.overallHealth());
		weatherLabel.setText(weather.getWeather(wagon.getMilesTraveled(),calendar.MONTH));
	}
	
	public void readdButtons() {
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
	}

	/**
	 * clockActionPerformed - displays the JOptionPane with current run information AFTER the previous JOptionPane is closed
	 * @param e - ActionEvent
	 */
	public void clockActionPerformed(ActionEvent e) {
		travelButton.setVisible(true);
		calendar.add(GregorianCalendar.DATE, 1);
		date = dateFormatter.format(calendar.getTime());
		
		
		
		
		
		
		// Make it so the player can scavenge once after this clock cycle
		canScavenge = true;
		
		// Instantiate and update list in RandomEvents object
		rndEvt = new RandomEvents(wagon, people);
		RiverPanel riverPanel = null;
		
		Destinations dest = wagon.atDestination();
		if(dest != null) {
			clock.stop();
			travelButton.setVisible(false);
			
			if(dest.getName().contains("River")) {
				River river = new River(dest.getDistance(), dest.getName());
				riverPanel = new RiverPanel(river, healths, rndEvt, clock);
				frame.getContentPane().add(riverPanel, BorderLayout.CENTER, 1);
				riverPanel.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight() - panel.getHeight());

				
			}
			else {
				fortPanel = new FortPanel(dest, wagon, clock, this);
				frame.getContentPane().add(fortPanel, BorderLayout.CENTER, 1);
				fortPanel.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight() - panel.getHeight());
				
			}
		}

		

		// Print out for debugging
		System.out.println("Clock Action Performed");
		wagon.travel();
		healths.travelNeeds();
		
		
		if (wagon.getMilesTraveled() >= 1800)
        {
        	JOptionPane.showMessageDialog(null, "<html><center>You WIN!<center/><br><center> You took " + wagon.getTotalDaysTraveled() + " days to make it to California!<center/><br><center>You still had " + healths.getPeopleAmount() + " members of your party left!<center/><br><br><center>Have fun mining gold!",
                    "WINNER! WOOP WOOP!", JOptionPane.INFORMATION_MESSAGE);
        	System.exit(0);
            //add pane for WINNERS! WHOOP WHOOP.
            //displays how many people are left.
            //maybe add a bit about what the group did when they got to Sacramento.
        }
		if (healths.getPeopleAmount() == 0)
        {
        	JOptionPane.showMessageDialog(null, "<html><center>You LOSE!<center/><br><center>You traveled " + wagon.getMilesTraveled() + " miles before all of your party died.<center/>",
                    "LOSER! WOMP WOMP!", JOptionPane.INFORMATION_MESSAGE);
        	System.exit(0);
        }

		
		updateLabels();
		//GameEnd.gameOutCome();
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
		frame.setBounds(100, 100, 900, 775);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(new Rectangle(0, 0, 0, 0));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("align 50% 100%", "[][][][][][][][][][][][][][][][][][][][]", "[][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel, "cell 9 0,alignx right,growy");

		// Formatting for Date JLabel
		dateLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(dateLabel, "cell 10 0,grow");

		JLabel lblNewLabel_1 = new JLabel("Food: ");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1, "cell 9 1,alignx right,growy");

		// Formatting for TotalFood JLabel
		totalFood.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(totalFood, "cell 10 1,grow");

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
		
		JLabel lblNewLabel_4 = new JLabel("Health:");
		lblNewLabel_4.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_4, "cell 9 4,alignx right");
		
		healthLabel = new JLabel("Good Health");
		healthLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(healthLabel, "cell 10 4");
		
		JLabel lblNewLabel_3 = new JLabel("Weather:");
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_3, "cell 9 5,alignx right");
		
		
		weatherLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(weatherLabel, "cell 10 5");

		
		frame.getContentPane().add(panel_1, BorderLayout.CENTER, 1);
		panel_1.setLayout(new BorderLayout(0, 0));
		travelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!clock.isRunning()) {
					clock.start();
					travelButton.setText("Click to stop travel");
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
		ImageJLabel.setBounds(new Rectangle(0, 0, 900, 440));
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
				if(canScavenge) {
					rndEvt.scavenge();
					calendar.add(GregorianCalendar.DATE, 1);
					date = dateFormatter.format(calendar.getTime());
					canScavenge = false;
					updateLabels();
				}
				else {
					JOptionPane.showMessageDialog(null, "You must travel one more day before you can scavenge.");
				}
			}
		});
		scavengeButton.setBackground(Color.WHITE);
		scavengeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		scavengeButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(scavengeButton);
		
		JButton restButton = new JButton("Rest");
		restButton.setMargin(new Insets(2, 12, 2, 12));
		restButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int days = wagon.rest(healths);
				calendar.add(GregorianCalendar.DATE, days);
				date = dateFormatter.format(calendar.getTime());
				wagon.changeTotalFood(-1 * (healths.getPeopleAmount() * wagon.getFoodConsumption()));
				updateLabels();
				healths.restNeeds();
				System.out.println(healths.healthTotalScore());
			}
		});
		restButton.setBackground(Color.WHITE);
		restButton.setHorizontalTextPosition(SwingConstants.CENTER);
		restButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(restButton);
		
		JButton tradeButton = new JButton("Trade");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trading trade = new Trading(wagon, trail);
			}
		});
		tradeButton.setBackground(Color.WHITE);
		tradeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		tradeButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(tradeButton);
		
		JButton suppliesButton = new JButton("Check Supplies");
		suppliesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuppliesPanel panel = new SuppliesPanel(wagon, trail, clock, null);
				frame.getContentPane().add(panel, BorderLayout.CENTER, 1);
				panel.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight() - panel.getHeight());
				actionButtonPane.setVisible(false);
				travelButton.setVisible(false);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		suppliesButton.setMargin(new Insets(2, 10, 2, 10));
		suppliesButton.setBackground(Color.WHITE);
		suppliesButton.setHorizontalTextPosition(SwingConstants.CENTER);
		suppliesButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(suppliesButton);
		
		JButton paceButton = new JButton("Change Pace");
		paceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int miles = 12;
				Integer[] options = {12, 13, 14, 15, 16, 17, 18, 19, 20};
				
				miles = (int) JOptionPane.showInputDialog(null, "How many miles a day would you like to travel?",
						"Travel Pace", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				wagon.setMilesPerDay(miles);
			}
		});
		paceButton.setBackground(Color.WHITE);
		paceButton.setHorizontalTextPosition(SwingConstants.CENTER);
		paceButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(paceButton);
		
		JButton rationsButton = new JButton("Change Rations");
		rationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pounds = 2;
				Integer[] options = {1, 2, 3};
				
				pounds = (int) JOptionPane.showInputDialog(null, "How many pounds of food per person a day would you like to consume?",
						"Food Consumption", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				wagon.setFoodConsumption(pounds);
			}
		});
		rationsButton.setBackground(Color.WHITE);
		rationsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		rationsButton.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		actionButtonPane.add(rationsButton);
		
		Forts store = new Forts(wagon, null, this);
		store.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight() - panel.getHeight());
		
		frame.getContentPane().add(store, BorderLayout.CENTER);
		
		
		// Instantiate timer
		clock = new javax.swing.Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed( evt);
			}});
	}
}
