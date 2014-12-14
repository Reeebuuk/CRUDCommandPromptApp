package com.uzelac.commands.impl;

import com.uzelac.commands.Command;
import com.uzelac.model.exception.QuitException;

public class Quit implements Command
{
    @Override
    public String execute()
    {
        throw new QuitException();
    }
}
