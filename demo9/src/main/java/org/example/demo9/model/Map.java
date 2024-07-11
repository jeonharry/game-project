package org.example.demo9.model;

import javafx.scene.image.Image;
import org.example.demo9.model.raiders.Raider;
import org.example.demo9.model.towers.Tower;

import java.util.ArrayList;

public class Map
{
    private final int coins;
    private final ArrayList <Double> towerPlaces;
    private final ArrayList<ArrayList <ArrayList<Double>>> heroPlaces;
    private final ArrayList <Double> endPoint;
    private ArrayList <Tower> towers=new ArrayList<>();
    private ArrayList <Raider> raidersInMap=new ArrayList<>();
    private final ArrayList <Double> startButton;
    private final ArrayList <Direction> directions;
    private final int attackWaves;
    private final Image image;
    public Map(int coins, ArrayList <Double> towerPlaces,ArrayList <ArrayList <ArrayList<Double>>> heroPlaces, ArrayList <Double> endPoint,ArrayList <Direction> directions ,Image image,ArrayList <Double> startButton,int attackWaves)
    {
        this.coins=coins;
        this.towerPlaces = towerPlaces;
        this.heroPlaces = heroPlaces;
        this.endPoint = endPoint;
        this.attackWaves = attackWaves;
        this.directions=directions;
        this.image=image;
        this.startButton=startButton;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<Double> getTowerPlaces() {
        return towerPlaces;
    }

    public ArrayList <ArrayList<ArrayList<Double>>> getHeroPlaces() {
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

    public ArrayList<Raider> getRaidersInMap() {
        return raidersInMap;
    }

    public void setRaidersInMap(ArrayList<Raider> raidersInMap) {
        this.raidersInMap = raidersInMap;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Double> getStartButton() {
        return startButton;
    }
}
