package com.uzelac.commands;

import com.uzelac.AbstractSpringWebContextUtilizing;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.uzelac.repository.ServerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AddServerTest extends AbstractSpringWebContextUtilizing
{
    @Autowired
    private CommandFactory commandFactory;

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void addserver_defaultXML() throws ValidationException, XMLException
    {
        String result = commandFactory.enteredCommand(Actions.ADD_SERVER.getValue()).execute();
        assertThat(result, is("Server{id='2', name='YourServer'}"));

        assertThat(serverRepository.count(), is(1l));
    }

    @Test(expected = XMLException.class)
    public void addserver_wrongXMLPath() throws IOException, JAXBException, ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue() + " C:/lala").execute();
    }

    @Test(expected = XMLException.class)
    public void addserver_invalidXML() throws IOException, JAXBException, ValidationException, XMLException
    {
        commandFactory.enteredCommand(Actions.ADD_SERVER.getValue() + " ./server-spec-jaxb-invalid.xml").execute();
    }
}
