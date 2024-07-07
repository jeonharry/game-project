package org.example.demo9.model.towers;

import javafx.scene.image.Image;
import org.example.demo9.Main;

import java.io.IOException;
import java.util.ArrayList;

public class DefendTower extends Tower {
    public DefendTower(double domain) throws IOException {
        super(5, 70, 90,domain,"pics/def1.png");
        ArrayList<Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/def1.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
    }
}
