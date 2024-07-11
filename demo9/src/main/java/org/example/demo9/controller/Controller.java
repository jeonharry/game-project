package org.example.demo9.controller;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Ellipse;


public class Controller
{
    private static Controller controller;
    private StackPane startPageRoot;
    private StackPane loginStackPane;
    private Label logedInUsername;
    private ImageView logout_btn;
    private AnchorPane gamePage;
    private Node selectedTower=null;
    private AnchorPane map;
    private Label gems;
    private Label coins;
    private Label hearts;
    private Label waves;
    private Ellipse towerPlace;
    private Node selectedTowerUpgrade;
    private Node pageForUpgrade=null;
    private MediaPlayer music=null;
    private Controller(){}

    public static Controller getController() {
        if(controller==null)
            controller=new Controller();
        return controller;
    }

    public StackPane getStartPageRoot() {
        return startPageRoot;
    }

    public void setStartPageRoot(StackPane startPageRoot) {
        this.startPageRoot = startPageRoot;
    }

    public StackPane getLoginStackPane() {
        return loginStackPane;
    }

    public void setLoginStackPane(StackPane loginStackPane) {
        this.loginStackPane = loginStackPane;
    }

    public Label getLogedInUsername() {
        return logedInUsername;
    }

    public void setLogedInUsername(Label logedInUsername) {
        this.logedInUsername = logedInUsername;
    }

    public ImageView getLogout_btn() {
        return logout_btn;
    }

    public void setLogout_btn(ImageView logout_btn) {
        this.logout_btn = logout_btn;
    }

    public AnchorPane getGamePage() {
        return gamePage;
    }

    public void setGamePage(AnchorPane gamePage) {
        this.gamePage = gamePage;
    }

    public Label getGems() {
        return gems;
    }

    public void setGems(Label gems) {
        this.gems = gems;
    }

    public void setSelectedTower(Node selectedTower) {
        this.selectedTower = selectedTower;
    }

    public Node getSelectedTower() {
        return selectedTower;
    }

    public void setMap(AnchorPane map) {
        this.map = map;
    }

    public AnchorPane getMap() {
        return map;
    }

    public Label getCoins() {
        return coins;
    }

    public void setCoins(Label coins) {
        this.coins = coins;
    }

    public Label getHearts() {
        return hearts;
    }

    public void setHearts(Label hearts) {
        this.hearts = hearts;
    }

    public Label getWaves() {
        return waves;
    }

    public void setWaves(Label waves) {
        this.waves = waves;
    }

    public Ellipse getTowerPlace() {
        return towerPlace;
    }

    public void setTowerPlace(Ellipse towerPlace) {
        this.towerPlace = towerPlace;
    }

    public Node getSelectedTowerUpgrade() {
        return selectedTowerUpgrade;
    }

    public void setSelectedTowerUpgrade(Node selectedTowerUpgrade) {
        this.selectedTowerUpgrade = selectedTowerUpgrade;
    }

    public Node getPageForUpgrade() {
        return pageForUpgrade;
    }

    public void setPageForUpgrade(Node pageForUpgrade) {
        this.pageForUpgrade = pageForUpgrade;
    }

    public MediaPlayer getMusic() {
        return music;
    }

    public void setMusic(MediaPlayer music) {
        this.music = music;
    }
}
