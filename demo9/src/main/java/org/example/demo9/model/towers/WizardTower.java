package org.example.demo9.model.towers;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;
import org.example.demo9.model.raiders.Raider;

import java.io.IOException;
import java.util.ArrayList;

public class WizardTower extends Tower
{
    private Raider attacking=null;
    public WizardTower( double domain,int damage) throws IOException {
        super(damage, 100, 110,domain,"pics/wizard1.png");
        ArrayList<Image> imagesForAnimate=new ArrayList<>();
        imagesForAnimate.add(new Image(Main.class.getResource("pics/wizard1.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/wizard2.png").toExternalForm()));
        this.setImagesForAnimate(imagesForAnimate);
        this.setAttackingDevice(new ImageView(new Image(Main.class.getResource("pics/Clipped_image_20240707_172920.png").toExternalForm())));
    }

    public Raider getAttacking() {
        return attacking;
    }

    public void setAttacking(Raider attacking) {
        this.attacking = attacking;
    }
}
