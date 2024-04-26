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

public class RiverPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public RiverPanel(River river) {
		setLayout(null);
		
		// General Label creation
		// Text include the passed river's name
		JLabel RiverName = new JLabel("You made it to " + river.getName() + "!");
		RiverName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverName.setHorizontalAlignment(SwingConstants.CENTER);
		RiverName.setBounds(125, 11, 244, 18);
		add(RiverName);
		
		// Label's text includes the passed river's depth
		JLabel RiverDepth = new JLabel("The river is " + river.getDepth() + " feet deep.");
		RiverDepth.setHorizontalAlignment(SwingConstants.LEFT);
		RiverDepth.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverDepth.setBounds(36, 108, 238, 18);
		add(RiverDepth);
		
		// Label's text includes the passed river's width
		JLabel RiverWidth = new JLabel("The river is " + river.getWidth() + " feet wide.");
		RiverWidth.setHorizontalAlignment(SwingConstants.LEFT);
		RiverWidth.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverWidth.setBounds(36, 137, 213, 18);
		add(RiverWidth);
		
		// Label's text is set based on the passed river's flow value (1 for slowest flow, 3 for the fastest).
		JLabel RiverFlow = new JLabel("");
		switch(river.getFlow()) {
		case 1: RiverFlow.setText("The river's current is slow"); break;
		case 2: RiverFlow.setText("The river's current is steady"); break;
		case 3: RiverFlow.setText("The river's current is fast"); break;
		}
		RiverFlow.setHorizontalAlignment(SwingConstants.LEFT);
		RiverFlow.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverFlow.setBounds(36, 171, 300, 18);
		add(RiverFlow);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblWhatWouldYou.setBounds(179, 255, 160, 18);
		add(lblWhatWouldYou);
		
		
		// Display the options for the user
		JLabel lblFordTheRiver = new JLabel("Ford the river: Enter 1");
		lblFordTheRiver.setHorizontalAlignment(SwingConstants.LEFT);
		lblFordTheRiver.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFordTheRiver.setBounds(36, 289, 130, 18);
		add(lblFordTheRiver);
		
		JLabel lblFloatAcrossThe = new JLabel("Float across the river: Enter 2");
		lblFloatAcrossThe.setHorizontalAlignment(SwingConstants.LEFT);
		lblFloatAcrossThe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFloatAcrossThe.setBounds(36, 318, 174, 18);
		add(lblFloatAcrossThe);
		
		JLabel lblFordTheRiver_1_1 = new JLabel("Pay for a Ferry: Enter 3");
		lblFordTheRiver_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFordTheRiver_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFordTheRiver_1_1.setBounds(36, 347, 141, 18);
		add(lblFordTheRiver_1_1);
		
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
							" to pay for the ferry. Do you still want to take the ferry?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
							new Object[] {"Yes", "No"}, -1);
					
					// If the user selects 'Yes' the user pays for the ferry and makes it across
					if(res == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "You made it across the river!",
								"", JOptionPane.INFORMATION_MESSAGE);
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
						}
						
						// Otherwise, only tell the user that the wagon was lost in the current of the river ( Will add other consequences in later version )
						else {
							JOptionPane.showMessageDialog(null, "The river's current was " +
									"too strong, but your party made it across safely.",
									"", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				
				// When the user enters '1' the user chose to ford the river
				else if(Integer.parseInt(textField.getText()) == 1) {
					
					// Calls the fordRiver method, which returns true when they successfully cross the river
					if(river.fordRiver()) {
						
						// Display message to the user that they made it across
						JOptionPane.showMessageDialog(null, "You successfully forded the " +
								"river!", "", JOptionPane.INFORMATION_MESSAGE);
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
						}
						
						// Otherwise, only tell the user that the wagon was lost in the current of the river ( Will add other consequences in later version )
						else {
							JOptionPane.showMessageDialog(null, "The river was too deep" +
									" to ford, but your party made it across safely.",
									"", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else {
					System.out.println(textField.getText());
				}
			}
		});
		textField.setBounds(225, 376, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Your Decision:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(97, 376, 124, 18);
		add(lblNewLabel);
	}
}
