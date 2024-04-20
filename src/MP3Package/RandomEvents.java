package MP3Package;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {
    Random rnd = new Random();

    public RandomEvents()
    {

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

    private void Thief() //2% & lots of supplies lost
    {

    }

    private void indiansHelp() // 5% chance to get 30 pounds of food
    {

    }

    private void servereThunderstorm() // 15%
    {

    }

    private void severeBlizzard() // 15%
    {

    }

    private void heavyFog() // 6% & lose a day
    {

    }

    private void hailStorm() // 6%
    {

    }

    private void deadOX() // 2.5%
    {

    }

    private void injuredMember() // 2.5%
    {

    }

    private void snakeBite() // 0.7%
    {

    }

    private void loseTrial() // Make this higher due to diriy //2%
    {

    }

    private void fruit() // 4% & 20 pounds
    {

    }

    private void wagonFire() // 2% & lost supplies
    {

    }

    private void memberLost() // 1% & lose 5 days
    {

    }

    private void findWagon() // 2% & gained supplies
    {

    }

    private void oxWanders() // 1% & lose 3 days
    {

    }

    private void water() // 15% & lose few pounds of food
    {

    }

    private void badGrass() // 20%
    {

    }

    private void illness() //0-40% based on health, person & disease random
    {

    }

}
