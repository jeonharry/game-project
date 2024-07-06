package org.example.demo9.model.spells;

import org.example.demo9.model.spells.Spell;

public class FreezeSpell implements Spell {
    private static int price=350;
    private static String info="THIS SPECIAL CONCOCTION WILL FREEZE \nYOUR ENEMIES HELPLESS IN PLACE FOR \nA WHILE.";

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
