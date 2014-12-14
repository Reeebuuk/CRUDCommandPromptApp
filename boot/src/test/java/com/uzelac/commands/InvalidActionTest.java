package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.constants.MessagesToDispay;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InvalidActionTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void invalidAction() throws ValidationException, XMLException
    {
        String result = commandFactory.enteredCommand("invalidaction").execute();
        assertThat(result, is(MessagesToDispay.INVALID_ACTION));
    }
}
