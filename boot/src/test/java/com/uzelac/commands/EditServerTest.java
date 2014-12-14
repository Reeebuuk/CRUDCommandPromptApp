package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.uzelac.repository.ServerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EditServerTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void editserver_editExisting() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();
        assertThat(serverRepository.count(), is(1l));

        String result = commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 2 hoho").execute();

        assertThat(result, is("Server{id='2', name='hoho'}"));

        assertThat(serverRepository.count(), is(1l));
    }

    @Test(expected = ValidationException.class)
    public void editserver_editNonExisting() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();
        commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 3 hoho").execute();
    }

    @Test(expected = ValidationException.class)
    public void editserver_missingParameter() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 3").execute();
    }

    @Test(expected = ValidationException.class)
    public void editserver_tooMuchParameters() throws ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.EDIT_SERVER.getValue() + " 3 lala m").execute();
    }
}
