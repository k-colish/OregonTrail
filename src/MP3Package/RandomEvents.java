package MP3Package;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for generating random events during the game.
 */
public class RandomEvents {
    private Random rnd = new Random();
    private Wagon wagon;
    private People people;

    /**
     * Constructor for RandomEvents class.
     * @param wagonlist The wagon object for which events are generated.
     * @param list The list of people in the game.
     */
    public RandomEvents(Wagon wagonlist, People list)
    {
        this.wagon = wagonlist;
        this.people = list;
    }

    /**
     * Generate a random event.
     */
    public void allEvents() {
        switch(randomValue(18)) {
            case 1: Thief(); break;
            case 2: indiansHelp(); break;
            case 3: severeThunderstorm(); break;
            case 4: severeBlizzard(); break;
            case 5: heavyFog(); break;
            case 6: hailStorm(); break;
            case 7: deadOX(); break;
            case 8: injuredMember(); break;
            case 9: snakeBite(); break;
            case 10: loseTrial(); break;
            case 11: fruit(); break;
            case 12: wagonFire(); break;
            case 13: memberLost(); break;
            case 14: findWagon(); break;
            case 15: oxWanders(); break;
            case 16: water(); break;
            case 17: badGrass(); break;
            case 18: clothingHealth(); break;
            default: System.err.println("Error in allEvents");
        }
    }

    /**
     * Generate random events when resting.
     */
    public void restEvents() { //used for the player decides to rest for the day
        switch(randomValue(9)) {
            case 1: Thief(); break;
            case 2: indiansHelp(); break;
            case 3: injuredMember(); break;
            case 4: snakeBite(); break;
            case 5: fruit(); break;
            case 6: wagonFire(); break;
            case 7: memberLost(); break;
            case 8: oxWanders(); break;
            case 9: clothingHealth(); break;
            default: System.err.println("Error in restEvents");
        }
    }

    /**
     * Generate environment-related events.
     */
    public void environmentEvents() {
        switch(randomValue(6)) {
            case 1: severeThunderstorm(); break;
            case 2: severeBlizzard(); break;
            case 3: heavyFog(); break;
            case 4: hailStorm(); break;
            case 5: water(); break;
            case 6: badGrass(); break;
            default: System.err.println("Error in environmentEvents");
        }
    }

    /**
     * Generate a random integer value.
     * @param value The upper limit for the random value.
     * @return The random integer value.
     */
    public int randomValue(int value) {
        int rmd = rnd.nextInt(value) + 1;
        return rmd;
    }

    /**
     * Event: Thief - 2% chance, lots of supplies lost.
     */
    private void Thief() { //2% & lots of supplies lost, does not currently work
    	int food = 0;
        String lostItems = "";
        if (randomValue(50) == 1) {
            System.out.println("Thief");
            for (int i = 0; i < randomValue(5); i++) {
                ArrayList<Item> ThiefItems = wagon.getItems();
                int j = randomValue(ThiefItems.size()) - 1;
                lostItems += ThiefItems.get(j).getName()+"\n";
                wagon.removeItemAmount(ThiefItems.get(j).getName(), 1);
            }
            if(wagon.getTotalFood() > 0) {
                food = randomValue(wagon.getTotalFood());
                wagon.changeTotalFood(-food);
            	JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + 
                        food + " pounds of food!", "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);
            }
            else JOptionPane.showMessageDialog(null, "You have lost: " + lostItems,
                    "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Indians Help - 5% chance, gain 30 pounds of food.
     */
    private void indiansHelp() { // 5% chance to get 30 pounds of food
        if (randomValue(20) == 1) {
            wagon.changeTotalFood(30);
            JOptionPane.showMessageDialog(null, "You have gained 30 pounds of food!",
                    "Indians have come to help", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("indians");
        }
    }

    /**
     * Event: Severe Thunderstorm - 15% chance, lose a day.
     */
    private void severeThunderstorm() { // 15% & lose a day // Need to implement weather
        if (randomValue(17) <= 3) {
            wagon.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a thunderstorm and" +
                            " lost one day.","Severe Thunderstorm", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("thunderstorm");
        }
    }

    /**
     * Event: Severe Blizzard - 15% chance, lose a day.
     */
    private void severeBlizzard() { // 15% & lose a day
        if (randomValue(17) <= 3 || wagon.getMilesTraveled() > 1650 && wagon.getMilesTraveled() < 1750){
            wagon.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a blizzard and lost one day.",
                    "Severe Blizzard", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("blizzard");
        }
    }

    /**
     * Event: Heavy Fog - 6% chance, 50% lose a day.
     */
    private void heavyFog() { // 6% after fort Hall & 50% lose a day
        // using salt lake city instead of fot Hall, which is 1235 miles from the start.
        if (wagon.getMilesTraveled() > 1235 && randomValue(2) == 1) {
            System.out.println("FOG");
            wagon.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in heavy fog and lost one day.",
                    "Heavy Fog", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Hail Storm - 6% chance, 50% lose a day.
     */
    private void hailStorm() { // 6% before fort Hall & 50% lose a day
        // using salt lake city instead of fot Hall, which is 1235 miles from the start.
        if (wagon.getMilesTraveled() < 1235 && randomValue(2) == 1) {
            System.out.println("hail");
            wagon.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a hail storm and lost one " +
                    "day.", "Hail Storm", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Dead Ox - 2.5% chance, lose an ox.
     */
    private void deadOX() { // 2.5%
        if (randomValue(40) == 1) {
            System.out.println("RIP OX");
            wagon.removeItemAmount("Oxen", 1);
            JOptionPane.showMessageDialog(null, "One of your oxen has died.", "Oxen died",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Injured Member - 2.5% chance, a party member gets injured.
     */
    private void injuredMember() { // 2.5% & gain 20 points
        Health healths = new Health(people, wagon, this);
        String part = "";
        if (randomValue(40) == 1) {
            switch(randomValue(6)) {
                case 1: part = "arm"; break;
                case 2: part = "leg"; break;
                case 3: part = "foot"; break;
                case 4: part = "hand"; break;
                case 5: part = "neck"; break;
                case 6: part = "head"; break;
                default: System.err.println("Error in injured Member");
            }
            System.out.println("list" + people.getNames());
            String person = people.getNames().get(randomValue(healths.getPeopleAmount()) - 1);
            JOptionPane.showMessageDialog(null, person + " has hurt their " + part +".",
                    "A party member has been injured!", JOptionPane.INFORMATION_MESSAGE);
            healths.addPoints(20, 1);
            System.out.println("you hurt? :(");
        }
    }

    /**
     * Event: Snake Bite - 0.7% chance, a party member gets bitten by a snake.
     */
    private void snakeBite() { // 0.7%
        Health healths = new Health(people, wagon, this);
        if (randomValue(1000) <= 7) {
            String person = people.getNames().get(randomValue(healths.getPeopleAmount())-1);
            JOptionPane.showMessageDialog(null, person + " got bit by a snake.",
                    "A party member has been bit!", JOptionPane.INFORMATION_MESSAGE);
            healths.addPoints(40, 1); // adds 40 points to the person that got bit
            System.out.println("SNAKE");
        }
    }

    /**
     * Event: Lose Trail - 5% chance, go the wrong way and lose some days.
     */
    private void loseTrial() { // Make this higher due to diriy // 2% // set to 5%
        if (randomValue(20) == 1) {
            int rmd = randomValue(5);
            wagon.addDays(rmd);
            System.out.println("JERRY WRONG WAY AGAIN");
            JOptionPane.showMessageDialog(null, "You have gone the wrong way and lost " + rmd +
                    " days.", "Wrong Way!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Find Fruit - 4% chance, gain 20 pounds of food.
     */
    private void fruit() { // 4% & gain 20 pounds of food
        if (randomValue(25) == 1) {
            wagon.changeTotalFood(20);
            System.out.println("BERRIES!!!");
            JOptionPane.showMessageDialog(null, "You have found berries and gained 20 pounds" +
                    " of food. ", "BERRIES!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Wagon Fire - 2% chance, lose supplies due to a fire.
     */
    private void wagonFire() { // 2% & lost supplies
        String lostItems = "";
        if (randomValue(50) == 1) {
            System.out.println("FIRE!");
            int food = randomValue(wagon.getTotalFood());
            wagon.changeTotalFood(-food);
            for (int i = 0; i < randomValue(10); i++) {
                ArrayList<Item> ThiefItems = wagon.getItems();
                int j = randomValue(wagon.getItems().size()) - 1;
                lostItems = lostItems + ThiefItems.get(j).getName() + "\n";
                wagon.removeItemAmount(ThiefItems.get(j).getName(), 1);
            }
            JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + food
                    + " pounds of food!", "Wagon on fire", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Member Lost - 1% chance, a party member gets lost.
     */
    private void memberLost() { // 1% & lose 5 days
        Health healths = new Health(people, wagon, this);
        if (randomValue(100) == 1) {
            wagon.addDays(5);
            String person = people.getNames().get(randomValue(healths.getPeopleAmount())-1);
            JOptionPane.showMessageDialog(null, person +" wondered off and it lost 5 days to" +
                    " find them", "Lost "+ person +"!", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("GONE AGAIN");
        }
    }

    /**
     * Event: Find Wagon - 2% chance, find a deserted wagon with supplies.
     */
    private void findWagon() { // 2% & gained supplies
        String freeItems = "";
        if (randomValue(50) == 1) {
            System.out.println("LOOT");
            int food = randomValue(125);
            wagon.changeTotalFood(food);
            for (int i = 0; i < randomValue(5); i++) {
                ArrayList<Item> LootItems = wagon.getItems();
                int j = randomValue(wagon.getItems().size()) - 1;
                freeItems = freeItems + LootItems.get(j).getName() + "\n";
                wagon.addItemAmount(LootItems.get(j).getName(), 1);
            }
            JOptionPane.showMessageDialog(null, "You have found a deserted wagon it had: "
                    + freeItems + " and " + food  + " pounds of food!", "FREE LOOT!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Ox Wanders - 1% chance, an ox wanders off.
     */
    private void oxWanders() { // 1% & lose 3 days
        if (randomValue(100) == 1) {
            wagon.addDays(3);
            System.out.println("WHERE IT GO");
            JOptionPane.showMessageDialog(null, "An ox wandered off and you lost 3 days to find" 
                    + " it.", "Lost Ox", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Event: Water Contamination - 15% chance, lose food due to contaminated water.
     */
    private void water() { // 15% & lose few pounds of food
        Health healths = new Health(people, wagon, this);
        if (randomValue(17) <= 3) {
            int food = randomValue(20);
            wagon.changeTotalFood(-food);
            JOptionPane.showMessageDialog(null, "Your water got contaminated, you lost " +
                    food + " pounds of food.", "Bad Water", JOptionPane.INFORMATION_MESSAGE);
            healths.addPoints(randomValue(10)+9, healths.getPeopleAmount());
            System.out.println("NEED WATER");
        }
    }

    /**
     * Event: Bad Grass - 20% chance, lose food due to bad grass.
     */
    private void badGrass() { // 20% & lose few pounds of food
        if (randomValue(5) == 1) {
            int rmd = randomValue(10);
            wagon.changeTotalFood(-rmd);
            JOptionPane.showMessageDialog(null, "The oxen got in your food due to little" +
                    " grass, you lost " + rmd + " pounds of food.", "Bad Grass", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("What is this blue grass?");
        }
    }

    /**
     * Check if there's an illness event.
     * @return true if an illness event occurs, false otherwise.
     */
    public boolean isillness() { //0-40% based on health, person & disease random
        Health healths = new Health(people, wagon, this);
        String disease = "";
        int rnd = 0;
        if (healths.healthTotalScore() <= 15)
            return false;
        else if (healths.healthTotalScore() <= 30 && healths.healthTotalScore() > 15)
            rnd = 10;
        else if (healths.healthTotalScore() <= 45 && healths.healthTotalScore() > 30)
            rnd = 9;
        else if (healths.healthTotalScore() <= 60 && healths.healthTotalScore() > 45)
            rnd = 8;
        else if (healths.healthTotalScore() <= 75 && healths.healthTotalScore() > 60)
            rnd = 7;
        else if (healths.healthTotalScore() <= 90 && healths.healthTotalScore() > 75)
            rnd = 6;
        else if (healths.healthTotalScore() <= 105 && healths.healthTotalScore() > 90)
            rnd = 5;
        else if (healths.healthTotalScore() <= 130 && healths.healthTotalScore() > 105)
            rnd = 4;
        else if (healths.healthTotalScore() > 130)
            rnd = 3;

        if (randomValue(rnd) == 1) {
            switch(randomValue(6)) {
                case 1: disease = "Heat exhaustion"; break;
                case 2: disease = "Typhoid"; break;
                case 3: disease = "Cholera"; break;
                case 4: disease = "Measles"; break;
                case 5: disease = "Dysentery"; break;
                case 6: disease = "Mountain fever"; break;
                default: System.err.println("Error in Health illness");
            }
            String person = people.getNames().get(randomValue(healths.getPeopleAmount())-1);
            JOptionPane.showMessageDialog(null, person + "has contracted " + disease + ".",
                    "A party member got an illness!", JOptionPane.INFORMATION_MESSAGE);
            healths.addPoints(20, 1);
            System.out.println("sick");
            return true;
        }
        else
            return false;
    }

    /**
     * Event: Clothing Ripped - Random chance, lose a pair of clothing.
     */
    private void clothingHealth() {
        if (wagon.getMilesTraveled() > 100 && wagon.getMilesTraveled() % 10 >= 6 && randomValue(100) == 1) {
            wagon.removeItemAmount("Clothing", 1);
            JOptionPane.showMessageDialog(null, "You lost one pair of clothing",
                    "Clothing ripped", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * determines if you have successfully scavenged for food
     */
    public void scavenge() {
        int food = randomValue(30) + 20;
    	if (randomValue(10) <= 8) {
    		JOptionPane.showMessageDialog(null, "You successfully scavenged for food! " +
                    "You have gained " + food + " pounds of food!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    		wagon.changeTotalFood(food);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "You did not find any food. Better luck " +
                    "next time...", "FAILED", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
    public void riverLoss(River river) {
    	double loss = river.getLossSeverity();
    	Random rnd = new Random();
    	int index = rnd.nextInt(1, wagon.getItems().size());
    	wagon.removeItemAmount(wagon.getItems().get(index).getName(), 1);
    	wagon.changeTotalFood((int)(-loss * 10));
    	JOptionPane.showMessageDialog(null, "You lost 1 " + wagon.getItems().get(index).getName() + " and " + (int)(loss * 10) + " food in the river!");
    	
    	
    }
}
