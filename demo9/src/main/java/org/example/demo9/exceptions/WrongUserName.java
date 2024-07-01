package org.example.demo9.exceptions;

public class WrongUserName extends Exception{
    public WrongUserName(String message)
    {
        super("username doesn't exists "+message);
    }
    public WrongUserName()
    {
        this("");
    }
}
