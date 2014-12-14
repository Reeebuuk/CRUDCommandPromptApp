package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.constants.MessagesToDispay;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HelpTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void help() throws ValidationException, XMLException
    {
        String result = commandFactory.enteredCommand(Actions.HELP.getValue()).execute();
        assertThat(result, is(MessagesToDispay.HELP));
    }
}
