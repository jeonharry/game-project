package org.example.demo9.model;

import javafx.scene.Node;

import java.util.ArrayList;

public class Map
{
    private final int coins;
    private final ArrayList <Node> towerPlaces;
    private final ArrayList <Node> heroPlaces;
    private final ArrayList <ArrayList <Double>> endPoint;
    private final int attackWaves;
    public Map(int coins, ArrayList <Node> towerPlaces, ArrayList <Node> heroPlaces, ArrayList <ArrayList<Double>> endPoint, int attackWaves)
    {
        this.coins=coins;
        this.towerPlaces = towerPlaces;
        this.heroPlaces = heroPlaces;
        this.endPoint = endPoint;
        this.attackWaves = attackWaves;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<Node> getTowerPlaces() {
        return towerPlaces;
    }

    public ArrayList<Node> getHeroPlaces() {
        return heroPlaces;
    }

    public ArrayList<ArrayList<Double>> getEndPoint() {
        return endPoint;
    }

    public int getAttackWaves() {
        return attackWaves;
    }
}
