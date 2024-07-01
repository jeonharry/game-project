package org.example.demo9.exceptions;

public class NotEnoughGems extends Exception{
    public NotEnoughGems(String message)
    {
        super("not enough gems "+message);
    }
    public NotEnoughGems()
    {
        this("");
    }
}
