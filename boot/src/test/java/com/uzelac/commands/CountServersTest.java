package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class CountServersTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void countServers_one() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();

        assertEquals("1", commandFactory.enteredCommand(Actions.COUNT_SERVERS.getValue()).execute());
    }

    @Test
    public void countServers_zero() throws ValidationException, XMLException
    {
        assertEquals("0", commandFactory.enteredCommand(Actions.COUNT_SERVERS.getValue()).execute());
    }
}
