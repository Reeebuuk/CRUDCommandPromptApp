package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.model.exception.QuitException;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class QuitTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test(expected = QuitException.class)
    public void quit() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.QUIT.getValue()).execute();
    }
}
