package org.example.demo9.exceptions;

public class NotEnoughLevel extends Exception
{
    public NotEnoughLevel(String message)
    {
        super("not enough level for upgrade "+message);
    }
    public NotEnoughLevel()
    {
        this("");
    }
}
