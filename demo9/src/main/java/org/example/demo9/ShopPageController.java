package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.model.spells.BoySpell;
import org.example.demo9.model.spells.CoinSpell;
import org.example.demo9.model.spells.FreezeSpell;
import org.example.demo9.model.spells.HealSpell;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopPageController implements Initializable {

    @FXML
    private Label boyAmount;

    @FXML
    private Label coinAmount;

    @FXML
    private Label freezeAmount;

    @FXML
    private Label gems;

    @FXML
    private Label healAmount;

    @FXML
    private Label price;

    @FXML
    private Label spellInfo;

    @FXML
    private Label spellName;

    @FXML
    void buy(MouseEvent event) {
        try
        {
            if(spellName.getText().compareTo("Little Boy")==0)
                PlayerController.getPlayerController().buySpell("boy");
            else if(spellName.getText().compareTo("Coin Bag")==0)
                PlayerController.getPlayerController().buySpell("coin");
            else if(spellName.getText().compareTo("Freeze Spell")==0)
                PlayerController.getPlayerController().buySpell("freeze");
            else if(spellName.getText().compareTo("Heal Jar")==0)
                PlayerController.getPlayerController().buySpell("heal");
            loadPage();
            Controller.getController().getGems().setText(String.valueOf(PlayerController.getPlayer().getGems()));
        }catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void finish(MouseEvent event) {
        Controller.getController().getGamePage().getChildren().removeLast();
    }

    @FXML
    void selectBoy(MouseEvent event) {
        spellName.setText("Little Boy");
        spellInfo.setText(BoySpell.getInfo());
        price.setText(String.valueOf(BoySpell.getPrice()));
    }

    @FXML
    void selectCoin(MouseEvent event) {
        spellName.setText("Coin Bag");
        spellInfo.setText(CoinSpell.getInfo());
        price.setText(String.valueOf(CoinSpell.getPrice()));
    }

    @FXML
    void selectFreeze(MouseEvent event) {
        spellName.setText("Freeze Spell");
        spellInfo.setText(FreezeSpell.getInfo());
        price.setText(String.valueOf(FreezeSpell.getPrice()));
    }

    @FXML
    void selectHeal(MouseEvent event) {
        spellName.setText("Heal Jar");
        spellInfo.setText(HealSpell.getInfo());
        price.setText(String.valueOf(HealSpell.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spellInfo.setText(BoySpell.getInfo());
        loadPage();
    }
    public void loadPage()
    {
        gems.setText(String.valueOf(PlayerController.getPlayer().getGems()));
        boyAmount.setText(String.valueOf(PlayerController.getPlayerController().getBoySpellAmount()));
        freezeAmount.setText(String.valueOf(PlayerController.getPlayerController().getFreezeSpellAmount()));
        coinAmount.setText(String.valueOf(PlayerController.getPlayerController().getCoinSpellAmount()));
        healAmount.setText(String.valueOf(PlayerController.getPlayerController().getHealSpellAmount()));
    }
}
