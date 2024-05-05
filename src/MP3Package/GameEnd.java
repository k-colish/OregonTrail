package MP3Package;

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
            //add pane for WINNERS! WHOOP WHOOP.
            //displays how many people are left.
            //maybe add a bit about what the group did when they got to Sacramento.
        }

        if (health.getPeopleAmount() <= 0)
        {
            //add pane for Losers! WOMP WOMP
            //displays that everyone died
        }
    }

}
