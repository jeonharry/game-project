package org.example.demo9.controller;

import org.example.demo9.exceptions.*;
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
        newPlayer.setGems(400);
        Database.getDatabase().addNewPlayer(newPlayer);
    }
    public boolean login(String username,String password) throws Exception {
        if(!Database.getDatabase().checkExistence(username))
            throw new WrongUserName();
        if(!Database.getDatabase().checkPassword(username, password))
            throw new WrongPassword();
        player=Database.getDatabase().getPlayer(username,password);
        Database.getDatabase().login(player);
        return true;
    }
    public void buySpell(String spellName) throws Exception {
        Spell spell=null;
        int price=0;
        if(spellName.compareTo("heal")==0)
        {
            spell=new HealSpell();
            price=HealSpell.getPrice();
        }
        else if(spellName.compareTo("coin")==0)
        {
            spell=new CoinSpell();
            price=CoinSpell.getPrice();
        }
        else if(spellName.compareTo("freeze")==0)
        {
            spell=new FreezeSpell();
            price=FreezeSpell.getPrice();
        }
        else if(spellName.compareTo("boy")==0)
        {
            spell=new BoySpell();
            price=BoySpell.getPrice();
        }
        else
            throw new Exception("spell not found");
        if(player.getGems()<price)
            throw new NotEnoughGems();
        player.setGems(player.getGems()-price);
        Database.getDatabase().addSpell(player.getID(),spellName);
        player.getBackpack().add(spell);
        Database.getDatabase().updatePlayerInfoGema(player.getGems(),player.getID());
    }
    public void updateName(String username) throws SQLException, UserNameExist {
        if(Database.getDatabase().checkExistence(username))
            throw new UserNameExist();
        player.setUsername(username);
        Database.getDatabase().updatePlayerInfoName(username, player.getID());
    }
    public void updatePassword(String password) throws SQLException, UserNameExist {
        player.setPassword(password);
        Database.getDatabase().updatePlayerInfoPassword(password, player.getID());
    }
    public void logout() throws SQLException {
        Database.getDatabase().logout(player.getID());
        player=null;
    }
    public boolean checkLogin() throws SQLException {
        if(Database.getDatabase().checkLogin())
        {
            player=Database.getDatabase().getLogedInPlayer();
            return true;
        }
        return false;
    }
    public long getBoySpellAmount()
    {
        long counter = 0;
        for(Spell temp: getPlayer().getBackpack())
            if(temp!=null && temp instanceof BoySpell)
                counter++;
        return counter;
    }
    public long getFreezeSpellAmount()
    {
        long counter = 0;
        for(Spell temp: getPlayer().getBackpack())
            if(temp instanceof FreezeSpell)
                counter++;
        return counter;
    }
    public long getHealSpellAmount()
    {
        long counter = 0;
        for(Spell temp: getPlayer().getBackpack())
            if(temp instanceof HealSpell)
                counter++;
        return counter;
    }
    public long getCoinSpellAmount()
    {
        long counter = 0;
        for(Spell temp: getPlayer().getBackpack())
            if(temp instanceof CoinSpell)
                counter++;
        return counter;
    }
    public void updateLevel(long level) throws SQLException {
        player.setLevel(level);
        Database.getDatabase().updatePlayerInfoLevel(level, player.getID());
    }
    public Spell useSpell(String spellName) throws Exception {
        for(Spell temp:player.getBackpack())
        {
            if(temp!=null)
            {
                if(temp instanceof HealSpell && spellName.compareTo("heal")==0)
                {
                    Database.getDatabase().useSpell(player.getID(),spellName);
                    player.getBackpack().remove(temp);
                    return temp;
                }
                else if(temp instanceof CoinSpell && spellName.compareTo("coin")==0)
                {
                    Database.getDatabase().useSpell(player.getID(),spellName);
                    player.getBackpack().remove(temp);
                    return temp;
                }
                else if(temp instanceof FreezeSpell && spellName.compareTo("freeze")==0)
                {
                    Database.getDatabase().useSpell(player.getID(),spellName);
                    player.getBackpack().remove(temp);
                    return temp;
                }
                else if(temp instanceof BoySpell && spellName.compareTo("boy")==0)
                {
                    Database.getDatabase().useSpell(player.getID(),spellName);
                    player.getBackpack().remove(temp);
                    return temp;
                }
            }
        }
        throw new NoSpell();
    }
    public void updateGems(long gems) throws SQLException {
        player.setGems(player.getGems()+gems);
        Database.getDatabase().updatePlayerInfoGema(player.getGems(),player.getID());
    }
}
