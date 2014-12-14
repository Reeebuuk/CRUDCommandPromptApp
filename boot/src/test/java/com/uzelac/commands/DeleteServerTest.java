package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.constants.MessagesToDispay;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.uzelac.repository.ServerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteServerTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Autowired
    private ServerRepository serverRepository;

    @Before
    public void testSetup() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();
        assertThat(serverRepository.count(), is(1l));
    }

    @Test
    public void deleteserver_existingServer() throws ValidationException, XMLException
    {
        String result = commandFactory.enteredCommand(Actions.DELETE_SERVER.getValue() + " 2").execute();
        assertThat(result, is(MessagesToDispay.SERVER_DELETED));

        assertThat(serverRepository.count(), is(0l));
    }

    @Test(expected = ValidationException.class)
    public void deleteserver_nonExistingServer() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.DELETE_SERVER.getValue() + " 3").execute();
    }

    @Test(expected = ValidationException.class)
    public void deleteserver_missingParameter() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.DELETE_SERVER.getValue() + " ").execute();
    }

    @Test(expected = ValidationException.class)
    public void deleteserver_invalidParameter() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.DELETE_SERVER.getValue() + " 2v").execute();
    }
}
