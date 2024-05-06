package MP3Package;

import javax.swing.JOptionPane;

public class GameEnd {

    private Wagon wagon;
    private Health health;

    public GameEnd(Wagon wagonlist, Health list)
    {
        this.wagon = wagonlist;
        this.health = list;
    }

    public void gameOutCome()
    {
        if (wagon.getMilesTraveled() >= 1800)
        {
        	JOptionPane.showMessageDialog(null, "You WIN!",
                    "WINNER! WOOP WOOP!", JOptionPane.INFORMATION_MESSAGE);
            //add pane for WINNERS! WHOOP WHOOP.
            //displays how many people are left.
            //maybe add a bit about what the group did when they got to Sacramento.
        }

        if (health.getPeopleAmount() == 0)
        {
        	JOptionPane.showMessageDialog(null, "You LOSE!",
                    "LOSER! WOMP WOMP!", JOptionPane.INFORMATION_MESSAGE);
            //add pane for Losers! WOMP WOMP
            //displays that everyone died
        }
    }

}
