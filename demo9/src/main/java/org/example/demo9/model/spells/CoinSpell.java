package org.example.demo9.model.spells;

import org.example.demo9.controller.Controller;
import org.example.demo9.model.spells.Spell;

public class CoinSpell implements Spell {
    private static int price=400;
    private static String info="LEAVE NOTHING TO CHANCE, BRING \nEXTRA GOLD TO THE BATTLEFIELD!GOLD \nBAG EACH BAG iS WORTH 500 GOLD PIECES.";


    public static int getPrice() {
        return price;
    }

    @Override
    public void drop() {
        Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+200));
    }

    public static String getInfo() {
        return info;
    }
}
