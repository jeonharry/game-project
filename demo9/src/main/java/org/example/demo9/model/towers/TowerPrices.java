package org.example.demo9.model.towers;

public enum TowerPrices
{
    ARCHER(70),DEF(70),BOMB(125),WIZARD(100);
    private int price;
    TowerPrices(int price)
    {
        this.price=price;
    }

    public int getPrice() {
        return price;
    }
}
