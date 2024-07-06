package org.example.demo9.model.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.demo9.Main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Tower
{
    private int damage;
    private int price;
    private double domain;
    private ImageView tower=new ImageView(new Image(Main.class.getResource("pics/archer1.png").toExternalForm()));
    private ArrayList <Image> imagesForAnimate=new ArrayList<>();
    private boolean animate=true;
    public Tower(int damage,int price,double domain)
    {
        tower.setFitWidth(65); tower.setFitHeight(50); tower.setPreserveRatio(true);
        this.damage=damage;
        this.domain=domain;
        this.price=price;
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer1.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer2.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer3.png").toExternalForm()));
        imagesForAnimate.add(new Image(Main.class.getResource("pics/archer4.png").toExternalForm()));
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDomain() {
        return domain;
    }

    public void setDomain(double domain) {
        this.domain = domain;
    }

    public ImageView getTower() {
        return tower;
    }

    public void setTower(ImageView tower) {
        this.tower = tower;
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public void animation()
    {
        Timeline timeline=new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),event -> {
            if(animate)
                tower.setImage(imagesForAnimate.getFirst());
            else
                tower.setImage(imagesForAnimate.getFirst());
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),event -> {
            if(animate)
                tower.setImage(imagesForAnimate.get(1));
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),event -> {
            if (animate)
                tower.setImage(imagesForAnimate.get(2));
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(700),event -> {
            if(animate)
                tower.setImage(imagesForAnimate.get(3));
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(900),event -> {
            if(animate)
                tower.setImage(imagesForAnimate.getFirst());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
