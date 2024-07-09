package org.example.demo9.model.towers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;
import org.example.demo9.model.raiders.Raider;

import java.io.IOException;
import java.util.ArrayList;

public class DefendTower extends Tower {
    private boolean isStopping=false;
    private ArrayList <Raider> onAttackings=new ArrayList<>();
    public DefendTower(double domain) throws IOException {
        super(0, 70, 90,domain,"pics/def1.png");
        ArrayList<Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/def1.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
        this.setAttackingDevice(new ImageView());
    }

    public boolean isStopping() {
        return isStopping;
    }

    public void setStopping(boolean stopping) {
        isStopping = stopping;
    }

    public ArrayList<Raider> getOnAttackings() {
        return onAttackings;
    }

    public void setOnAttackings(ArrayList<Raider> onAttackings) {
        this.onAttackings = onAttackings;
    }
}
