package org.example.demo9.model.raiders;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class FlierRaider extends Raider {
    public FlierRaider(int loot, ArrayList<ArrayList<Double>> road) {
        super(45, loot, 35, road,new ImageView());
    }
}
