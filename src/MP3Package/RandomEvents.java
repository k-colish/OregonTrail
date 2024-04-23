package MP3Package;

import javax.swing.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {
    Random rnd = new Random();
    private final ArrayList<String> allItems = new ArrayList<String>();
    Wagon wagonlist;

    public RandomEvents(Wagon wagon)
    {
        wagonlist = wagon;
        allItemsCSV();
    }

    public void allItemsCSV()
    {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(this.getClass().getResourceAsStream("/csv/AllItems.csv"));
            System.out.println("Item: " + reader);
            allItems.add(String.valueOf(reader));
        }
        catch(Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public void allEvents()
    {
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
            case 18: illness(); break;
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
        String lostItems = "";
        if (randomValue(50) == 1)
        {
            System.out.println("Thief");
            int food = randomValue(125);
            wagonlist.changeTotalFood(-food);
            for (int i = 0; i < randomValue(5); i++)
            {
                ArrayList<Item> ThiefItems = wagonlist.getItems();
                int j = randomValue(wagonlist.getItems().size());
                lostItems = lostItems + ThiefItems.get(j) + "\n";
                wagonlist.removeItemAmount(ThiefItems.get(j), 1);
            }
            JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + food
                    + " pounds of food!", "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);
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

    private void deadOX() // 2.5%
    {
        if (randomValue(40) == 1)
        {
            System.out.println("RIP OX");
            JOptionPane.showMessageDialog(null, "One of your oxen has died.", "Oxen died",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void injuredMember() // 2.5%
    {
        if (randomValue(40) == 1)
        {
            //add sick/injured method
            System.out.println("you hurt? :(");
        }
    }

    private void snakeBite() // 0.7%
    {
        if (randomValue(1000) <= 7)
        {
            //add sick/injured method
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
        if (randomValue(50) == 1)
        {
            System.out.println("FIRE!");
            int food = randomValue(250);
            wagonlist.changeTotalFood(-food);
            for (int i = 0; i < randomValue(10); i++)
            {
                ArrayList<Item> ThiefItems = wagonlist.getItems();
                int j = randomValue(wagonlist.getItems().size());
                lostItems = lostItems + ThiefItems.get(j) + "\n";
                wagonlist.removeItemAmount(ThiefItems.get(j), 1);
            }
            JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + " and " + food
                    + " pounds of food!", "Wagon on fire", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void memberLost() // 1% & lose 5 days
    {
        if (randomValue(25) == 1) {
            wagonlist.addDays(5);
            JOptionPane.showMessageDialog(null, "NAME! wondered off", "Lost + NAME!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("GONE AGAIN");
        }
    }

    private void findWagon() // 2% & gained supplies
    {
        String freeItems = "";
        if (randomValue(25) == 1)
        {
            System.out.println("LOOT");
            int food = randomValue(125);
            wagonlist.changeTotalFood(food);
            for (int i = 0; i < randomValue(5); i++)
            {
                ArrayList<Item> LootItems = wagonlist.getItems();
                int j = randomValue(wagonlist.getItems().size());
                freeItems = freeItems + LootItems.get(j) + "\n";
                wagonlist.addItemAmount(LootItems.get(j), 1);
            }
            JOptionPane.showMessageDialog(null, "You have found a deserted wagon it had: "
                    + freeItems + " and " + food  + " pounds of food!", "FREE LOOT!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void oxWanders() // 1% & lose 3 days
    {
        if (randomValue(25) == 1) {
            wagonlist.addDays(3);
            System.out.println("WHERE IT GO");
            JOptionPane.showMessageDialog(null, "An ox wandered off and lost 3 days to find " +
                    "it.", "Lost Ox", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void water() // 15% & lose few pounds of food
    {
        if (randomValue(17) <= 3) {
            int rmd = randomValue(20);
            wagonlist.changeTotalFood(-rmd);
            JOptionPane.showMessageDialog(null, "Your water got contaminated, you lost " +
                    rmd + " pounds of food.", "Bad Water", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("NEED WATER");
        }
    }

    private void badGrass() // 20% & lose few pounds of food
    {
        if (randomValue(20) == 1) {
            int rmd = randomValue(10);
            wagonlist.changeTotalFood(-rmd);
            JOptionPane.showMessageDialog(null, "The oxen got in your food due to little" +
                    " grass, you lost " + rmd + " pounds of food.", "Bad Grass", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("What is this blue grass?");
        }
    }

    private void illness() //0-40% based on health, person & disease random
    {
        System.out.println("sick");
    }
}
