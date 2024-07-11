package org.example.demo9.model.towers;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import org.example.demo9.Main;
import org.example.demo9.model.raiders.Raider;

import java.io.IOException;
import java.util.ArrayList;

public class ArcherTower extends Tower
{
    private Raider attacking=null;
    public ArcherTower(double domain,int damage) throws IOException {
        super(damage,70 ,90,domain,"pics/archer1.png");
        ArrayList <Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer1.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer2.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer3.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer4.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
        this.setAttackingDevice(new ImageView(new Image(Main.class.getResource("pics/Clipped_image_20240707_175041.png").toExternalForm())));
        Rotate rotate=new Rotate();
        rotate.setAngle(45);
        this.getAttackingDevice().getTransforms().add(rotate);
    }

    public Raider getAttacking() {
        return attacking;
    }

    public void setAttacking(Raider attacking) {
        this.attacking = attacking;
    }
}
