package org.example.demo9.controller;

import org.example.demo9.exceptions.NotEnoughGems;
import org.example.demo9.exceptions.UserNameExist;
import org.example.demo9.exceptions.WrongPassword;
import org.example.demo9.exceptions.WrongUserName;
import org.example.demo9.model.*;
import org.example.demo9.model.spells.*;

import java.sql.SQLException;

public class PlayerController
{
    private static PlayerController playerController;
    private static Player player;
    private PlayerController(){}
    public static PlayerController getPlayerController() {
        if(playerController==null)
            playerController=new PlayerController();
        return playerController;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerController.player = player;
    }
    public void signUp(String username,String password) throws SQLException, UserNameExist {
        if(Database.getDatabase().checkExistence(username))
            throw new UserNameExist();
        Player newPlayer=new Player(username,password);
        Database.getDatabase().addNewPlayer(newPlayer);
    }
    public boolean login(String username,String password) throws Exception {
        if(!Database.getDatabase().checkExistence(username))
            throw new WrongUserName();
        if(!Database.getDatabase().checkPassword(username, password))
            throw new WrongPassword();
        player=Database.getDatabase().getPlayer(username,password);
        return true;
    }
    public void buySpell(String spellName) throws Exception {
        Spell spell=null;
        if(spellName.compareTo("heal")==0)
            spell=new HealSpell();
        else if(spellName.compareTo("coin")==0)
            spell=new CoinSpell();
        else if(spellName.compareTo("freeze")==0)
            spell=new FreezeSpell();
        else if(spellName.compareTo("boy")==0)
            spell=new BoySpell();
        else
            throw new Exception("spell not found");
        if(player.getGems()<spell.getPrice())
            throw new NotEnoughGems();
        Database.getDatabase().addSpell(player.getID(),spellName,spell.getPrice());
        player.getBackpack().add(spell);
    }
}
