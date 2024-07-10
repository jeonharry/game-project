package org.example.demo9.exceptions;

public class NoSpell extends Exception
{
    public NoSpell(String message)
    {
        super("you dont have this spell "+message);
    }
    public NoSpell()
    {
        this("");
    }
}
