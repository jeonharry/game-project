package org.example.demo9.model;

import javafx.scene.Node;
import org.example.demo9.model.towers.Tower;

import java.util.ArrayList;

public class Map
{
    private final int coins;
    private final ArrayList <Double> towerPlaces;
    private final ArrayList <Node> heroPlaces;
    private final ArrayList <Double> endPoint;
    private ArrayList <Tower> towers=new ArrayList<>();
    private final int attackWaves;
    public Map(int coins, ArrayList <Double> towerPlaces, ArrayList <Node> heroPlaces, ArrayList <Double> endPoint, int attackWaves)
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

    public ArrayList<Double> getTowerPlaces() {
        return towerPlaces;
    }

    public ArrayList<Node> getHeroPlaces() {
        return heroPlaces;
    }

    public ArrayList<Double> getEndPoint() {
        return endPoint;
    }

    public int getAttackWaves() {
        return attackWaves;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }
}
