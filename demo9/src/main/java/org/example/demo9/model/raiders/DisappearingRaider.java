package org.example.demo9.model.raiders;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class DisappearingRaider extends Raider {
    public DisappearingRaider(int loot, ArrayList<ArrayList<Double>> road) {
        super(35, loot, 30, road,new ImageView());
    }
}
