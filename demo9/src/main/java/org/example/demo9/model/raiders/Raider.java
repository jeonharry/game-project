package org.example.demo9.model.raiders;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.demo9.LosePageController;
import org.example.demo9.Main;
import org.example.demo9.MapController;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.towers.*;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Raider
{
    private int health;
    private int loot;
    private int speed;
    private ArrayList <ArrayList<Double>> road;
    private ArrayList <Image> imagesForAnimation=new ArrayList<>();
    private ImageView raider;
    private double x;
    private double y;
    private double translateX;
    private double translateY;
    private ParallelTransition transition;
    private DefendTower defendTower=null;
    private int wait;
    public Raider(int health,int loot,int speed, int wait,ArrayList <ArrayList <Double>> road,ImageView raider)
    {
        this.health=health;
        this.loot=loot;
        this.speed=speed;
        this.road=road;
        this.raider=raider;
        this.wait=wait;
        this.raider.setFitHeight(35); this.raider.setFitWidth(35); this.raider.setPreserveRatio(true);
        Controller.getController().getMap().getChildren().add(this.raider);
        this.raider.setLayoutY(road.getFirst().getLast()); this.raider.setLayoutX(road.getFirst().getFirst());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<ArrayList<Double>> getRoad() {
        return road;
    }

    public void setRoad(ArrayList<ArrayList<Double>> road) {
        this.road = road;
    }

    public ArrayList<Image> getImagesForAnimation() {
        return imagesForAnimation;
    }

    public void setImagesForAnimation(ArrayList<Image> imagesForAnimation) {
        this.imagesForAnimation = imagesForAnimation;
    }

    public ImageView getRaider() {
        return raider;
    }

    public void setRaider(ImageView raider) {
        this.raider = raider;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public ParallelTransition getTransition() {
        return transition;
    }

    public void setTransition(ParallelTransition transition) {
        this.transition = transition;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void walk()
    {
        int steps=(int)Math.sqrt(((Math.abs(raider.getLayoutX()-this.road.get(1).getFirst()))*(Math.abs(raider.getLayoutX()-this.road.get(1).getFirst())))+((Math.abs(raider.getLayoutY()-this.road.get(1).getLast()))*(Math.abs(raider.getLayoutY()-this.road.get(1).getLast()))));
        steps/=15;
        this.x=raider.getLayoutX();
        this.y=raider.getLayoutY();
        if(this instanceof  DisappearingRaider)
            ((DisappearingRaider) this).setTime(System.currentTimeMillis());
        walk(steps,1);
    }

    public DefendTower getDefendTower() {
        return defendTower;
    }

    public void setDefendTower(DefendTower defendTower) {
        this.defendTower = defendTower;
    }

    public void walk(int steps, int counter)
    {
        ParallelTransition parallelTransition=new ParallelTransition(this.raider);
        transition=parallelTransition;
        Timeline timeline=new Timeline();
        int i;
        for(i=0;i<this.imagesForAnimation.size();++i) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(this.speed+i*this.speed), event -> {
                raider.setImage(imagesForAnimation.get(finalI));
            }));
        }
        final long[] time = {System.currentTimeMillis()};
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i*this.speed+this.speed),event -> {
            raider.setImage(imagesForAnimation.getFirst());
            if(this instanceof DisappearingRaider && ((DisappearingRaider) this).isDisapear())
                raider.setVisible(false);
            if(System.currentTimeMillis()- time[0] >this.wait)
            {
                if(this instanceof DisappearingRaider)
                {
                    if(((DisappearingRaider) this).isDisapear() && System.currentTimeMillis()- ((DisappearingRaider) this).getTime() >9000)
                    {
                        raider.setVisible(true);
                        ((DisappearingRaider) this).setDisapear(false);
                    }
                    else if(!((DisappearingRaider) this).isDisapear())
                    {
                        for(Tower temp: MapController.getMap().getTowers())
                            if(temp!=null)
                            {
                                double startX=temp.getTower().getLayoutX()-temp.getDomain();
                                double startY=temp.getTower().getLayoutY()-temp.getDomain();
                                double endX=temp.getTower().getLayoutX()+temp.getDomain();
                                double endY=temp.getTower().getLayoutY()+temp.getDomain();
                                if(raider.getLayoutX()+raider.getTranslateX()>=startX && raider.getLayoutX()+raider.getTranslateX()<=endX && raider.getLayoutY()+raider.getTranslateY()>=startY && raider.getLayoutY()+raider.getTranslateY()<=endY)
                                {
                                    if(temp instanceof ArcherTower && (((ArcherTower) temp).getAttacking()==null || ((ArcherTower) temp).getAttacking().equals(this)))
                                    {
                                        translateX=raider.getTranslateX();
                                        translateY=raider.getTranslateY();
                                        ((ArcherTower) temp).setAttacking(this);
                                        temp.damage(this);
                                    }
                                    if(temp instanceof WizardTower && (((WizardTower) temp).getAttacking()==null || ((WizardTower) temp).getAttacking().equals(this)))
                                    {
                                        translateX=raider.getTranslateX();
                                        translateY=raider.getTranslateY();
                                        ((WizardTower) temp).setAttacking(this);
                                        temp.damage(this);
                                    }
                                    if(temp instanceof DefendTower && !((DefendTower) temp).getOnAttackings().contains(this))
                                    {
                                        if(!((DefendTower) temp).isStopping())
                                        {
                                            translateX=raider.getTranslateX();
                                            translateY=raider.getTranslateY();
                                            ((DefendTower) temp).getOnAttackings().add(this);
                                            ((DefendTower) temp).setStopping(true);
                                            defendTower=(DefendTower) temp;
                                            temp.damage(this);
                                        }
                                        else
                                        {
                                            if(((DefendTower) temp).getOnAttackings().contains(this))
                                                ((DefendTower) temp).getOnAttackings().remove(this);
                                            else
                                            {
                                                if(!defendTower.equals(temp))
                                                {
                                                    ((DefendTower) temp).getOnAttackings().add(this);
                                                    defendTower=(DefendTower) temp;
                                                }
                                            }
                                        }
                                    }
                                    if(temp instanceof Artillery && !(this instanceof FlierRaider)  && (((Artillery) temp).getOnAttacking()==null || ((Artillery) temp).getOnAttacking().equals(this)))
                                    {
                                        translateX=raider.getTranslateX();
                                        translateY=raider.getTranslateY();
                                        ((Artillery) temp).setOnAttacking(this);
                                        temp.damage(this);
                                    }
                                }
                                else
                                {
                                    if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==this)
                                        ((ArcherTower) temp).setAttacking(null);
                                    if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==this)
                                        ((WizardTower) temp).setAttacking(null);
                                    if(temp instanceof DefendTower && ((DefendTower) temp).isStopping() && ((DefendTower) temp).getOnAttackings().contains(this))
                                    {
                                        ((DefendTower) temp).setStopping(false);
                                        ((DefendTower) temp).getOnAttackings().remove(this);
                                    }
                                    if(temp instanceof  Artillery && ((Artillery) temp).getOnAttacking()==this)
                                        ((Artillery) temp).setOnAttacking(null);
                                }
                            }
                    }
                }
                else
                {
                    time[0] =System.currentTimeMillis();
                    for(Tower temp: MapController.getMap().getTowers())
                        if(temp!=null)
                        {
                            double startX=temp.getTower().getLayoutX()-temp.getDomain();
                            double startY=temp.getTower().getLayoutY()-temp.getDomain();
                            double endX=temp.getTower().getLayoutX()+temp.getDomain();
                            double endY=temp.getTower().getLayoutY()+temp.getDomain();
                            if(raider.getLayoutX()+raider.getTranslateX()>=startX && raider.getLayoutX()+raider.getTranslateX()<=endX && raider.getLayoutY()+raider.getTranslateY()>=startY && raider.getLayoutY()+raider.getTranslateY()<=endY)
                            {
                                if(temp instanceof ArcherTower && (((ArcherTower) temp).getAttacking()==null || ((ArcherTower) temp).getAttacking().equals(this)))
                                {
                                    translateX=raider.getTranslateX();
                                    translateY=raider.getTranslateY();
                                    ((ArcherTower) temp).setAttacking(this);
                                    temp.damage(this);
                                }
                                if(temp instanceof WizardTower && (((WizardTower) temp).getAttacking()==null || ((WizardTower) temp).getAttacking().equals(this)))
                                {
                                    translateX=raider.getTranslateX();
                                    translateY=raider.getTranslateY();
                                    ((WizardTower) temp).setAttacking(this);
                                    temp.damage(this);
                                }
                                if(temp instanceof DefendTower && !(this instanceof FlierRaider) && !((DefendTower) temp).getOnAttackings().contains(this))
                                {
                                    if(!((DefendTower) temp).isStopping())
                                    {
                                        translateX=raider.getTranslateX();
                                        translateY=raider.getTranslateY();
                                        ((DefendTower) temp).getOnAttackings().add(this);
                                        ((DefendTower) temp).setStopping(true);
                                        defendTower=(DefendTower) temp;
                                        temp.damage(this);
                                    }
                                    else
                                    {
                                        if(((DefendTower) temp).getOnAttackings().contains(this))
                                            ((DefendTower) temp).getOnAttackings().remove(this);
                                        else
                                        {
                                            if(!defendTower.equals(temp))
                                            {
                                                ((DefendTower) temp).getOnAttackings().add(this);
                                                defendTower=(DefendTower) temp;
                                            }
                                        }
                                    }
                                }
                                if(temp instanceof Artillery && !(this instanceof FlierRaider)  && (((Artillery) temp).getOnAttacking()==null || ((Artillery) temp).getOnAttacking().equals(this)))
                                {
                                    translateX=raider.getTranslateX();
                                    translateY=raider.getTranslateY();
                                    ((Artillery) temp).setOnAttacking(this);
                                    temp.damage(this);
                                }
                            }
                            else
                            {
                                if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==this)
                                    ((ArcherTower) temp).setAttacking(null);
                                if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==this)
                                    ((WizardTower) temp).setAttacking(null);
                                if(temp instanceof DefendTower && ((DefendTower) temp).isStopping() && ((DefendTower) temp).getOnAttackings().contains(this))
                                {
                                    ((DefendTower) temp).setStopping(false);
                                    ((DefendTower) temp).getOnAttackings().remove(this);
                                }
                                if(temp instanceof  Artillery && ((Artillery) temp).getOnAttacking()==this)
                                    ((Artillery) temp).setOnAttacking(null);
                            }
                        }
                }
            }
        }));
        timeline.setCycleCount(steps);
        TranslateTransition transitionY=new TranslateTransition();
        transitionY.setByY(this.road.get(counter).getLast()-y);
        transitionY.setDuration(Duration.millis(((i+1)*steps)*this.speed));
        transitionY.setNode(this.raider);
        transitionY.setCycleCount(1);
        TranslateTransition transitionX=new TranslateTransition();
        transitionX.setByX(this.road.get(counter).getFirst()-x);
        transitionX.setDuration(Duration.millis(((i+1)*steps)*this.speed));
        transitionX.setNode(this.raider);
        transitionX.setCycleCount(1);
        parallelTransition.setCycleCount(1);
        parallelTransition.getChildren().add(timeline);
        parallelTransition.getChildren().add(transitionX);
        parallelTransition.getChildren().add(transitionY);
        parallelTransition.setOnFinished(event ->{
            int k=counter+1;
            if(health>0 && k<this.road.size())
            {
                x=this.road.get(k-1).getFirst();
                y=this.road.get(k-1).getLast();
                walk(((int)Math.sqrt(((Math.abs(x-this.road.get(k).getFirst()))*(Math.abs(x-this.road.get(k).getFirst())))+((Math.abs(y-this.road.get(k).getLast()))*(Math.abs(y-this.road.get(k).getLast()))))/15),k);
            }
            else if(k>=this.road.size())
            {
                Controller.getController().getHearts().setText(String.valueOf(Integer.parseInt(Controller.getController().getHearts().getText())-1));
                Controller.getController().getMap().getChildren().remove(this.getRaider());
                MapController.getMap().getRaidersInMap().remove(this);
                for(Tower temp:MapController.getMap().getTowers())
                {
                    if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==this)
                        ((ArcherTower) temp).setAttacking(null);
                    if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==this)
                        ((WizardTower) temp).setAttacking(null);
                }
                raider.setLayoutY(0); raider.setLayoutX(0);
                if(Integer.parseInt(Controller.getController().getHearts().getText())<=0)
                {
                    end();
                    LosePageController.setMap(MapController.getMap());
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("LosePage.fxml"));
                    try {
                        Controller.getController().getMap().getChildren().add(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(Controller.getController().getWaves().getText().compareTo(MapController.getMap().getAttackWaves()+"/"+MapController.getMap().getAttackWaves())==0 && MapController.getMap().getRaidersInMap().isEmpty())
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
        });
        parallelTransition.play();
    }
    private void end()
    {
        for(Raider raider: MapController.getMap().getRaidersInMap())
            if(raider!=null)
            {
                raider.getTransition().stop();
                Controller.getController().getMap().getChildren().remove(raider.getRaider());
                raider.getRaider().setLayoutY(0);
                raider.getRaider().setLayoutX(0);
                for(Tower temp:MapController.getMap().getTowers())
                    if(temp!=null)
                    {
                        if (temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking() != null)
                            if (((ArcherTower) temp).getAttacking().equals(raider))
                                ((ArcherTower) temp).setAttacking(null);
                        if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()!=null)
                            if(((WizardTower) temp).getAttacking().equals(raider))
                                ((WizardTower) temp).setAttacking(null);
                        if (temp instanceof Artillery && ((Artillery) temp).getOnAttacking() != null)
                            if (((Artillery) temp).getOnAttacking().equals(raider))
                                ((Artillery) temp).setOnAttacking(null);
                        if(temp instanceof DefendTower)
                            ((DefendTower) temp).getOnAttackings().remove(raider);
                    }
            }
        MapController.getMap().getRaidersInMap().removeAll(MapController.getMap().getRaidersInMap());
    }
}
