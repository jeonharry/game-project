package org.example.demo9.model.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import org.example.demo9.Main;
import org.example.demo9.TowerController;
import org.example.demo9.TowerGeneratorController;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.exceptions.NotEnoughLevel;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Tower
{
    private int damage;
    private int price;
    private double domain;
    private int level=1;
    private ImageView towerImage;
    private Node tower;
    private ArrayList <Image> imagesForAnimate=new ArrayList<>();
    private boolean animate=true;
    private int upgradePrice;
    private ImageView level2;
    private ImageView level3;
    private ImageView level4;
    private ImageView level5;
    private ImageView arrow;
    public Tower(int damage,int price,int upgradePrice,double domain,String towerImage) throws IOException {
        TowerController.setTower(this);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("Tower.fxml"));
        this.tower=loader.load();
        this.towerImage.setImage(new Image(Main.class.getResource(towerImage).toExternalForm()));
        this.damage=damage;
        this.domain=domain;
        this.price=price;
        this.upgradePrice=upgradePrice;
        tower.setCursor(Cursor.HAND);
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

    public ImageView getTowerImage() {
        return towerImage;
    }

    public void setTowerImage(ImageView towerImage) {
        this.towerImage = towerImage;
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public void setUpgradePrice(int upgradePrice) {
        this.upgradePrice = upgradePrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ImageView getLevel2() {
        return level2;
    }

    public void setLevel2(ImageView level2) {
        this.level2 = level2;
    }

    public ImageView getLevel3() {
        return level3;
    }

    public void setLevel3(ImageView level3) {
        this.level3 = level3;
    }

    public ImageView getLevel4() {
        return level4;
    }

    public void setLevel4(ImageView level4) {
        this.level4 = level4;
    }

    public ImageView getLevel5() {
        return level5;
    }

    public void setLevel5(ImageView level5) {
        this.level5 = level5;
    }

    public Node getTower() {
        return tower;
    }

    public void setTower(Node tower) {
        this.tower = tower;
    }

    public ArrayList<Image> getImagesForAnimate() {
        return imagesForAnimate;
    }

    public void setImagesForAnimate(ArrayList<Image> imagesForAnimate) {
        this.imagesForAnimate = imagesForAnimate;
    }

    public ImageView getArrow() {
        return arrow;
    }

    public void setArrow(ImageView arrow) {
        this.arrow = arrow;
    }

    public void animation()
    {
        Timeline timeline=new Timeline();
        int i = 0;
        for(i=0;i<imagesForAnimate.size();++i) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(150+i*150), event -> {
                if(animate)
                    towerImage.setImage(imagesForAnimate.get(finalI));
                else
                    towerImage.setImage(imagesForAnimate.getFirst());
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i*150+150),event -> {
            if(animate)
                towerImage.setImage(imagesForAnimate.getFirst());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void destroy()
    {
        TowerGeneratorController.getMap().getTowers().remove(this);
        Controller.getController().getMap().getChildren().remove(this.getTower());
        Ellipse ellipse=new Ellipse(); ellipse.setLayoutX(this.getTower().getLayoutX()+30); ellipse.setLayoutY(this.getTower().getLayoutY()+40);
        ellipse.setFill(Color.TRANSPARENT); ellipse.setStroke(Color.TRANSPARENT); ellipse.setRadiusX(30); ellipse.setRadiusY(23); ellipse.setCursor(Cursor.HAND);
        Controller.getController().getMap().getChildren().add(ellipse);
        ellipse.setOnMouseClicked(e -> {
            try {
                if(Controller.getController().getSelectedTower()!=null)
                {
                    Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
                    Controller.getController().setSelectedTower(null);
                }
                if(Controller.getController().getPageForUpgrade()!=null)
                {
                    Controller.getController().getMap().getChildren().remove(Controller.getController().getPageForUpgrade());
                    Controller.getController().setPageForUpgrade(null);
                }
                Node child=new FXMLLoader(Main.class.getResource("TowerGenerator.fxml")).load();
                child.setLayoutX(ellipse.getLayoutX()-65); child.setLayoutY(ellipse.getLayoutY()-65);
                Controller.getController().getMap().getChildren().add(child);
                Controller.getController().setSelectedTower(child);
                Controller.getController().setTowerPlace(ellipse);
            } catch (IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
        });
    }
    public void upgrade() throws NotEnoughLevel {
        if(PlayerController.getPlayer().getLevel()<=level)
            throw new NotEnoughLevel();
        upgradePrice+=50;
        damage+=20;
        domain+=5;
        level++;
        if(this.getLevel()>1)
            level2.setVisible(true);
        if(this.getLevel()>2)
            level3.setVisible(true);
        if(this.getLevel()>3)
            level4.setVisible(true);
        if(this.getLevel()>4)
            level5.setVisible(true);
    }
    public void damage()
    {
        this.getArrow().setPreserveRatio(true); this.getArrow().setLayoutX(this.getTower().getLayoutX()+40); this.getArrow().setLayoutY(this.getTower().getLayoutY()+10);
        this.getArrow().setFitHeight(12); this.getArrow().setFitHeight(12);
        Controller.getController().getMap().getChildren().add(arrow);
        //transition & damage
    }
}
