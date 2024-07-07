package org.example.demo9.model.towers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;
import org.example.demo9.model.towers.Tower;

import java.io.IOException;
import java.util.ArrayList;

public class Artillery extends Tower
{
    public Artillery(double domain) throws IOException {
        super(15, 125,100,domain,"pics/bomb1.png");
        ArrayList<Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb1.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb2.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb3.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb4.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb5.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/bomb6.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
        this.setArrow(new ImageView(new Image(Main.class.getResource("pics/Clipped_image_20240707_174445.png").toExternalForm())));
    }
}
