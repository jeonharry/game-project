package org.example.demo9.model.towers;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import org.example.demo9.*;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.exceptions.NotEnoughLevel;
import org.example.demo9.model.Direction;
import org.example.demo9.model.raiders.DisappearingRaider;
import org.example.demo9.model.raiders.FlierRaider;
import org.example.demo9.model.raiders.Raider;
import org.example.demo9.model.raiders.ShieldRaider;

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
    private ImageView attackingDevice;
    private int sellPrice;
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
        this.sellPrice=price;
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

    public ImageView getAttackingDevice() {
        return attackingDevice;
    }

    public void setAttackingDevice(ImageView attackingDevice) {
        this.attackingDevice = attackingDevice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int animation()
    {
        Timeline timeline=new Timeline();
        int i;
        for(i=0;i<imagesForAnimate.size();++i) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200+i*200), event -> {
                if(animate)
                    towerImage.setImage(imagesForAnimate.get(finalI));
                else
                    towerImage.setImage(imagesForAnimate.getFirst());
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i*200+200),event -> {
            if(animate)
                towerImage.setImage(imagesForAnimate.getFirst());
        }));
        timeline.setCycleCount(1);
        timeline.play();
        return (i+1)*200;
    }
    public void destroy()
    {
        MapController.getMap().getTowers().remove(this);
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
        sellPrice+=upgradePrice;
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
    public void damage(Raider enemy)
    {
        ImageView arrow=new ImageView();
        arrow.setImage(attackingDevice.getImage());
        arrow.getTransforms().addAll(attackingDevice.getTransforms());
        int duration=this.animation();
        Timeline timeline=new Timeline();
        if(!(this instanceof DefendTower))
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration),event -> {
                arrow.setPreserveRatio(true); arrow.setLayoutX(this.getTower().getLayoutX()+40); arrow.setLayoutY(this.getTower().getLayoutY()+10);
                arrow.setFitHeight(12); arrow.setFitHeight(12);
                Controller.getController().getMap().getChildren().add(arrow);
            }));
        if(this instanceof ArcherTower && ((ArcherTower) this).getAttacking()!=null)
        {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration),event ->{
                if(((ArcherTower) this).getAttacking()!=null)
                {
                    TranslateTransition transitionY=new TranslateTransition();
                    transitionY.setByY(((ArcherTower) this).getAttacking().getY()+(((ArcherTower) this).getAttacking().getRaider().getTranslateY()-((ArcherTower) this).getAttacking().getTranslateY())-arrow.getLayoutY());
                    transitionY.setDuration(Duration.millis(100));
                    transitionY.setNode(arrow);
                    transitionY.setCycleCount(1);
                    TranslateTransition transitionX=new TranslateTransition();
                    transitionX.setByX(((ArcherTower) this).getAttacking().getX()+(((ArcherTower) this).getAttacking().getRaider().getTranslateX()-((ArcherTower) this).getAttacking().getTranslateX())-arrow.getLayoutX());
                    transitionX.setDuration(Duration.millis(100));
                    transitionX.setNode(arrow);
                    transitionX.setCycleCount(1);
                    ParallelTransition parallelTransition=new ParallelTransition(arrow,transitionX,transitionY);
                    parallelTransition.setCycleCount(1);
                    parallelTransition.play();
                }
            }));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration+100),event ->{
                Controller.getController().getMap().getChildren().remove(arrow);
                arrow.setLayoutX(0); arrow.setLayoutY(0);
                if(((ArcherTower) this).getAttacking() instanceof ShieldRaider)
                    ((ArcherTower) this).getAttacking().setHealth(((ArcherTower) this).getAttacking().getHealth()-(this.getDamage()/2));
                else if(((ArcherTower) this).getAttacking()!=null)
                    ((ArcherTower) this).getAttacking().setHealth(((ArcherTower) this).getAttacking().getHealth()-this.getDamage());
                if(((ArcherTower) this).getAttacking()!=null)
                {
                    if(((ArcherTower) this).getAttacking().getHealth()<=0)
                    {
                        Controller.getController().getMap().getChildren().remove(((ArcherTower) this).getAttacking().getRaider());
                        Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+((ArcherTower) this).getAttacking().getLoot()));
                        MapController.getMap().getRaidersInMap().remove(((ArcherTower) this).getAttacking());
                        ((ArcherTower) this).getAttacking().getRaider().setLayoutY(0);
                        ((ArcherTower) this).getAttacking().getRaider().setLayoutX(0);
                        ((ArcherTower) this).getAttacking().getTransition().stop();
                        die(((ArcherTower) this).getAttacking());
                        ((ArcherTower) this).setAttacking(null);
                    }
                }
            }));
            timeline.play();
        }
        if(this instanceof WizardTower && ((WizardTower) this).getAttacking()!=null)
        {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration),event ->{
                if(((WizardTower) this).getAttacking()!=null)
                {
                    TranslateTransition transitionY=new TranslateTransition();
                    transitionY.setByY(((WizardTower) this).getAttacking().getY()+(((WizardTower) this).getAttacking().getRaider().getTranslateY()-((WizardTower) this).getAttacking().getTranslateY())-arrow.getLayoutY());
                    transitionY.setDuration(Duration.millis(100));
                    transitionY.setNode(arrow);
                    transitionY.setCycleCount(1);
                    TranslateTransition transitionX=new TranslateTransition();
                    transitionX.setByX(((WizardTower) this).getAttacking().getX()+(((WizardTower) this).getAttacking().getRaider().getTranslateX()-((WizardTower) this).getAttacking().getTranslateX())-arrow.getLayoutX());
                    transitionX.setDuration(Duration.millis(100));
                    transitionX.setNode(arrow);
                    transitionX.setCycleCount(1);
                    ParallelTransition parallelTransition=new ParallelTransition(arrow,transitionX,transitionY);
                    parallelTransition.setCycleCount(1);
                    parallelTransition.play();
                }
            }));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration+100),event ->{
                Controller.getController().getMap().getChildren().remove(arrow);
                arrow.setLayoutX(0); arrow.setLayoutY(0);
                if(((WizardTower) this).getAttacking() instanceof ShieldRaider && ((ShieldRaider) ((WizardTower) this).getAttacking()).isHaveShield())
                {
                    ((WizardTower) this).getAttacking().setHealth(((WizardTower) this).getAttacking().getHealth()-(((WizardTower) this).getAttacking().getHealth()/2));
                    ((ShieldRaider) ((WizardTower) this).getAttacking()).setHaveShield(false);
                }
                else if (((WizardTower) this).getAttacking()!=null)
                    ((WizardTower) this).getAttacking().setHealth(((WizardTower) this).getAttacking().getHealth()-this.getDamage());
                if(((WizardTower) this).getAttacking()!=null)
                {
                    if(((WizardTower) this).getAttacking().getHealth()<=0)
                    {
                        Controller.getController().getMap().getChildren().remove(((WizardTower) this).getAttacking().getRaider());
                        Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+((WizardTower) this).getAttacking().getLoot()));
                        MapController.getMap().getRaidersInMap().remove(((WizardTower) this).getAttacking());
                        ((WizardTower) this).getAttacking().getRaider().setLayoutY(0);
                        ((WizardTower) this).getAttacking().getRaider().setLayoutX(0);
                        ((WizardTower) this).getAttacking().getTransition().stop();
                        die(((WizardTower) this).getAttacking());
                        ((WizardTower) this).setAttacking(null);
                    }
                }
            }));
            timeline.play();
        }
        if(this instanceof Artillery && ((Artillery) this).getOnAttacking()!=null)
        {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration),event ->{
                if(((Artillery) this).getOnAttacking()!=null)
                {
                    TranslateTransition transitionY=new TranslateTransition();
                    transitionY.setByY(((Artillery) this).getOnAttacking().getY()+(((Artillery) this).getOnAttacking().getRaider().getTranslateY()-((Artillery) this).getOnAttacking().getTranslateY())-arrow.getLayoutY());
                    transitionY.setDuration(Duration.millis(200));
                    transitionY.setNode(arrow);
                    transitionY.setCycleCount(1);
                    TranslateTransition transitionX=new TranslateTransition();
                    transitionX.setByX(((Artillery) this).getOnAttacking().getX()+(((Artillery) this).getOnAttacking().getRaider().getTranslateX()-((Artillery) this).getOnAttacking().getTranslateX())-arrow.getLayoutX());
                    transitionX.setDuration(Duration.millis(200));
                    transitionX.setNode(arrow);
                    transitionX.setCycleCount(1);
                    ParallelTransition parallelTransition=new ParallelTransition(arrow,transitionX,transitionY);
                    parallelTransition.setCycleCount(1);
                    parallelTransition.play();
                }
            }));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration+200),event ->{
                Controller.getController().getMap().getChildren().remove(arrow);
                arrow.setLayoutX(0); arrow.setLayoutY(0);
                if(((Artillery) this).getOnAttacking()!=null)
                {
                    ((Artillery) this).getOnAttacking().setHealth(((Artillery) this).getOnAttacking().getHealth()-this.getDamage());
                    Raider onAttack=((Artillery) this).getOnAttacking();
                    for(Raider raider:MapController.getMap().getRaidersInMap())
                        if(raider!=null && onAttack!=null && !(raider instanceof FlierRaider))
                        {
                            if(raider instanceof DisappearingRaider && ((DisappearingRaider) raider).isDisapear())
                                break;
                            double distance=Math.sqrt((((onAttack.getRaider().getLayoutX()+onAttack.getRaider().getTranslateX())-(raider.getRaider().getLayoutX()+raider.getRaider().getTranslateX()))*((onAttack.getRaider().getLayoutX()+onAttack.getRaider().getTranslateX())-(raider.getRaider().getLayoutX()+raider.getRaider().getTranslateX())))+(((onAttack.getRaider().getLayoutY()+onAttack.getRaider().getTranslateY())-(raider.getRaider().getLayoutY()+raider.getRaider().getTranslateY()))*((onAttack.getRaider().getLayoutY()+onAttack.getRaider().getTranslateY())-(raider.getRaider().getLayoutY()+raider.getRaider().getTranslateY()))));
                            if(distance<=13)
                            {
                                raider.setHealth(raider.getHealth()-this.getDamage());
                                if(raider.getHealth()<=0)
                                {
                                    Controller.getController().getMap().getChildren().remove(raider.getRaider());
                                    Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+(raider.getLoot())));
                                    MapController.getMap().getRaidersInMap().remove(raider);
                                    raider.getRaider().setLayoutY(0);
                                    raider.getRaider().setLayoutX(0);
                                    raider.getTransition().stop();
                                    die(raider);
                                }
                            }
                        }
                    if(((Artillery) this).getOnAttacking().getHealth()<=0)
                    {
                        Controller.getController().getMap().getChildren().remove(((Artillery) this).getOnAttacking().getRaider());
                        Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+(((Artillery) this).getOnAttacking().getLoot())));
                        MapController.getMap().getRaidersInMap().remove(((Artillery) this).getOnAttacking());
                        ((Artillery) this).getOnAttacking().getRaider().setLayoutY(0);
                        ((Artillery) this).getOnAttacking().getRaider().setLayoutX(0);
                        ((Artillery) this).getOnAttacking().getTransition().stop();
                        die(((Artillery) this).getOnAttacking());
                        ((Artillery) this).setOnAttacking(null);
                    }
                }
            }));
            this.animation();
        }
        if(this instanceof DefendTower)
        {
            double distance=8888888888.0;
            int num=-1;
            for(int i=0;i<MapController.getMap().getTowerPlaces().size();++i)
            {
                if(distance>Math.sqrt(((Math.abs(tower.getLayoutX()-MapController.getMap().getTowerPlaces().get(i)))*(Math.abs(tower.getLayoutX()-MapController.getMap().getTowerPlaces().get(i))))+((Math.abs(tower.getLayoutY()-MapController.getMap().getTowerPlaces().get(i+1)))*(Math.abs(tower.getLayoutY()-MapController.getMap().getTowerPlaces().get(i+1))))))
                {
                    distance=Math.sqrt(((Math.abs(tower.getLayoutX()-MapController.getMap().getTowerPlaces().get(i)))*(Math.abs(tower.getLayoutX()-MapController.getMap().getTowerPlaces().get(i))))+((Math.abs(tower.getLayoutY()-MapController.getMap().getTowerPlaces().get(i+1)))*(Math.abs(tower.getLayoutY()-MapController.getMap().getTowerPlaces().get(i+1)))));
                    num=i;
                }
                i++;
            }
            Direction direction=MapController.getMap().getDirections().get(num/2);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0),event -> {
                if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN))
                {
                    arrow.setImage(new Image(Main.class.getResource("pics/brick-wall-gray-seamless-pattern-background-light-cartoon-vector-5texture-illustration-5horizontal-old-grey-179065880.jpg").toExternalForm()));
                    arrow.setFitHeight(45); arrow.setFitHeight(45);
                    arrow.setLayoutX(this.getTower().getLayoutX());
                    arrow.setLayoutY(((DefendTower) this).getOnAttackings().getFirst().getRaider().getLayoutY()+((DefendTower) this).getOnAttackings().getFirst().getTranslateY());
                }
                else
                {
                    arrow.setImage(new Image(Main.class.getResource("pics/brick-wall-gray-seamless-pattern-background-light-cartoon-vector-texture-illustration-horizontal-old-grey-179065880.jpg").toExternalForm()));
                    arrow.setFitHeight(45); arrow.setFitHeight(45);
                    arrow.setLayoutX(((DefendTower) this).getOnAttackings().getFirst().getRaider().getLayoutX()+((DefendTower) this).getOnAttackings().getFirst().getTranslateX());
                    arrow.setLayoutY(this.getTower().getLayoutY());
                }
                arrow.setPreserveRatio(true);
                Controller.getController().getMap().getChildren().add(arrow);
                for(Raider raider:((DefendTower) this).getOnAttackings())
                    if(raider!=null)
                        raider.getTransition().pause();
            }));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), e ->{
                Controller.getController().getMap().getChildren().remove(arrow);
                arrow.setLayoutX(0); arrow.setLayoutY(0);
                for(Raider raider:((DefendTower) this).getOnAttackings())
                    if(raider!=null)
                    {
                        raider.getTransition().play();
                    }
            }));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10000),e ->{
                ((DefendTower) this).setStopping(false);
            }));
            timeline.play();
        }
    }
    public void die(Raider raider)
    {
        for(Tower temp:MapController.getMap().getTowers())
            if(temp!=null)
            {
                if(raider!=null)
                {
                    if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()!=null)
                        if(((ArcherTower) temp).getAttacking().equals(raider))
                            ((ArcherTower) temp).setAttacking(null);
                    if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()!=null)
                        if(((WizardTower) temp).getAttacking().equals(raider))
                            ((WizardTower) temp).setAttacking(null);
                    if (temp instanceof Artillery && ((Artillery) temp).getOnAttacking() != null)
                        if (((Artillery) temp).getOnAttacking().equals(raider))
                            ((Artillery) temp).setOnAttacking(null);
                    if(temp instanceof DefendTower)
                        ((DefendTower) temp).getOnAttackings().remove(raider);
                    LosePageController.setGems(LosePageController.getGems()+1);
                }
            }
        if(Controller.getController().getWaves().getText().compareTo(MapController.getMap().getAttackWaves()+"/"+MapController.getMap().getAttackWaves())==0 && MapController.getMap().getRaidersInMap().isEmpty())
            MapController.win();
        if(MapController.getMap().getRaidersInMap().isEmpty())
        {
            Controller.getController().getMusic().stop();
            MediaPlayer music=new MediaPlayer(new Media(Main.class.getResource("audio/02. Battle Preparations.mp3").toExternalForm()));
            music.setVolume(Controller.getController().getMusic().getVolume());
            Controller.getController().setMusic(music);
            Controller.getController().getMusic().setCycleCount(MediaPlayer.INDEFINITE);
            Controller.getController().getMusic().play();
        }
    }
}
