package MP3Package;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundImage extends JPanel {
    
    private static final long serialVersionUID = 1L;
	private String filename = "/Images/Ash Hollow.JPG";
    private ImageIcon backgroundImage = new ImageIcon(getClass().getResource(filename));
    private JLabel imageLabel; 
    
    public BackgroundImage() {
        setLayout(null); // Set layout to null for absolute positioning
        
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 734, 661);
        add(imageLabel);
        imageLabel.setIcon(backgroundImage);
    }
}
