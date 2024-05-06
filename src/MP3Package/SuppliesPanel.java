package MP3Package;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SuppliesPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuppliesPanel(Wagon wagon, OregonTrail trail) {
		setLayout(new MigLayout("", "[grow][][][][grow,center][][][center][left][grow][][][]", "[][][][][][][][][][][][18.00,center]"));
		
		JLabel lblNewLabel = new JLabel("Supplies");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(lblNewLabel, "cell 4 0,alignx center");
		
		SuppliesPanel panel = this;
		JButton backButton = new JButton("Close");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				comp.getParent().getParent().remove(panel);
				trail.readdButtons();
				win.revalidate();
				win.repaint();
			}
		});
		backButton.setMargin(new Insets(4, 14, 0, 14));
		backButton.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		add(backButton, "cell 4 11,alignx center");
		
		for(int i = 0; i < wagon.getItems().size(); i++) {
			JLabel label = null;
			if(wagon.getItems().get(i).getName().contains("Food")) {
				label = new JLabel(wagon.getItems().get(i).getName() + ": " + wagon.getTotalFood());
			}
			else {
				label = new JLabel(wagon.getItems().get(i).getName() + ": " + wagon.getItems().get(i).getAmount());
			}
			label.setFont(new Font("Myanmar Text", Font.BOLD, 15));
			add(label, "cell 4 " + (i + 3));
		}
	}
	

}
