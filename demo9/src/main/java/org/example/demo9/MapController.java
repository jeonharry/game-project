package org.example.demo9;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.model.Map;
import org.example.demo9.model.raiders.*;
import org.example.demo9.model.spells.Spell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private Label coins;

    @FXML
    private Label hearts;

    @FXML
    private AnchorPane root;

    @FXML
    private Label waves;

    @FXML
    private ImageView nextWave;
    @FXML
    private ImageView background;

    @FXML
    private Label freezeAmount;

    @FXML
    private Label healAmount;

    @FXML
    private Label boyAmount;

    @FXML
    private Label coinAmount;

    @FXML
    private HBox spells;

    private static Image image;
    private static Map map;
    private static int mapNum;
    private long time;
    private boolean use=false;
    private static boolean lose=false;
    private static Timeline transition;
    private static Timeline end;

    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("GamePage.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @FXML
    void pickBoy(MouseEvent event) {
        try
        {
            Spell spell=PlayerController.getPlayerController().useSpell("boy");
            loadSpells();
            spell.drop();
            use=true;
            time=System.currentTimeMillis();
            spells.setVisible(false);
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void pickCoin(MouseEvent event) {
        try
        {
            Spell spell=PlayerController.getPlayerController().useSpell("coin");
            loadSpells();
            spell.drop();
            use=true;
            time=System.currentTimeMillis();
            spells.setVisible(false);
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void pickFreeze(MouseEvent event) {
        try
        {
            Spell spell=PlayerController.getPlayerController().useSpell("freeze");
            loadSpells();
            spell.drop();
            use=true;
            time=System.currentTimeMillis();
            spells.setVisible(false);
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void pickHeal(MouseEvent event) {
        try
        {
            Spell spell=PlayerController.getPlayerController().useSpell("heal");
            loadSpells();
            spell.drop();
            use=true;
            time=System.currentTimeMillis();
            spells.setVisible(false);
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void showSpells(MouseEvent event) {
        if(use)
        {
            if(System.currentTimeMillis()-time>=6000)
            {
                spells.setVisible(!spells.isVisible());
            }
        }
        else
            spells.setVisible(!spells.isVisible());
    }
    @FXML
    void start(MouseEvent event) {
        nextWave.setVisible(false);
        Timeline timeline=new Timeline();
        Timeline endWave=new Timeline();
        end=endWave;
        transition=timeline;
        endWave.getKeyFrames().add(new KeyFrame(Duration.millis(500),e ->{
            if(map.getRaidersInMap().isEmpty())
            {
                if(!lose)
                    timeline.play();
            }
        }));
        for(int i=1;i<=map.getAttackWaves();++i) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis((i-1)*3000), e ->{
                makeWaves(finalI);
                waves.setText(finalI+"/"+String.valueOf(map.getAttackWaves()));
                if(waves.getText().compareTo(map.getAttackWaves()+"/"+map.getAttackWaves())==0)
                {
                    endWave.stop();
                    timeline.stop();
                }
                timeline.pause();
            }));
        }
        timeline.play();
        endWave.setCycleCount(Animation.INDEFINITE);
        endWave.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().getMusic().stop();
        MediaPlayer music=new MediaPlayer(new Media(Main.class.getResource("audio/02. Battle Preparations.mp3").toExternalForm()));
        music.setVolume(Controller.getController().getMusic().getVolume());
        Controller.getController().setMusic(music);
        Controller.getController().getMusic().setCycleCount(MediaPlayer.INDEFINITE);
        Controller.getController().getMusic().play();
        time=System.currentTimeMillis();
        background.setImage(image);
        nextWave.setLayoutX(map.getStartButton().getFirst());
        nextWave.setLayoutY(map.getStartButton().getLast());
        if(mapNum==2)
        {
            Rotate rotate=new Rotate();
            rotate.setAngle(90);
            nextWave.getTransforms().add(rotate);
        }
        if(mapNum==3)
        {
            Rotate rotate=new Rotate();
            rotate.setAngle(180);
            nextWave.getTransforms().add(rotate);
        }
        if(mapNum==4)
        {
            Rotate rotate=new Rotate();
            rotate.setAngle(-90);
            nextWave.getTransforms().add(rotate);
        }
        Controller.getController().setCoins(coins);
        Controller.getController().setHearts(hearts);
        Controller.getController().setWaves(waves);
        Controller.getController().setMap(root);
        waves.setText("0/"+String.valueOf(map.getAttackWaves()));
        coins.setText(String.valueOf(map.getCoins()));
        loadSpells();
        for(int i=0;i<map.getTowerPlaces().size();i++)
            if(map.getTowerPlaces().get(i)!=null)
            {
                Ellipse tower=new Ellipse(); tower.setLayoutX(map.getTowerPlaces().get(i++)); tower.setLayoutY(map.getTowerPlaces().get(i));
                tower.setFill(Color.TRANSPARENT); tower.setStroke(Color.TRANSPARENT); tower.setRadiusX(30); tower.setRadiusY(23); tower.setCursor(Cursor.HAND);
                root.getChildren().add(tower);
                tower.setOnMouseClicked(event -> {
                    try {
                        if(Controller.getController().getSelectedTower()!=null)
                        {
                            root.getChildren().remove(Controller.getController().getSelectedTower());
                            Controller.getController().setSelectedTower(null);
                        }
                        if(Controller.getController().getPageForUpgrade()!=null)
                        {
                            root.getChildren().remove(Controller.getController().getPageForUpgrade());
                            Controller.getController().setPageForUpgrade(null);
                        }
                        Node child=new FXMLLoader(Main.class.getResource("TowerGenerator.fxml")).load();
                        child.setLayoutX(tower.getLayoutX()-65); child.setLayoutY(tower.getLayoutY()-65);
                        TowerGeneratorController.setMap(map);
                        root.getChildren().add(child);
                        Controller.getController().setSelectedTower(child);
                        Controller.getController().setTowerPlace(tower);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }
                });
            }
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapController.map = map;
    }
    private void makeWaves(int waveNum)
    {
        Controller.getController().getMusic().stop();
        MediaPlayer music=new MediaPlayer(new Media(Main.class.getResource("audio/03. Under Attack.mp3").toExternalForm()));
        music.setVolume(Controller.getController().getMusic().getVolume());
        Controller.getController().setMusic(music);
        Controller.getController().getMusic().setCycleCount(MediaPlayer.INDEFINITE);
        Controller.getController().getMusic().play();
        ArrayList<ArrayList <ArrayList <ArrayList<Double>>>> manyRoads=new ArrayList<>();
        for(int j=0;j<map.getHeroPlaces().size();++j)
            manyRoads.add(makeRoads(j));
        int counter=0;
        for(int i=0;i<waveNum+mapNum && i<5;++i)
        {
            Random random=new Random();
            Raider raider;
            if(map.getHeroPlaces().size()<=1)
            {
                ArrayList <ArrayList <ArrayList<Double>>> roads=manyRoads.getFirst();
                if(counter>=roads.size())
                    counter=0;
                if(random.nextInt(4+(mapNum-1))==0)
                raider=new FlierRaider(45+((mapNum-1)*10),10+((mapNum-1)*30),roads.get(counter));
                else if(random.nextInt(4+(mapNum-1))==1)
                    raider=new FastRaider(40+((mapNum-1)*10),15+((mapNum-1)*30),roads.get(counter));
                else if(random.nextInt(4+(mapNum-1))==2)
                    raider=new DisappearingRaider(35+((mapNum-1)*10),15+((mapNum-1)*30),roads.get(counter));
                else
                    raider=new ShieldRaider(70+((mapNum-1)*10),20+((mapNum-1)*30),roads.get(counter));
                if(counter<roads.size())
                    counter++;
            }
            else
            {
                ArrayList <ArrayList <ArrayList<Double>>> roads=manyRoads.get(random.nextInt(manyRoads.size()));
                if(counter>=roads.size())
                    counter=0;
                if(random.nextInt(4+(mapNum-1))==0)
                    raider=new FlierRaider(45+((mapNum-1)*10),20+((mapNum-1)*30),roads.get(counter));
                else if(random.nextInt(4+(mapNum-1))==1)
                    raider=new FastRaider(40+((mapNum-1)*10),25+((mapNum-1)*30),roads.get(counter));
                else if(random.nextInt(4+(mapNum-1))==2)
                    raider=new DisappearingRaider(35+((mapNum-1)*10),25+((mapNum-1)*30),roads.get(counter));
                else
                    raider=new ShieldRaider(70+((mapNum-1)*10),30+((mapNum-1)*30),roads.get(counter));
                if(counter<roads.size())
                    counter++;
            }
            map.getRaidersInMap().add(raider);
            raider.walk();
        }
    }
    public ArrayList <ArrayList <ArrayList<Double>>> makeRoads(int roadNum)
    {
        ArrayList <ArrayList <ArrayList<Double>>> roads=new ArrayList<>();
        roads.add(map.getHeroPlaces().get(roadNum));
        ArrayList <ArrayList<Double>> road1=new ArrayList<>();
        for(int i=0;i<map.getHeroPlaces().get(roadNum).size();++i)
        {
            ArrayList <Double> point=new ArrayList<>();
            point.add(map.getHeroPlaces().get(roadNum).get(i).getFirst()+5);
            point.add(map.getHeroPlaces().get(roadNum).get(i).getLast()+5);
            road1.add(point);
        }
        roads.add(road1);
        ArrayList <ArrayList<Double>> road2=new ArrayList<>();
        for(int i=0;i<map.getHeroPlaces().get(roadNum).size();++i)
        {
            ArrayList <Double> point=new ArrayList<>();
            point.add(map.getHeroPlaces().get(roadNum).get(i).getFirst()-5);
            point.add(map.getHeroPlaces().get(roadNum).get(i).getLast()-5);
            road2.add(point);
        }
        roads.add(road2);
        return roads;
    }

    private void loadSpells()
    {
        boyAmount.setText(String.valueOf(PlayerController.getPlayerController().getBoySpellAmount()));
        freezeAmount.setText(String.valueOf(PlayerController.getPlayerController().getFreezeSpellAmount()));
        coinAmount.setText(String.valueOf(PlayerController.getPlayerController().getCoinSpellAmount()));
        healAmount.setText(String.valueOf(PlayerController.getPlayerController().getHealSpellAmount()));
    }
    public static void win()
    {
        WinPageController.setMap(map);
        WinPageController.setGems(300L *mapNum);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("WinPage.fxml"));
        try {
            Controller.getController().getMap().getChildren().add(loader.load());
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }
    public static int getMapNum() {
        return mapNum;
    }

    public static void setMapNum(int mapNum) {
        MapController.mapNum = mapNum;
    }

    public static Image getImage() {
        return image;
    }

    public static void setImage(Image image) {
        MapController.image = image;
    }

    public static boolean isLose() {
        return lose;
    }

    public static void setLose(boolean lose) {
        MapController.lose = lose;
    }

    public static Timeline getTransition() {
        return transition;
    }

    public static void setTransition(Timeline transition) {
        MapController.transition = transition;
    }

    public static Timeline getEnd() {
        return end;
    }

    public static void setEnd(Timeline end) {
        MapController.end = end;
    }
}
