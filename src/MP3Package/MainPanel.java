package MP3Package;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;



public class MainPanel extends JFrame{
	
	private GregorianCalendar calendar = new GregorianCalendar(1850, 2, 30);
	private String pattern = "MMMMM dd, yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
	private String date = dateFormatter.format(calendar.getTime());
	
	// File name and JLabel for use with the background image
	private String filename = "/Images/AshHollow.JPG";
	
	// Setting the ImageIcon for use with ImageLabel
	private ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(filename)));

	
	public MainPanel(Wagon wagon) {
		System.out.println(this.getBounds().toString());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(new Rectangle(0, 0, 0, 0));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("align 50% 100%", "[][][][][][][][][][][][][][][][][][][][]", "[][][][]"));
		
		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel, "cell 9 0,alignx right");
		
		JLabel Date = new JLabel(date);
		Date.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(Date, "cell 10 0");
		
		JLabel lblNewLabel_1 = new JLabel("Food: ");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1, "cell 9 1,alignx right");
		
		JLabel TotalFood = new JLabel(Integer.toString(wagon.getTotalFoodWeight()));
		TotalFood.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(TotalFood, "cell 10 1");
		
		JLabel lblNewLabel_1_1 = new JLabel("Next Landmark: ");
		lblNewLabel_1_1.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_1_1, "cell 9 2,alignx right");
		
		JLabel landmarkLabel = new JLabel(Integer.toString(wagon.milesToLandmark()) + " miles");
		landmarkLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(landmarkLabel, "cell 10 2");
		
		JLabel lblNewLabel_2 = new JLabel("Miles Traveled: ");
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(lblNewLabel_2, "cell 9 3,alignx right");
		
		JLabel milesLabel = new JLabel(Integer.toString(wagon.getMilesTraveled()) + " miles");
		milesLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		panel.add(milesLabel, "cell 10 3");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Press 'ENTER' to size up the situation");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setOpaque(true);
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
