package org.example.demo9.exceptions;

public class UserNameExist extends Exception{
    public UserNameExist(String message)
    {
        super("username already exists "+message);
    }
    public UserNameExist()
    {
        this("");
    }
}
