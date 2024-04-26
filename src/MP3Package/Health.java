package MP3Package;

import java.util.ArrayList;

public class Health {
//Need, Wagon, Weather, random, river, people,

    /*
    current idea is to set all 4 party members to 0 health then each method will be called that will check current
    events of the game and then when a person gets to 140+ then that person dies

    make an array with all 4 player characters that will each method will alter with for loops
    */

    private ArrayList<Integer> Healths = new ArrayList<Integer>();
    private ArrayList<People> people;
    private People names;
    private Wagon itemlist;
    private RandomEvents random;

    Health(ArrayList<People> names, Wagon wlist, RandomEvents event)
    {
        this.people = names;
        this.itemlist = wlist;
        this.random = event;
    }

    public String overallHealth() //going to return healthy, 0-34 = good health, 35-65 = fair health,
                                //70-104 = poor health, 105-139 = very poor health
    {
        death();
        int score  = healthScores()/getPeopleAmount();
        if (score >= 0 && score <= 34) {return "Good health";}
        if (score >= 35 && score <= 65) {return "Fair Health";}
        else if (score >= 70 && score <= 104) {return "Poor Health";}
        else if (score >= 105 && score <= 139) {return "Very poor health";}
        else {return "No people exist";}
    }

    public int healthScores()
    {
        int score = 0;
        for (int i = 0; i < Healths.size(); i ++) {score += Healths.get(i);}
        return score;
    }

    private void death() // displays that a person dies when they go 140+
    {
        for (int i = 0; i < Healths.size(); i ++)
        {
            if (Healths.get(i) > 140)
            {
                //add name thing to say which one died using massage dialog
                Healths.remove(i);
            }
        }
    }

    private int getPeopleAmount() {return Healths.size();} //return the size of the array

    private void travelingPace() //Steady = 2, Strenuous = 4, grueling = 6 // needs changed for 12-20 miles
    {
        //if ()
    }

    private void weatherHealth()
            //very hot = 2, hot = 1, cold = 2 if no clothing & between 0-2 if clothing is 1 per person
            //cold = 4 if no clothing & between 0-4 if clothing is 1 per person
    {
        //if (return itemlist.getItems().get(Integer.parseInt("Clothing")).getAmount(); < 2//4)
    }

    private void FoodHealth() // use this to increase health depending on how much food they get that day
            //Filling = 0, meager = 2, bare bones = 4, out of food = 6
    {
        if (itemlist.getTotalFood() == 0) // out of food
            addPoints(6, 4);
        else if (itemlist.getFoodConsumption() == 1) // bare bones
            addPoints(4, 4);
        else if (itemlist.getFoodConsumption() == 2) // meager
            addPoints(4, 2);
    }

    private void  illness() //if sick/injured, add an extra 1 per sick/injured person
    {
        int illnessCount = 0;
        if (random.illness())
            illnessCount++;
        addPoints(illnessCount, 4);
    }

    public void addPoints(int points, int peopleAmount)
    {for (int i = 0; i < peopleAmount; i++) {Healths.add(points, i);}}
}