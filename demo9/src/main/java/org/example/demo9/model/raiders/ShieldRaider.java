package org.example.demo9.model.raiders;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ShieldRaider extends Raider
{
    public ShieldRaider(int loot, ArrayList<ArrayList<Double>> road) {
        super(70, loot, 20, road,new ImageView());
    }
}
