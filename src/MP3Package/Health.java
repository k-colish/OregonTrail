package MP3Package;

import java.util.ArrayList;

public class Health {
//Need, Wagon, Weather, random, river, people,

    /*current idea is to set all 4 party members to 0 health then each method will be called that will check current
    events of the game and then when a person gets to 140+ then that person dies

    make an array with all 4 player characters that will each method will alter with for loops
     */

    Health()
    {

    }

    public String overallHealth() //going to return healthy, 0-34 = good health, 35-65 = fair health,
                                //70-104 = poor health, 105-139 = very poor health
    {
        int totalHealth = 0;

        switch(totalHealth/getAmountPeople())
        {
        case 0-34: return "Good health"; break;
        case 35-65: return "Fair Health"; break;
        case 70-104: return "Poor Health"; break;
        case 105-139: return "Very poor health";break;
        default: return "No people exist";
        }
    }

    private void death() //displays that a person dies when they go 140+
    {

    }
    private int getAmountPeople() //return the size of the array
    {

    }

    private void travelingPace() //Steady = 2, Strenuous = 4, grueling = 6
    {

    }

    private int checkClothing()
    {

    }

    private void weatherHealth()
            //very hot = 2, hot = 1, cold = 2 if no clothing & between 0-2 if clothing is 1 per person
            //cold = 4 if no clothing & between 0-4 if clothing is 1 per person
    {

    }

    private void FoodHealth() // use this to increase health depending on how much food they get that day
            //Filling = 0, meager = 2, bare bones = 4, out of food = 6
    {

    }
    private void  illness() //if sick/injured add an extra 1 per sick/injured person
    {

    }

    public void addPoints(int points, int peopleAmount)
    {

    }
}
