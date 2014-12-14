package com.uzelac.commands;

import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;

public interface Command
{
    public String execute() throws ValidationException, XMLException;
}
