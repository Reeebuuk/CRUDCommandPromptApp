package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.commands.impl.*;
import com.uzelac.model.exception.ValidationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CommandFactoryTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void invalid_emptyName() throws ValidationException
    {
        Command command = commandFactory.enteredCommand("");
        assertThat(command.getClass().toString(), is(InvalidAction.class.toString()));
    }

    @Test
    public void invalid_wrongAction() throws ValidationException
    {
        Command command = commandFactory.enteredCommand("addservers");
        assertThat(command.getClass().toString(), is(InvalidAction.class.toString()));
    }

    @Test
    public void addserver_withoutParameters() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.ADD_SERVER.getValue());
        assertThat(command.getClass().toString(), is(AddServer.class.toString()));
    }

    @Test
    public void addserver_withParameters() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.ADD_SERVER.getValue() + " c:/lala.xml");
        assertThat(command.getClass().toString(), is(AddServer.class.toString()));
    }

    @Test(expected = ValidationException.class)
    public void invalid_editserver_withoutParameters() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue());
        assertThat(command.getClass().toString(), is(InvalidAction.class.toString()));
    }

    @Test(expected = ValidationException.class)
    public void invalid_editserver_oneParameter() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 1");
        assertThat(command.getClass().toString(), is(InvalidAction.class.toString()));
    }

    @Test
    public void editserver_twoParameter() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 1 ime");
        assertThat(command.getClass().toString(), is(EditServer.class.toString()));
    }

    @Test
    public void countservers() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.COUNT_SERVERS.getValue());
        assertThat(command.getClass().toString(), is(CountServers.class.toString()));
    }

    @Test
    public void listservers() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.LIST_SERVERS.getValue());
        assertThat(command.getClass().toString(), is(ListServers.class.toString()));
    }

    @Test
    public void help() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.HELP.getValue());
        assertThat(command.getClass().toString(), is(Help.class.toString()));
    }

    @Test
    public void quit() throws ValidationException
    {
        Command command = commandFactory.enteredCommand(Actions.QUIT.getValue());
        assertThat(command.getClass().toString(), is(Quit.class.toString()));
    }
}
