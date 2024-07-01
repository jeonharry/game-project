package org.example.demo9.model.towers;

public abstract class Tower
{
    private int damage;
    private int price;
    private double domain;
    public Tower(int damage,int price,double domain)
    {
        this.damage=damage;
        this.domain=domain;
        this.price=price;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDomain() {
        return domain;
    }

    public void setDomain(double domain) {
        this.domain = domain;
    }
}
