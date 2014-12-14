package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListServersTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void listServers_one() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();

        String result = commandFactory.enteredCommand(Actions.LIST_SERVERS.getValue()).execute();
        assertThat(result, is("List of servers:\nServer{id='2', name='YourServer'}"));
    }

    @Test
    public void listServers_zero() throws ValidationException, XMLException
    {
        String result = commandFactory.enteredCommand(Actions.LIST_SERVERS.getValue()).execute();
        assertThat(result, is("List of servers:\n"));
    }
}
