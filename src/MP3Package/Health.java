package MP3Package;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class for managing the health status of party members.
 */
public class Health {

    private ArrayList<Integer> healths = new ArrayList<>(4);
    private People people;
    private Wagon itemlist;
    private RandomEvents random;
    private int illnessCount = 0;

    /**
     * Constructor for Health class.
     * @param names The list of people in the game.
     * @param wlist The wagon object containing items and data.
     * @param event The random events object for generating events.
     */
    Health(People names, Wagon wlist, RandomEvents event) {
        int peopleStartnumber = 4;
        this.people = names;
        this.itemlist = wlist;
        this.random = event;
        for (int j = 0; j < peopleStartnumber; j++) {healths.add(j, 1);}
    }

    /**
     * Get the overall health status of the party.
     * @return A string indicating the overall health status.
     */
    public String overallHealth() {
        //going to return healthy, 0-34 = good health, 35-65 = fair health,
        // 70-104 = poor health, 105-139 = very poor health

        System.out.println("Healths: " + healthTotalScore()/getPeopleAmount());
        if (healthTotalScore()/getPeopleAmount() <= 34) {return "Good health";}
        else if (healthTotalScore()/getPeopleAmount() >= 35 && healthTotalScore()/getPeopleAmount() <= 69) {return "Fair Health";}
        else if (healthTotalScore()/getPeopleAmount() >= 70 && healthTotalScore()/getPeopleAmount() <= 104) {return "Poor Health";}
        else if (healthTotalScore()/getPeopleAmount() >= 105) {return "Very poor health";}
        else {return "No people exist, its all in your mind. HA HA HA";}
    }

    /**
     * Adjust health based on travel-related needs.
     */
    public void travelNeeds() { // Used when the player travels
        death();
        travelingPace();
        FoodHealth();
        illness();
    }

    /**
     * Adjust health based on resting-related needs.
     */
    public void restNeeds() { // Used when the player rests
        death();
        FoodHealth();
        restImprovement();
        illness();
    }

    /**
     * Calculate the total health score of all party members combined.
     * @return The total health score.
     */
    public int healthTotalScore() { //used to get the total score of all group members combined
        int score = 0;
        for (int i = 0; i < getPeopleAmount(); i ++) {score += healths.get(i);}
        return score;
    }

    /**
     * Check for deaths among party members.
     */
    private void death() { // displays that a person dies when they go 140+
        for (int i = 0; i < getPeopleAmount(); i ++) {
        	System.out.println(i + "'s health: " + healths.get(i));
            if (healths.get(i) > 140) {
                String person = people.getNames().get(random.randomValue(getPeopleAmount())-1);
                JOptionPane.showMessageDialog(null, person + " has died.",
                        "A party member has died!", JOptionPane.INFORMATION_MESSAGE);
                people.removeName(person);
                healths.remove(i);
                System.out.println("DEAD");
            }
        }
    }

    /**
     * Get the number of people in the party.
     * @return The number of people in the party.
     */
    public int getPeopleAmount() {return healths.size();} //return the size of the array

    /**
     * Adjust health based on the pace of travel.
     */
    private void travelingPace() { //Steady = 2, Strenuous = 4, grueling = 6 // needs changed for 12-20 miles
        if (itemlist.getMilesPerDay() <= 13)
            addPoints(2, getPeopleAmount());
        else if (itemlist.getMilesPerDay() > 13 && itemlist.getMilesPerDay() <= 16)
            addPoints(4, getPeopleAmount());
        else if (itemlist.getMilesPerDay() > 16)
            addPoints(6, getPeopleAmount());
    }

    /**
     * Adjust health based on weather conditions.
     */
    private void weatherHealth() {
        //very hot = 2, hot = 1, cold = 2 if no clothing & between 0-2 if clothing is 1 per person
        // cold = 4 if no clothing & between 0-4 if clothing is 1 per person

//        Item item = new Item(1, "clothing", 0);
//        if (weather thing && item.getAmount()/getPeopleAmount() < 2) {addPoints(2, getPeopleAmount());}
//        else if (weather thing && item.getAmount()/getPeopleAmount() < 4) {addPoints(4, getPeopleAmount());}
//        else if (//add that if its bad weather they still get 1 point and have clothing) {addPoints(1, getPeopleAmount());}
    }

    /**
     * Adjust health based on food consumption.
     */
    private void FoodHealth() {
        // use this to increase health depending on how much food they get that day
        // Filling = 0, meager = 2, bare bones = 4, out of food = 6

        if (itemlist.getTotalFood() <= 0) // out of food
            addPoints(6, getPeopleAmount());
        else if (itemlist.getFoodConsumption() == 1) // bare bones
            addPoints(4, getPeopleAmount());
        else if (itemlist.getFoodConsumption() == 2) // meager
            addPoints(2, getPeopleAmount());
    }

    /**
     * Check for illnesses among party members.
     */
    private void illness() { //if sick/injured, add an extra 1 per sick/injured person
        if (random.isillness()) {
            illnessCount++;
        }
        addPoints(illnessCount, getPeopleAmount());
    }

    /**
     * Adjust health based on rest-related improvements.
     */
    private void restImprovement() { // when the player stops to rest each player losses some points
        addPoints((-1 * 7), getPeopleAmount());
    }

    /**
     * Add or subtract health points for each party member.
     * @param points The number of points to add or subtract.
     * @param peopleAmount The number of people in the party.
     */
    public void addPoints(int points, int peopleAmount)
    {
    	for (int i = 0; i < peopleAmount; i++) {
    		healths.set(i, healths.get(i) + points);
    		System.out.println("Added " + points + " points.");
    	}
    }
}