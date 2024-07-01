package org.example.demo9.model.raiders;

import java.util.ArrayList;

public abstract class Raider
{
    private int health;
    private int loot;
    private int speed;
    private ArrayList <ArrayList<Double>> road;
    public Raider(int health,int loot,int speed, ArrayList <ArrayList <Double>> road)
    {
        this.health=health;
        this.loot=loot;
        this.speed=speed;
        this.road=road;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<ArrayList<Double>> getRoad() {
        return road;
    }

    public void setRoad(ArrayList<ArrayList<Double>> road) {
        this.road = road;
    }
}
