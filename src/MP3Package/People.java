package MP3Package;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class for managing the list of people in the game.
 */
public class People {

    private ArrayList<String> names = new ArrayList<>();

    

    /**
     * Method to set names for the people in the game.
     * Prompts the user to enter names for each person.
     */
    public void setNames() {
        for (int i = 1; i < 5; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter character " + i + "'s name: ", "Enter Your Party's Names!", JOptionPane.INFORMATION_MESSAGE);
            names.add(i-1, name);
            System.out.println("listPEOPLE1: " + names.get(i-1));
            System.out.println("listPEOPLE2: " + names.size());
        }
    }

    /**
     * Get the list of names of people in the game.
     * @return The list of names.
     */
    public ArrayList<String> getNames() {
        System.out.println("listPEOPLE3: " + names.size());
        return names;
    }

    /**
     * Remove a name from the list of people.
     * @param name The name to be removed.
     */
    public void removeName(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                names.remove(i); break;
            }
        }
    }
}