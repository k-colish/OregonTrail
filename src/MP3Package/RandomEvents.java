package MP3Package;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for generating random events durring the game.
 */
public class RandomEvents {
    Random rnd = new Random();
    Wagon wagonlist;

    /**
     * Constructor for RandomEvents class.
     * @param wagon The wagon object for which events are generated.
     */
    public RandomEvents(Wagon wagon)
    {
        wagonlist = wagon;
    }

    /**
     *
     */
    public void allEvents()
    {
        switch(randomValue(17)) {
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
            default: System.err.println("Error in allEvents");
        }
    }

    public void restEvents() //used for the player decides to rest for the day
    {
        switch(randomValue(8)) {
            case 1: Thief(); break;
            case 2: indiansHelp(); break;
            case 3: injuredMember(); break;
            case 4: snakeBite(); break;
            case 5: fruit(); break;
            case 6: wagonFire(); break;
            case 7: memberLost(); break;
            case 8: oxWanders(); break;
            default: System.err.println("Error in restEvents");
        }
    }

    public void environmentEvents()
    {
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

    private int randomValue(int x) {
        int rmd = rnd.nextInt(x) + 1;
        System.out.println("randomValue" + rmd);
        return rmd;
    }


    private void Thief() //2% & lots of supplies lost, does not currently work
    {
    	int food = 0;
        String lostItems = "";
        if (randomValue(50) == 1) {
            System.out.println("Thief");
            if(wagonlist.getTotalFood() > 0) {
            	food = randomValue(wagonlist.getTotalFood());
            }
            wagonlist.changeTotalFood(-food);
            for (int i = 0; i < randomValue(5); i++)
            {
                ArrayList<Item> ThiefItems = wagonlist.getItems();
                int j = randomValue(ThiefItems.size());
                lostItems += ThiefItems.get(j).getName();
                wagonlist.removeItemAmount(ThiefItems.get(j).getName(), 1);
            }
            if(wagonlist.getTotalFood() > 0) {
            	JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + 
                        food + " pounds of food!", "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);
            }
            else JOptionPane.showMessageDialog(null, "You have lost: " + lostItems,
                    "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void indiansHelp() // 5% chance to get 30 pounds of food
    {
        if (randomValue(20) == 1) {
            wagonlist.changeTotalFood(30);
            JOptionPane.showMessageDialog(null, "You have gained 30 pounds of food!",
                    "Indians have come to help", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("indians");
        }
    }

    private void severeThunderstorm() // 15%
    {
        if (randomValue(17) <= 3) {
            wagonlist.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a thunderstorm and" +
                            " lost one day.","Severe Thunderstorm", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("thunderstorm");
        }
    }

    private void severeBlizzard() // 15%
    {
        if (randomValue(17) <= 3){
            wagonlist.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a blizzard and lost one day.",
                    "Severe Blizzard", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("blizzard");
        }
    }

    private void heavyFog() // 6% after fort Hall & 50% lose a day
    {
        if (randomValue(2) == 1 ) {
            System.out.println("FOG");
            wagonlist.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in heavy fog and lost one day.",
                    "Heavy Fog", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void hailStorm() // 6% before fort Hall & 50% lose a day
    {
        if (randomValue(2) == 1 ) {
            System.out.println("hail");
            wagonlist.addDays(1);
            JOptionPane.showMessageDialog(null, "You got stuck in a hail storm and lost one " +
                            "day.", "Hail Storm", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deadOX() // 2.5% //Need to add to remove an OX
    {
        if (randomValue(40) == 1) {
            System.out.println("RIP OX");
            JOptionPane.showMessageDialog(null, "One of your oxen has died.", "Oxen died",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void injuredMember() // 2.5% // 20 points
    {
        People names = new People();
        Health score = new Health(names, wagonlist, this);
        if (randomValue(40) == 1) {
            score.addPoints(20, 1);
            System.out.println("you hurt? :(");
        }
    }

    private void snakeBite() // 0.7%
    {
        People names = new People();
        Health score = new Health(names, wagonlist, this);
        if (randomValue(1000) <= 7) {
            score.addPoints(40, 1); // adds 40 points to the person that got bit
            System.out.println("SNAKE");
        }
    }

    private void loseTrial() // Make this higher due to diriy // 2% // set to 5%
    {
        if (randomValue(20) == 1) {
            int rmd = randomValue(5);
            wagonlist.addDays(rmd);
            System.out.println("JERRY WRONG WAY AGAIN");
            JOptionPane.showMessageDialog(null, "You have gone the wrong way and lost " + rmd +
                    " days.", "Wrong Way!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void fruit() // 4% & 20 pounds
    {
        if (randomValue(25) == 1) {
            wagonlist.changeTotalFood(20);
            System.out.println("BERRIES!!!");
            JOptionPane.showMessageDialog(null, "You have found berries and gained 20 pounds" +
                    " of food. ", "BERRIES!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void wagonFire() // 2% & lost supplies
    {
        String lostItems = "";
        if (randomValue(50) == 1) {
            System.out.println("FIRE!");
            int food = randomValue(250);
            wagonlist.changeTotalFood(-food);
            for (int i = 0; i < randomValue(10); i++)
            {
                ArrayList<Item> ThiefItems = wagonlist.getItems();
                int j = randomValue(wagonlist.getItems().size());
                lostItems = lostItems + ThiefItems.get(j) + "\n";
                wagonlist.removeItemAmount(ThiefItems.get(j).getName(), 1);
            }
            JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + food
                    + " pounds of food!", "Wagon on fire", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void memberLost() // 1% & lose 5 days
    {
        if (randomValue(100) == 1) {
            wagonlist.addDays(5);
            JOptionPane.showMessageDialog(null, "NAME! wondered off", "Lost + NAME!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("GONE AGAIN");
        }
    }

    private void findWagon() // 2% & gained supplies
    {
        String freeItems = "";
        if (randomValue(50) == 1) {
            System.out.println("LOOT");
            int food = randomValue(125);
            wagonlist.changeTotalFood(food);
            for (int i = 0; i < randomValue(5); i++)
            {
                ArrayList<Item> LootItems = wagonlist.getItems();
                int j = randomValue(wagonlist.getItems().size());
                freeItems = freeItems + LootItems.get(j).getName() + "\n";
                wagonlist.addItemAmount(LootItems.get(j).getName(), 1);
            }
            JOptionPane.showMessageDialog(null, "You have found a deserted wagon it had: "
                    + freeItems + " and " + food  + " pounds of food!", "FREE LOOT!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void oxWanders() // 1% & lose 3 days
    {
        if (randomValue(100) == 1) {
            wagonlist.addDays(3);
            System.out.println("WHERE IT GO");
            JOptionPane.showMessageDialog(null, "An ox wandered off and lost 3 days to find " +
                    "it.", "Lost Ox", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void water() // 15% & lose few pounds of food
            //NEEDED - adds 10-20 to each person
    {
        People names = new People();
        Health score = new Health(names, wagonlist, this);
        if (randomValue(17) <= 3) {
            int rmd = randomValue(20);
            wagonlist.changeTotalFood(-rmd);
            JOptionPane.showMessageDialog(null, "Your water got contaminated, you lost " +
                    rmd + " pounds of food.", "Bad Water", JOptionPane.INFORMATION_MESSAGE);
            score.addPoints(randomValue(10)+9, score.getPeopleAmount());
            System.out.println("NEED WATER");
        }
    }

    private void badGrass() // 20% & lose few pounds of food
    {
        if (randomValue(5) == 1) {
            int rmd = randomValue(10);
            wagonlist.changeTotalFood(-rmd);
            JOptionPane.showMessageDialog(null, "The oxen got in your food due to little" +
                    " grass, you lost " + rmd + " pounds of food.", "Bad Grass", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("What is this blue grass?");
        }
    }

    public boolean illness() //0-40% based on health, person & disease random
            //NEEDED - adds 20 to that person
    {
        People names = new People();
        Health score = new Health(names, wagonlist, this);
        String disease = "";
        String name = "";
        int rnd = 0;
        if (score.healthScores() <= 15)
            rnd = 10;
        else if (score.healthScores() <= 30 && score.healthScores() > 15)
            rnd = 9;
        else if (score.healthScores() <= 45 && score.healthScores() > 30)
            rnd = 8;
        else if (score.healthScores() <= 60 && score.healthScores() > 45)
            rnd = 7;
        else if (score.healthScores() <= 75 && score.healthScores() > 60)
            rnd = 6;
        else if (score.healthScores() <= 90 && score.healthScores() > 75)
            rnd = 5;
        else if (score.healthScores() <= 105 && score.healthScores() > 90)
            rnd = 4;
        else if (score.healthScores() <= 130 && score.healthScores() > 105)
            rnd = 3;
        else if (score.healthScores() > 130)
            rnd = 2;

        if (randomValue(rnd) == 1)
        {
            switch(randomValue(6)) {
                case 1: disease = "Heat exhaustion"; break;
                case 2: disease = "Typhoid"; break;
                case 3: disease = "Cholera"; break;
                case 4: disease = "Measles"; break;
                case 5: disease = "Dysentery"; break;
                case 6: disease = "Mountain fever"; break;
                default: System.err.println("Error in Health illness");
            }
            //and name randomizer
            JOptionPane.showMessageDialog(null, name + "has contracted " + disease,
                    "Got an illness", JOptionPane.INFORMATION_MESSAGE);
            score.addPoints(20, 1);
            System.out.println("sick");
            return true;
        }
        else
            return false;
    }
    /**
     * determines if you have successfully scavenged for food
     */
    public int scavenge() {
        int food = randomValue(60)+19;
    	if (randomValue(10) <= 7) {
    		JOptionPane.showMessageDialog(null, "You successfully scavenged for food! " +
                    "You have gained " + food + " pounds of food!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    		wagonlist.changeTotalFood(food);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "You did not find any food. Better luck " +
                    "next time...", "FAILED", JOptionPane.INFORMATION_MESSAGE);
    	}
    	return food;
    }
}
