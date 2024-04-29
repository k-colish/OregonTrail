package MP3Package;

import java.util.ArrayList;
import java.util.Random;

public class Health {
//Need, Wagon, Weather, random, river, people,

    /*
    current idea is to set all 4 party members to 0 health then each method will be called that will check current
    events of the game and then when a person gets to 140+ then that person dies

    make an array with all 4 player characters that will each method will alter with for loops
    */

    private ArrayList<Integer> Healths = new ArrayList<Integer>();
    private People name;
    private Wagon itemlist;
    private RandomEvents random;
    int illnessCount = 0;

    Health(People names, Wagon wlist, RandomEvents event)
    {
        this.name = names;
        this.itemlist = wlist;
        this.random = event;
    }

    public String overallHealth() //going to return healthy, 0-34 = good health, 35-65 = fair health,
                                //70-104 = poor health, 105-139 = very poor health
    {
        if (healthScores()/getPeopleAmount() <= 34) {return "Good health";}
        else if (healthScores()/getPeopleAmount() >= 35 && healthScores()/getPeopleAmount() <= 65) {return "Fair Health";}
        else if (healthScores()/getPeopleAmount() >= 70 && healthScores()/getPeopleAmount() <= 104) {return "Poor Health";}
        else if (healthScores()/getPeopleAmount() >= 105) {return "Very poor health";}
        else {return "No people exist";}
    }

    public void travelNeeds() // Used when the player travels
    {
        death();
        travelingPace();
        FoodHealth();
        illness();
    }

    public void restNeeds() // Used when the player rests
    {
        death();
        FoodHealth();
        restImprovement();
        illness();
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
                System.out.println("DEAD");
            }
        }
    }

    public int getPeopleAmount() {return Healths.size();} //return the size of the array

    private void travelingPace() //Steady = 2, Strenuous = 4, grueling = 6 // needs changed for 12-20 miles
    {
        if (itemlist.getMilesPerDay() <= 13)
            addPoints(2, getPeopleAmount());
        else if (itemlist.getMilesPerDay() > 13 && itemlist.getMilesPerDay() <= 16)
            addPoints(4, getPeopleAmount());
        else if (itemlist.getMilesPerDay() > 16)
            addPoints(6, getPeopleAmount());
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
        if (itemlist.getTotalFood() <= 0) // out of food
            addPoints(6, getPeopleAmount());
        else if (itemlist.getFoodConsumption() == 1) // bare bones
            addPoints(4, getPeopleAmount());
        else if (itemlist.getFoodConsumption() == 2) // meager
            addPoints(2, getPeopleAmount());
    }

    private void  illness() //if sick/injured, add an extra 1 per sick/injured person
    {
        if (random.illness())
            illnessCount++;
        addPoints(illnessCount, getPeopleAmount());
    }

    private void restImprovement()
    {
        Random rnd = new Random();
        int rmd = rnd.nextInt(10) + 1;
        addPoints(rmd, getPeopleAmount());
    }

    public void addPoints(int points, int peopleAmount)
    {for (int i = 0; i < peopleAmount; i++) {Healths.add(points, i);}}
}