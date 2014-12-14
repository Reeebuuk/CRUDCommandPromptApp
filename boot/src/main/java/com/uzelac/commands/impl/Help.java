package com.uzelac.commands.impl;

import com.uzelac.commands.Command;
import com.uzelac.constants.MessagesToDispay;

public class Help implements Command
{
    @Override
    public String execute()
    {
        return MessagesToDispay.HELP;
    }
}
