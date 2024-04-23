package MP3Package;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainPanel extends JFrame{
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/AshHollow.JPG";
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(filename)));

	
	public MainPanel(Wagon wagon, String date) {
		System.out.println(this.getBounds().toString());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(new Rectangle(0, 0, 0, 0));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("align 50% 100%", "[][][][][][][][][][][][][][][][][][][][]", "[][][][]"));
		
		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel, "cell 9 0,alignx right,growy");
		
		JLabel Date = new JLabel(date);
		Date.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(Date, "cell 10 0,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Food: ");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1, "cell 9 1,alignx right,growy");
		
		JLabel TotalFood = new JLabel(Integer.toString(wagon.getTotalFood()));
		TotalFood.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(TotalFood, "cell 10 1,grow");
		
		JLabel lblNewLabel_1_1 = new JLabel("Next Landmark: ");
		lblNewLabel_1_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1_1, "cell 9 2,grow");
		
		JLabel landmarkLabel = new JLabel(Integer.toString(wagon.milesToLandmark()) + " miles");
		landmarkLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(landmarkLabel, "cell 10 2,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Miles Traveled: ");
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_2, "cell 9 3,alignx right,growy");
		
		JLabel milesLabel = new JLabel(Integer.toString(wagon.getMilesTraveled()) + " miles");
		milesLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(milesLabel, "cell 10 3,grow");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton lblNewLabel_3 = new JButton("Click to start travel!");
		lblNewLabel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(Color.BLACK);
		panel_1.add(lblNewLabel_3, BorderLayout.SOUTH);
		
		JLabel ImageJLabel = new JLabel("");
		ImageJLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageJLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(ImageJLabel, BorderLayout.CENTER);
		ImageJLabel.setBounds(new Rectangle(900, 600));
		int width = ImageJLabel.getWidth();
		int height = ImageJLabel.getHeight();
		Image img = backgroundImage.getImage();
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageJLabel.setIcon(new ImageIcon(newImg));
	}
}
