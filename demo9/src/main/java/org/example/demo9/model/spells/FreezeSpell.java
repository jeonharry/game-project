package org.example.demo9.model.spells;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.example.demo9.MapController;
import org.example.demo9.model.raiders.DisappearingRaider;
import org.example.demo9.model.raiders.FlierRaider;
import org.example.demo9.model.raiders.Raider;
import org.example.demo9.model.towers.*;

public class FreezeSpell implements Spell {
    private static int price=350;
    private static String info="THIS SPECIAL CONCOCTION WILL FREEZE \nYOUR ENEMIES HELPLESS IN PLACE FOR \nA WHILE.";

    public static int getPrice() {
        return price;
    }

    @Override
    public void drop() {
        Timeline timeline=new Timeline();
        Timeline stop=new Timeline();
        final long[] time=new long[1];
        stop.getKeyFrames().add(new KeyFrame(Duration.millis(50),e ->{
            for(Raider raider: MapController.getMap().getRaidersInMap())
                if(raider!=null)
                {
                    raider.getTransition().pause();
                }
            time[0] = System.currentTimeMillis();
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),e ->{
            for(Raider raider: MapController.getMap().getRaidersInMap())
                if(raider!=null)
                {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(raider.getSpeed()+raider.getSpeed()),event -> {
                        if(System.currentTimeMillis()- time[0] >raider.getWait())
                        {
                            if(raider instanceof DisappearingRaider)
                            {
                                if(((DisappearingRaider) raider).isDisapear())
                                {
                                    raider.getRaider().setVisible(true);
                                    ((DisappearingRaider) raider).setDisapear(false);
                                }
                            }
                            time[0] =System.currentTimeMillis();
                            for(Tower temp: MapController.getMap().getTowers())
                                if(temp!=null)
                                {
                                    double startX=temp.getTower().getLayoutX()-temp.getDomain();
                                    double startY=temp.getTower().getLayoutY()-temp.getDomain();
                                    double endX=temp.getTower().getLayoutX()+temp.getDomain();
                                    double endY=temp.getTower().getLayoutY()+temp.getDomain();
                                    if(raider.getRaider().getLayoutX()+raider.getRaider().getTranslateX()>=startX && raider.getRaider().getLayoutX()+raider.getRaider().getTranslateX()<=endX && raider.getRaider().getLayoutY()+raider.getRaider().getTranslateY()>=startY && raider.getRaider().getLayoutY()+raider.getRaider().getTranslateY()<=endY)
                                    {
                                        if(temp instanceof ArcherTower && (((ArcherTower) temp).getAttacking()==null || ((ArcherTower) temp).getAttacking().equals(raider)))
                                        {
                                            raider.setTranslateX(raider.getRaider().getTranslateX());
                                            raider.setTranslateY(raider.getRaider().getTranslateY());
                                            ((ArcherTower) temp).setAttacking(raider);
                                            temp.damage(raider);
                                        }
                                        if(temp instanceof WizardTower && (((WizardTower) temp).getAttacking()==null || ((WizardTower) temp).getAttacking().equals(raider)))
                                        {
                                            raider.setTranslateX(raider.getRaider().getTranslateX());
                                            raider.setTranslateY(raider.getRaider().getTranslateY());
                                            ((WizardTower) temp).setAttacking(raider);
                                            temp.damage(raider);
                                        }
                                        if(temp instanceof DefendTower && !(raider instanceof FlierRaider) && !((DefendTower) temp).getOnAttackings().contains(raider))
                                        {
                                            if(!((DefendTower) temp).isStopping())
                                            {
                                                raider.setTranslateX(raider.getRaider().getTranslateX());
                                                raider.setTranslateY(raider.getRaider().getTranslateY());
                                                ((DefendTower) temp).getOnAttackings().add(raider);
                                                ((DefendTower) temp).setStopping(true);
                                                raider.setDefendTower((DefendTower) temp);
                                                temp.damage(raider);
                                            }
                                            else
                                            {
                                                if(((DefendTower) temp).getOnAttackings().contains(raider))
                                                    ((DefendTower) temp).getOnAttackings().remove(raider);
                                                else
                                                {
                                                    if(!raider.getDefendTower().equals(temp))
                                                    {
                                                        ((DefendTower) temp).getOnAttackings().add(raider);
                                                        raider.setDefendTower((DefendTower) temp);
                                                    }
                                                }
                                            }
                                        }
                                        if(temp instanceof Artillery && !(raider instanceof FlierRaider) && (((Artillery) temp).getOnAttacking()==null || ((Artillery) temp).getOnAttacking().equals(raider)))
                                        {
                                            raider.setTranslateX(raider.getRaider().getTranslateX());
                                            raider.setTranslateY(raider.getRaider().getTranslateY());
                                            ((Artillery) temp).setOnAttacking(raider);
                                            temp.damage(raider);
                                        }
                                    }
                                    else
                                    {
                                        if(temp instanceof ArcherTower && ((ArcherTower) temp).getAttacking()==raider)
                                            ((ArcherTower) temp).setAttacking(null);
                                        if(temp instanceof WizardTower && ((WizardTower) temp).getAttacking()==raider)
                                            ((WizardTower) temp).setAttacking(null);
                                        if(temp instanceof DefendTower && ((DefendTower) temp).isStopping() && ((DefendTower) temp).getOnAttackings().contains(raider))
                                        {
                                            ((DefendTower) temp).setStopping(false);
                                            ((DefendTower) temp).getOnAttackings().remove(raider);
                                        }
                                        if(temp instanceof  Artillery && ((Artillery) temp).getOnAttacking()==raider)
                                            ((Artillery) temp).setOnAttacking(null);
                                    }
                                }
                        }
                    }));
                }
        }));
        stop.getKeyFrames().add(new KeyFrame(Duration.millis(2050),e ->{
            for(Raider raider: MapController.getMap().getRaidersInMap())
                if(raider!=null)
                {
                    raider.getTransition().play();
                }
        }));
        timeline.setCycleCount(20);
        stop.play();
        timeline.play();
    }

    public static String getInfo() {
        return info;
    }
}
