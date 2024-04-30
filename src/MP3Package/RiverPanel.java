/** 
 * RiverPanel.java 
 * 
 * Creates a GUI representation when the player reaches a river and needs to decide which method they would like to use
 * to cross the river. Displays the result of the users choice using a JOptionPane.
 * 
 * @author - Kaiden Colish, Justin Schiefer, Zachary Iles, & Mitchell Gerwin
 * @version - 1.0.0 - 4/17/24
 */
package MP3Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import net.miginfocom.swing.MigLayout;

public class RiverPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private int status = 0;
	
	public RiverPanel(River river) {
		setLayout(new MigLayout("", "[300px][4px][400px]", "[18px][18px][18px][18px][18px][18px][20px][]"));
		
		// General Label creation
		// Text include the passed river's name
		JLabel RiverName = new JLabel("You made it to " + river.getName() + "!");
		RiverName.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		RiverName.setHorizontalAlignment(SwingConstants.CENTER);
		add(RiverName, "cell 1 0 2 1,alignx left,growy");
		
		// Label's text includes the passed river's depth
		String depthMsg = String.format("The river is %.2f feet deep.", river.getDepth());
		JLabel RiverDepth = new JLabel(depthMsg);
		RiverDepth.setHorizontalAlignment(SwingConstants.CENTER);
		RiverDepth.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(RiverDepth, "cell 1 1 2 1,alignx left,growy");
		
		// Label's text includes the passed river's width
		String widthMsg = String.format("The river is %.2f feet wide.", river.getWidth());
		JLabel RiverWidth = new JLabel(widthMsg);
		RiverWidth.setHorizontalAlignment(SwingConstants.CENTER);
		RiverWidth.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(RiverWidth, "cell 1 2 2 1,alignx left,growy");
		
		// Label's text is set based on the passed river's flow value (1 for slowest flow, 3 for the fastest).
		JLabel RiverFlow = new JLabel("");
		switch(river.getFlow()) {
		case 1: RiverFlow.setText("The river's current is slow"); break;
		case 2: RiverFlow.setText("The river's current is steady"); break;
		case 3: RiverFlow.setText("The river's current is fast"); break;
		}
		RiverFlow.setHorizontalAlignment(SwingConstants.CENTER);
		RiverFlow.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(RiverFlow, "cell 1 3 2 1,alignx left,growy");
		
		// Create textField for user input
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Debugging
				System.out.println("Did action");		
				
				// If structure checks the input of the textField to process the user's decision
				// When input is '3' the user chose to take the ferry
				if(Integer.parseInt(textField.getText()) == 3) {
					
					JOptionPane pane = new JOptionPane();
					
					// Display the option to get user input on whether they want to pay for the ferry or not
					int res = pane.showOptionDialog(null, "It will cost $" + river.takeFerry() +
							"0 to pay for the ferry. Do you still want to take the ferry?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
							new Object[] {"Yes", "No"}, -1);
					
					// If the user selects 'Yes' the user pays for the ferry and makes it across
					if(res == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "You made it across the river!",
								"", JOptionPane.INFORMATION_MESSAGE);
						status = 4;
					}
					// Otherwise, the pane closes and the user can input another selection
					
					
				}
				
				// When input is '2' the user chose to float across the river
				else if(Integer.parseInt(textField.getText()) == 2) {
					
					// Calls the floatRiver method, which returns true if they make it across the river
					if(river.floatRiver()) {
						
						// Display to the user that they made it across the river
						JOptionPane.showMessageDialog(null, "You made it across the river!",
								"", JOptionPane.INFORMATION_MESSAGE);
						status = 1;
					}
					
					// If here, the wagon did not make it across the river
					else {
						
						// Create a Random object to handle the chance of a person drowning
						Random rnd = new Random();
						int drown = rnd.nextInt(10) + 1;
						
						// Gives a 30% chance that one person will drown when the wagon does not make it across
						if(drown <= 3) {
							// Tell the user that they did not make it and that one person has died
							JOptionPane.showMessageDialog(null, "The river's current was " +
									"too strong and one member of your party drowned!");
							status = 2;
						}
						
						// Otherwise, only tell the user that the wagon was lost in the current of the river ( Will add other consequences in later version )
						else {
							JOptionPane.showMessageDialog(null, "The river's current was " +
									"too strong, but your party made it across safely.",
									"", JOptionPane.INFORMATION_MESSAGE);
							status = 3;
						}
					}
				}
				
				// When the user enters '1' the user chose to ford the river
				else if(Integer.parseInt(textField.getText()) == 1) {
					
					// Calls the fordRiver method, which returns true when they successfully cross the river
					if(river.fordRiver()) {
						
						// Display message to the user that they made it across
						JOptionPane.showMessageDialog(null, "You successfully forded the river!", "", JOptionPane.INFORMATION_MESSAGE);
						
						status = 1;
					}
					
					// Otherwise, the wagon did not make it across
					else {
						
						// Create a Random Object to handle the chance of a person drowning
						Random rnd = new Random();
						int drown = rnd.nextInt(10) + 1;
						
						// Gives a 30% chance that one person will drown when the wagon does not make it across
						if(drown <= 3) {
							
							// Tell the user that they did not make it and that one person has died
							JOptionPane.showMessageDialog(null, "The river was too deep" +
									" to ford and one member of your party drowned!");
							status = 2;
						}
						
						// Otherwise, only tell the user that the wagon was lost in the current of the river ( Will add other consequences in later version )
						else {
							JOptionPane.showMessageDialog(null, "The river was too deep" +
									" to ford, but your party made it across safely.",
									"", JOptionPane.INFORMATION_MESSAGE);
							status = 3;
						}
					}
				}
				else {
					System.out.println(textField.getText());
				}
			}
		});
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblWhatWouldYou, "cell 0 7 3 1,alignx center,growy");
		
		
		// Display the options for the user
		JLabel lblFordTheRiver = new JLabel("Ford the river: Enter 1");
		lblFordTheRiver.setHorizontalAlignment(SwingConstants.LEFT);
		lblFordTheRiver.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblFordTheRiver, "cell 0 8 3 1,alignx center,growy");
		
		JLabel lblFloatAcrossThe = new JLabel("Caulk the wagon and float across: Enter 2");
		lblFloatAcrossThe.setHorizontalAlignment(SwingConstants.LEFT);
		lblFloatAcrossThe.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblFloatAcrossThe, "cell 0 9 3 1,alignx center,growy");
		
		JLabel lblFordTheRiver_1_1 = new JLabel("Take a Ferry: Enter 3");
		lblFordTheRiver_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFordTheRiver_1_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblFordTheRiver_1_1, "cell 0 10 3 1,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("Enter Your Decision:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblNewLabel, "cell 0 11,alignx right,growy");
		add(textField, "cell 2 11,alignx left,aligny top");
		textField.setColumns(10);
	}
	
	
	/**
	 * getStatus - returns the status from crossing the river
	 * @return the status from crossing the river (1: made it across river safely, 2: failed to make it across + party member died, 
	 * 3: failed to cross, but everyone is safe, 4: took the ferry across)
	 */
	public int getStatus() {
		return status;
	}
}
