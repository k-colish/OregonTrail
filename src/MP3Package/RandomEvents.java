package MP3Package;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEvents {
    Random rnd = new Random();
    private final ArrayList<String> allItems = new ArrayList<String>();
    Wagon wagonlist;
    int wagonItems;

    public RandomEvents(Wagon wagon)
    {
        wagonlist = wagon;
        allItemsCSV();
    }

    public void allItemsCSV()
    {
        try {
            List<String> lines = Files.readAllLines(Paths.get("/csv/AllItems.csv"));
            String itemName = "";
            System.out.println("Item: " + itemName);
            allItems.add(itemName);
        }
        catch (IOException e) {
        System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public void allEvents()
    {
        switch(randomValue(18)) {
            case 1: Thief(); break;
            case 2: indiansHelp(); break;
            case 3: servereThunderstorm(); break;
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
            case 1: servereThunderstorm(); break;
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
        System.out.println("randomValue");
        return rmd;
    }


    private void Thief() //2% & lots of supplies lost, does not currently work
    {
        ArrayList<Item> ThiefItems = wagonlist.getItems();
        String lostItems = "";
        if (1 == randomValue(50))
        {
            System.out.println("Thief");
            int food = randomValue(125);
            wagonlist.changeTotalFood(food);
            for (int i = 0; i < randomValue(5); i++)
            {
                int j = randomValue(wagonlist.getItems().size());
                lostItems = lostItems + ThiefItems.get(j) + "\n";
            }
            JOptionPane.showMessageDialog(null, "You have lost: " + lostItems + "and" + food  + "pounds of food!", "Thief came during the night", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void indiansHelp() // 5% chance to get 30 pounds of food
    {

        if (randomValue(20) == 1) {
            wagonlist.changeTotalFood(30);
            System.out.println("indians");
        }
    }

    private void servereThunderstorm() // 15%
    {
        if (randomValue(17) <= 3) {
            wagonlist.addDays(1);
            System.out.println("thunderstorm");
        }
    }

    private void severeBlizzard() // 15%
    {
        if (randomValue(17) <= 3){
            System.out.println("blizzard");
            wagonlist.addDays(1);
        }
    }

    private void heavyFog() // 6% after fort Hall & 50% lose a day
    {
        if (randomValue(2) == 1 ) {
            System.out.println("FOG");
            wagonlist.addDays(1);
        }
    }

    private void hailStorm() // 6% before fort Hall & 50% lose a day
    {

        if (randomValue(2) == 1 ) {
            System.out.println("hail");
            wagonlist.addDays(1);
        }
    }

    private void deadOX() // 2.5%
    {

        if (randomValue(40) == 1)
        {
            System.out.println("RIP OX");
        }
            //add remove item method
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
            wagonlist.addDays(randomValue(5));
            System.out.println("JERRY WRONG WAY AGAIN");
        }
    }

    private void fruit() // 4% & 20 pounds
    {

        if (randomValue(25) == 1) {
            wagonlist.changeTotalFood(20);
            System.out.println("BERRIES!!!");
        }
    }

    private void wagonFire() // 2% & lost supplies
    {

        if (randomValue(50) == 1)
        {
            // add remove item method
            System.out.println("FIRE");
        }
    }

    private void memberLost() // 1% & lose 5 days
    {

        if (randomValue(25) == 1) {
            wagonlist.addDays(5);
            System.out.println("GONE AGAIN");
        }
    }

    private void findWagon() // 2% & gained supplies
    {

        if (randomValue(25) == 1)
        {
            System.out.println("LOOT");
            for (int i = 0; i <= randomValue(5); i++)
            {
                //wagonlist.addItem(); // add random item thing
            }
        }
    }

    private void oxWanders() // 1% & lose 3 days
    {

        if (randomValue(25) == 1) {
            wagonlist.addDays(3);
            System.out.println("WHERE IT GO");
        }
    }

    private void water() // 15% & lose few pounds of food
    {
        if (randomValue(17) <= 3) {
            wagonlist.changeTotalFood(-randomValue(20));
            System.out.println("NEED WATER");
        }
    }

    private void badGrass() // 20%
    {

        if (randomValue(20) == 1) {
            wagonlist.changeTotalFood(-randomValue(10));
            System.out.println("What is this blue grass?");
        }
    }

    private void illness() //0-40% based on health, person & disease random
    {
        System.out.println("sick");
    }
}
