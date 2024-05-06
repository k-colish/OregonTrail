package MP3Package;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FortPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FortPanel(Destinations dest, Wagon wagon, javax.swing.Timer clock, OregonTrail trail) {
		setLayout(new MigLayout("", "[150px][150px][200px][150px][150px]", "[18px][18px][18px][18px][18px][18px][20px][]"));
		
		JLabel FortName = new JLabel("You made it to " + dest.getName() + "!");
		FortName.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		FortName.setHorizontalAlignment(SwingConstants.CENTER);
		add(FortName, "cell 0 0 5 1,alignx center,growy");
		
		FortPanel panel = this;
		
		JButton storeButton = new JButton("Go Shopping!");
		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Forts store = new Forts(wagon, panel, trail);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				comp.getParent().getParent().remove(panel);
				win.remove(panel);
				win.add(store, BorderLayout.CENTER, 0);
				win.revalidate();
			}
		});
		storeButton.setVerticalAlignment(SwingConstants.BOTTOM);
		storeButton.setMargin(new Insets(0, 14, 0, 14));
		storeButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(storeButton, "cell 0 4 2 1,growx");
		
		JButton talkButton = new JButton("Talk to People!");
		talkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DestinationActivities talk = new DestinationActivities();
				talk.talkToRandos();
			}
		});
		talkButton.setVerticalAlignment(SwingConstants.BOTTOM);
		talkButton.setMargin(new Insets(0, 14, 0, 14));
		talkButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(talkButton, "cell 3 4 2 1,growx");
		
		JButton tradeButton = new JButton("Trade");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trading trade = new Trading(wagon);
			}
		});
		tradeButton.setMargin(new Insets(0, 14, 0, 14));
		tradeButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(tradeButton, "cell 0 7 2 1,growx");
		
		JButton travelButton = new JButton("Continue on Trail");
		travelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				comp.getParent().getParent().remove(panel);
				win.remove(panel);
				win.repaint();
				clock.start();
			}
		});
		travelButton.setMargin(new Insets(0, 14, 0, 14));
		travelButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(travelButton, "cell 3 7 2 1,growx");
	}
	

}
