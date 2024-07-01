package org.example.demo9.model;

public class FreezeSpell implements Spell{
    private int price=350;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void drop() {

    }
}
