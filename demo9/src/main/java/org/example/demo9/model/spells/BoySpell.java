package org.example.demo9.model.spells;

import org.example.demo9.model.spells.Spell;

public class BoySpell implements Spell {
    private static int price=500;
    private static String info="YOU ONLY NEED ONE OF THESE TO CLEAR \nTHE BATTLEFIELD AND CLAIM THE SPOILS \nOF WAR! JUST TELL US WHERE TO DROP IT!";

    public static int getPrice() {
        return price;
    }

    @Override
    public void drop() {

    }

    public static String getInfo() {
        return info;
    }
}
