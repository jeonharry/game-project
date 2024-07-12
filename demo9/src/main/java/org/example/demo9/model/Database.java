package org.example.demo9.model;

import org.example.demo9.model.spells.*;

import java.sql.*;
import java.util.ArrayList;

public class Database
{
    private static Database database;
    private String url="jdbc:mysql://localhost/game";
    private String userName="root";
    private String password="*JungKook1997";
    private Connection con;
    private ArrayList <Map> maps=new ArrayList<>();
    private Database(){}
    public static Database getDatabase() {
        if(database==null)
            database=new Database();
        return database;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<Map> maps) {
        this.maps = maps;
    }

    public void makeConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,userName,password);
    }
    public void finish()throws Exception
    {
        con.close();
    }
    public long getMaxID(String tableName) throws SQLException {
        String query="SELECT MAX(ID) from "+tableName;
        Statement s=con.prepareStatement(query);
        ResultSet rs=s.executeQuery(query);
        while (rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }
    public long getMaxNum(String tableName) throws SQLException {
        String query="SELECT MAX(num) from "+tableName;
        Statement s=con.prepareStatement(query);
        ResultSet rs=s.executeQuery(query);
        while (rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }
    public void addNewPlayer(Player player) throws SQLException {
        String cmd=String.format("INSERT INTO players (ID,username,password,level,gems) VALUES (%d,'%s','%s',%d,%d)", player.getID(),player.getUsername(),player.getPassword(),player.getLevel(),player.getGems());
        Statement statement=con.prepareStatement(cmd);
        statement.execute(cmd);
    }
    public boolean checkExistence(String username) throws SQLException {
        String cmd="SELECT username FROM players";
        Statement statement=con.prepareStatement(cmd);
        ResultSet rs=statement.executeQuery(cmd);
        while (rs.next())
        {
            if(rs.getString("username").compareTo(username)==0)
                return true;
        }
        return false;
    }
    public boolean checkPassword(String username,String password) throws SQLException {
        String cmd="SELECT password FROM players WHERE username='"+username+"'";
        Statement statement=con.prepareStatement(cmd);
        ResultSet rs=statement.executeQuery(cmd);
        while (rs.next())
        {
            if(rs.getString("password").compareTo(password)==0)
                return true;
        }
        return false;
    }
    public Player getPlayer(String userName,String password) throws SQLException {
        String cmd="SELECT * FROM players WHERE username='"+userName+"' AND password='"+password+"'";
        Statement statement=con.prepareStatement(cmd);
        ResultSet rs=statement.executeQuery(cmd);
        Player player=new Player(userName,password);
        while (rs.next())
        {
            player.setID(rs.getInt("ID"));
            player.setLevel(rs.getInt("level"));
            player.setGems(rs.getInt("gems"));
        }
        cmd="SELECT spell FROM backpacks INNER JOIN players ON backpacks.ID=players.ID WHERE players.ID="+player.getID();
        statement=con.prepareStatement(cmd);
        rs=statement.executeQuery(cmd);
        while (rs.next())
        {
            int amount=0;
            String query="SELECT amount FROM backpacks WHERE ID="+player.getID()+" AND spell='"+rs.getString("spell")+"'";
            Statement s=con.prepareStatement(query);
            ResultSet resultSet=s.executeQuery(query);
            while (resultSet.next())
            {
                amount=resultSet.getInt("amount");
            }
            for(int i=0;i<amount;++i)
            {
                Spell spell=null;
                if(rs.getString("spell").compareTo("heal")==0)
                    spell=new HealSpell();
                else if(rs.getString("spell").compareTo("coin")==0)
                    spell=new CoinSpell();
                else if(rs.getString("spell").compareTo("freeze")==0)
                    spell=new FreezeSpell();
                else if(rs.getString("spell").compareTo("boy")==0)
                    spell=new BoySpell();
                player.getBackpack().add(spell);
            }
        }
        return player;
    }
    public void addSpell(long ID,String spell) throws SQLException {
        long num=getMaxNum("backpacks")+1;
        int amount=0;
        String query="SELECT amount FROM backpacks WHERE ID="+ID+" AND spell='"+spell+"'";
        Statement statement=con.prepareStatement(query);
        ResultSet rs=statement.executeQuery(query);
        boolean exist=false;
        while (rs.next())
        {
            amount=rs.getInt("amount");
            exist=true;
        }
        String cmd;
        if(amount==0 && !exist)
            cmd=String.format("INSERT INTO backpacks (ID,spell,num,amount) VALUES (%d,'%s',%d,%d)",ID,spell,num,amount+1);
        else
            cmd=String.format("UPDATE backpacks SET amount=%d WHERE ID=%d AND spell='%s'",amount+1,ID,spell);
        statement=con.prepareStatement(cmd);
        statement.execute(cmd);
    }
    public void updatePlayerInfoName(String username,long ID) throws SQLException {
        String sqlCmd=String.format("UPDATE players SET username='%s' WHERE ID=%d",username,ID);
        Statement statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
        sqlCmd=String.format("UPDATE logedin SET username='%s' WHERE ID=%d",username,ID);
        statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
    }
    public void updatePlayerInfoPassword(String password,long ID) throws SQLException {
        String sqlCmd=String.format("UPDATE players SET password='%s' WHERE ID=%d",password,ID);
        Statement statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
        sqlCmd=String.format("UPDATE logedin SET password='%s' WHERE ID=%d",password,ID);
        statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
    }
    public void login(Player player) throws SQLException {
        String cmd="DELETE FROM logedin WHERE username='"+"NULL"+"'";
        Statement statement=con.prepareStatement(cmd);
        statement.execute(cmd);
        cmd=String.format("INSERT INTO logedin (ID,username,password,level,gems) VALUES (%d,'%s','%s',%d,%d)", player.getID(),player.getUsername(),player.getPassword(),player.getLevel(),player.getGems());
        statement=con.prepareStatement(cmd);
        statement.execute(cmd);
    }
    public boolean checkLogin() throws SQLException {
        String cmd="SELECT * From logedin";
        Statement statement=con.prepareStatement(cmd);
        ResultSet rs=statement.executeQuery(cmd);
        while (rs.next())
        {
            if(rs.getString("username").compareTo("NULL")!=0)
                return true;
        }
        return false;
    }
    public void logout(long ID) throws SQLException {
        String sqlCmd=String.format("UPDATE logedin SET username='%s' WHERE ID=%d","NULL",ID);
        Statement statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
    }
    public Player getLogedInPlayer() throws SQLException {
        String cmd="SELECT * FROM logedin";
        Statement statement=con.prepareStatement(cmd);
        ResultSet rs=statement.executeQuery(cmd);
        Player player=null;
        while (rs.next())
        {
            player=new Player(rs.getString("username"),rs.getString("password"));
            player.setID(rs.getInt("ID"));
            player.setLevel(rs.getInt("level"));
            player.setGems(rs.getInt("gems"));
        }
        cmd="SELECT spell FROM backpacks INNER JOIN logedin ON backpacks.ID=logedin.ID WHERE logedin.ID="+player.getID();
        statement=con.prepareStatement(cmd);
        rs=statement.executeQuery(cmd);
        while (rs.next())
        {
            int amount=0;
            String query="SELECT amount FROM backpacks WHERE ID="+player.getID()+" AND spell='"+rs.getString("spell")+"'";
            Statement s=con.prepareStatement(query);
            ResultSet resultSet=s.executeQuery(query);
            while (resultSet.next())
            {
                amount=resultSet.getInt("amount");
            }
            for(int i=0;i<amount;++i)
            {
                Spell spell=null;
                if(rs.getString("spell").compareTo("heal")==0)
                    spell=new HealSpell();
                else if(rs.getString("spell").compareTo("coin")==0)
                    spell=new CoinSpell();
                else if(rs.getString("spell").compareTo("freeze")==0)
                    spell=new FreezeSpell();
                else if(rs.getString("spell").compareTo("boy")==0)
                    spell=new BoySpell();
                player.getBackpack().add(spell);
            }
        }
        return player;
    }
    public void updatePlayerInfoGema(long gems,long ID) throws SQLException {
        String sqlCmd=String.format("UPDATE players SET gems='%s' WHERE ID=%d",gems,ID);
        Statement statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
        sqlCmd=String.format("UPDATE logedin SET gems='%s' WHERE ID=%d",gems,ID);
        statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
    }
    public void updatePlayerInfoLevel(long level,long ID) throws SQLException {
        String sqlCmd=String.format("UPDATE players SET level='%s' WHERE ID=%d",level,ID);
        Statement statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
        sqlCmd=String.format("UPDATE logedin SET level='%s' WHERE ID=%d",level,ID);
        statement=con.prepareStatement(sqlCmd);
        statement.execute(sqlCmd);
    }
    public void useSpell(long ID,String spell) throws SQLException {
        int amount=0;
        String query="SELECT amount FROM backpacks WHERE ID="+ID+" AND spell='"+spell+"'";
        Statement statement=con.prepareStatement(query);
        ResultSet rs=statement.executeQuery(query);
        while (rs.next())
        {
            amount=rs.getInt("amount");
        }
        String cmd=String.format("UPDATE backpacks SET amount=%d WHERE ID=%d AND spell='%s'",amount-1,ID,spell);
        statement=con.prepareStatement(cmd);
        statement.execute(cmd);
    }
}
