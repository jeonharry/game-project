package org.example.demo9.model.spells;

import org.example.demo9.model.spells.Spell;

public class HealSpell implements Spell
{
    private int price=300;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void drop() {

    }
}
