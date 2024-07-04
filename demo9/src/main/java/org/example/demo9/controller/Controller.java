package org.example.demo9.controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class Controller
{
    private static Controller controller;
    private StackPane startPageRoot;
    private StackPane loginStackPane;
    private Label logedInUsername;
    private ImageView logout_btn;
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
}
