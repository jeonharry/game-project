package org.example.demo9.exceptions;

public class WrongPassword extends Exception{
    public WrongPassword(String message)
    {
        super("password isn't correct "+message);
    }
    public WrongPassword()
    {
        this("");
    }
}
