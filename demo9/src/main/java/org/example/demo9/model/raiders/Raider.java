package org.example.demo9.model.raiders;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.demo9.MapController;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.towers.*;

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
    public Raider(int health,int loot,int speed, ArrayList <ArrayList <Double>> road,ImageView raider)
    {
        this.health=health;
        this.loot=loot;
        this.speed=speed;
        this.road=road;
        this.raider=raider;
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

    public void walk()
    {
        int steps=(int)Math.sqrt(((Math.abs(raider.getLayoutX()-this.road.get(1).getFirst()))*(Math.abs(raider.getLayoutX()-this.road.get(1).getFirst())))+((Math.abs(raider.getLayoutY()-this.road.get(1).getLast()))*(Math.abs(raider.getLayoutY()-this.road.get(1).getLast()))));
        steps/=15;
        this.x=raider.getLayoutX();
        this.y=raider.getLayoutY();
        walk(steps,1);
    }
    public void walk(int steps,int counter)
    {
        Timeline timeline=new Timeline();
        int i;
        for(i=0;i<this.imagesForAnimation.size();++i) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50+i*50), event -> {
                raider.setImage(imagesForAnimation.get(finalI));
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i*50+50),event -> {
            raider.setImage(imagesForAnimation.getFirst());
            for(Tower temp: MapController.getMap().getTowers())
                if(temp!=null)
                {
                    double startX=temp.getTower().getLayoutX()-temp.getDomain();
                    double startY=temp.getTower().getLayoutY()-temp.getDomain();
                    double endX=temp.getTower().getLayoutX()+temp.getDomain();
                    double endY=temp.getTower().getLayoutY()+temp.getDomain();
                    if(raider.getLayoutX()+raider.getTranslateX()>=startX && raider.getLayoutX()+raider.getTranslateX()<=endX && raider.getLayoutY()+raider.getTranslateY()>=startY && raider.getLayoutY()+raider.getTranslateY()<=endY)
                    {
                        if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==null)
                        {
                            translateX=raider.getTranslateX();
                            translateY=raider.getTranslateY();
                            ((ArcherTower) temp).setAttacking(this);
                            temp.damage();
                        }
                        if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==null)
                        {
                            translateX=raider.getTranslateX();
                            translateY=raider.getTranslateY();
                            ((WizardTower) temp).setAttacking(this);
                            temp.damage();
                        }
                        else if(temp instanceof Artillery || temp instanceof DefendTower)
                        {
                            temp.damage();
                        }
                    }
                    else
                    {
                        if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==this)
                            ((ArcherTower) temp).setAttacking(null);
                        if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==this)
                            ((WizardTower) temp).setAttacking(null);
                    }
                }
        }));
        timeline.setCycleCount(steps);
        TranslateTransition transitionY=new TranslateTransition();
        transitionY.setByY(this.road.get(counter).getLast()-y);
        transitionY.setDuration(Duration.millis(((i+1)*steps)*50));
        transitionY.setNode(this.raider);
        transitionY.setCycleCount(1);
        TranslateTransition transitionX=new TranslateTransition();
        transitionX.setByX(this.road.get(counter).getFirst()-x);
        transitionX.setDuration(Duration.millis(((i+1)*steps)*50));
        transitionX.setNode(this.raider);
        transitionX.setCycleCount(1);
        ParallelTransition parallelTransition=new ParallelTransition(this.raider,timeline,transitionX,transitionY);
        parallelTransition.setCycleCount(1);
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
                MapController.getMap().getRaidersInMap().remove(raider);
                for(Tower temp:MapController.getMap().getTowers())
                {
                    if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==this)
                        ((ArcherTower) temp).setAttacking(null);
                    if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==this)
                        ((WizardTower) temp).setAttacking(null);
                }
                raider.setLayoutY(0); raider.setLayoutX(0);
                if(Integer.parseInt(Controller.getController().getHearts().getText())<=0)
                    ;
                //lose page
            }
        });
        parallelTransition.play();
    }
}
