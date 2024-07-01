package org.example.demo9.model;

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
