package org.example.demo9.model.spells;

import org.example.demo9.model.spells.Spell;

public class HealSpell implements Spell
{
    private static int price=300;
    private static String info="MAGICAL HEARTS TRAPPED IN A B0X.\nBREAK GLASS ON AN EMERGENCY TO \nGAIN 5 EXTRA LIVES.";

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
