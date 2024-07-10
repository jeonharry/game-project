package org.example.demo9.model.raiders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;

import java.util.ArrayList;

public class FlierRaider extends Raider {
    public FlierRaider(int health,int loot, ArrayList<ArrayList<Double>> road) {
        super(health, loot, 35, 2000,road,new ImageView(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_000.png").toExternalForm())));
        ArrayList <Image> images=new ArrayList<>();
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_000.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_001.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_002.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_003.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_004.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_005.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_006.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_007.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_008.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_009.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_010.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_011.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_012.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_013.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_014.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_015.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_016.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/Fly/0_Monster_Fly_017.png").toExternalForm()));
        this.setImagesForAnimation(images);
    }
}
