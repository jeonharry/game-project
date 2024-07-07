package org.example.demo9.model.towers;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;

import java.io.IOException;
import java.util.ArrayList;

public class ArcherTower extends Tower
{
    public ArcherTower(double domain) throws IOException {
        super(20,70 ,90,domain,"pics/archer1.png");
        ArrayList <Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer1.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer2.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer3.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer4.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
        this.setArrow(new ImageView(new Image(Main.class.getResource("pics/Clipped_image_20240707_175041.png").toExternalForm())));
    }
}
