package org.example.demo9.model.raiders;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.demo9.Main;

import java.util.ArrayList;

public class FastRaider extends Raider {
    public FastRaider(int health,int loot, ArrayList<ArrayList<Double>> road) {
        super(health, loot, 30, 2700,road,new ImageView(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_000.png").toExternalForm())));
        ArrayList <Image> images=new ArrayList<>();
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_000.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_001.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_002.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_003.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_004.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_005.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_006.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_007.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_008.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_009.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_010.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_011.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_012.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_013.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_014.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_015.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_016.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_017.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_018.png").toExternalForm()));
        images.add(new Image(Main.class.getResource("pics/fast/1_enemies_1_walk_019.png").toExternalForm()));
        this.setImagesForAnimation(images);
    }
}
