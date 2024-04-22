package MP3Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEvents {
    Random rnd = new Random();
    private final ArrayList<String> allItems = new ArrayList<String>();
    Wagon wagonlist = null;
    int wagonItems;

    public RandomEvents(Wagon wagon)
    {
        wagonlist = wagon;
        allItemsCSV();
        ArrayList<Item> wagonItems = wagonlist.getItems();
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
        switch(rnd.nextInt(18) + 1) {
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
            default:
                //LOL
        }
    }

    private int randomValue(int x) {
        int rmd = rnd.nextInt(x) + 1;
        System.out.println("randomValue");
        return rmd;
    }


    private void Thief() //2% & lots of supplies lost
    {
        int food;

        if (1 == randomValue(50))
        {
            System.out.println("Thief");
            food = rnd.nextInt(125) + 1;
            //String wagonItem = wagonlist.get(randomValue(wagonItems));
           // if (wagonItem == allItems.get(randomValue(allItems.size())))
            {
                // add remove item method
            }
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

        int rmd = randomValue(17);
        if (rmd == 1 || rmd == 2 || rmd == 3) {
            wagonlist.addDays(1);
            System.out.println("thunderstorm");
        }
    }

    private void severeBlizzard() // 15%
    {
        int rmd = randomValue(17);
        if (rmd == 1 || rmd == 2 || rmd == 3){
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

        int rmd = randomValue(1000);
        if (rmd == 1 || rmd == 2 || rmd == 3 || rmd == 4 || rmd == 5 || rmd == 6 || rmd == 7)
        {
            //add sick/injured method
            System.out.println("SNAKE");
        }
    }

    private void loseTrial() // Make this higher due to diriy // 2%
    {

        if (randomValue(10) == 1) {
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
        int rmd = randomValue(17);
        if (rmd == 1 || rmd == 2 || rmd == 3) {
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
