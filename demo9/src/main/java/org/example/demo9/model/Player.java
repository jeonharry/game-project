package org.example.demo9.model;

import org.example.demo9.model.spells.Spell;

import java.sql.SQLException;
import java.util.ArrayList;

public class Player
{
    private String username;
    private String password;
    private long ID;
    private long level=1;
    private long gems;
    private ArrayList <Spell> backpack=new ArrayList<>();
    public Player(String username,String password) throws SQLException {
        this.username=username;
        this.password=password;
        this.ID= Database.getDatabase().getMaxID("players")+1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getGems() {
        return gems;
    }

    public void setGems(long gems) {
        this.gems = gems;
    }

    public ArrayList<Spell> getBackpack() {
        return backpack;
    }

    public void setBackpack(ArrayList<Spell> backpack) {
        this.backpack = backpack;
    }
}
