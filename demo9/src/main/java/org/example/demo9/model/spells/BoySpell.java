package org.example.demo9.model.spells;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.demo9.Main;
import org.example.demo9.MapController;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.raiders.Raider;
import org.example.demo9.model.towers.*;

public class BoySpell implements Spell {
    private static int price=500;
    private static String info="YOU ONLY NEED ONE OF THESE TO CLEAR \nTHE BATTLEFIELD AND CLAIM THE SPOILS \nOF WAR! JUST TELL US WHERE TO DROP IT!";

    public static int getPrice() {
        return price;
    }

    @Override
    public void drop() {
        for(Raider raider: MapController.getMap().getRaidersInMap())
            if(raider!=null)
            {
                raider.getTransition().stop();
                Controller.getController().getMap().getChildren().remove(raider.getRaider());
                Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+(raider.getLoot())));
                raider.getRaider().setLayoutY(0);
                raider.getRaider().setLayoutX(0);
                die(raider);
            }
        MapController.getMap().getRaidersInMap().removeAll(MapController.getMap().getRaidersInMap());
        if(Controller.getController().getWaves().getText().compareTo(MapController.getMap().getAttackWaves()+"/"+MapController.getMap().getAttackWaves())==0)
            MapController.win();
        Controller.getController().getMusic().stop();
        MediaPlayer music=new MediaPlayer(new Media(Main.class.getResource("audio/02. Battle Preparations.mp3").toExternalForm()));
        music.setVolume(Controller.getController().getMusic().getVolume());
        Controller.getController().setMusic(music);
        Controller.getController().getMusic().setCycleCount(MediaPlayer.INDEFINITE);
        Controller.getController().getMusic().play();
    }

    public static String getInfo() {
        return info;
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
                }
            }
    }
}
