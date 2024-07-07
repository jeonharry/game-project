package org.example.demo9.model.spells;

import org.example.demo9.controller.Controller;
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
        int hearts=Integer.parseInt(Controller.getController().getHearts().getText())+5;
        if(hearts<20)
            Controller.getController().getHearts().setText(String.valueOf(hearts));
        else
            Controller.getController().getHearts().setText(String.valueOf(20));
    }

    public static String getInfo() {
        return info;
    }
}
