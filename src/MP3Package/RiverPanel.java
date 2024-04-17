package MP3Package;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class RiverPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public RiverPanel(River river) {
		setLayout(null);
		
		JLabel RiverName = new JLabel("You made it to " + river.getName() + "!");
		RiverName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverName.setHorizontalAlignment(SwingConstants.CENTER);
		RiverName.setBounds(125, 11, 244, 18);
		add(RiverName);
		
		JLabel RiverDepth = new JLabel("The river is " + river.getDepth() + " feet deep.");
		RiverDepth.setHorizontalAlignment(SwingConstants.LEFT);
		RiverDepth.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverDepth.setBounds(36, 108, 238, 18);
		add(RiverDepth);
		
		JLabel RiverWidth = new JLabel("The river is " + river.getWidth() + " feet wide.");
		RiverWidth.setHorizontalAlignment(SwingConstants.LEFT);
		RiverWidth.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RiverWidth.setBounds(36, 137, 213, 18);
		add(RiverWidth);
		
		
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
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Did action");
				if(Integer.parseInt(textField.getText()) == 3) {
					JOptionPane pane = new JOptionPane();
					int res = pane.showOptionDialog(null, "It will cost $" + river.takeFerry() + " to pay for the ferry. Do you still want to take the ferry?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Yes", "No"}, -1);
					if(res == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "You made it across the river!", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(Integer.parseInt(textField.getText()) == 2) {
					if(river.floatRiver()) {
						JOptionPane.showMessageDialog(null, "You made it across the river!", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						Random rnd = new Random();
						int drown = rnd.nextInt(10) + 1;
						if(drown <= 3) {
							JOptionPane.showMessageDialog(null, "The river's current was too strong and one member of your party drowned!");
						}
						else {
							JOptionPane.showMessageDialog(null, "The river's current was too strong, but your party made it across safely.", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else if(Integer.parseInt(textField.getText()) == 1) {
					if(river.fordRiver()) {
						JOptionPane.showMessageDialog(null, "You successfully forded the river!", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						Random rnd = new Random();
						int drown = rnd.nextInt(10) + 1;
						if(drown <= 3) {
							JOptionPane.showMessageDialog(null, "The river was too deep to ford and one member of your party drowned!");
						}
						else {
							JOptionPane.showMessageDialog(null, "The river was too deep to ford, but your party made it across safely.", "", JOptionPane.INFORMATION_MESSAGE);
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
