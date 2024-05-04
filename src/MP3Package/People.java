package MP3Package;

import javax.swing.*;
import java.util.ArrayList;

public class People {

    private ArrayList<String> names = new ArrayList<>();

    People()
    {
        for (int j = 0; j < 4; j++) {names.add(j, ""+j);}
    }

    public void setNames() {
        for (int i = 1; i < 5; i++) {
            String name = JOptionPane.showInputDialog(null, "Please enter name " + i);
            names.add(i-1, name);
            System.out.println("listPEOPLE1: " + names.get(i-1));
            System.out.println("listPEOPLE2: " + names.size());
        }
    }

    public ArrayList<String> getNames() {
        System.out.println("listPEOPLE3: " + names.size());
        return names;
    }

    public void removeName(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                names.remove(i); break;
            }
        }
    }
}