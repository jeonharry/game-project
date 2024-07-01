package org.example.demo9.model;

public class CoinSpell implements Spell{
    private int price=400;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void drop() {

    }
}
