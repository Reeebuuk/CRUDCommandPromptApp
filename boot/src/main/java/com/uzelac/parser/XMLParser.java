package com.uzelac.parser;

import com.uzelac.constants.ApplicationConstants;
import com.uzelac.model.exception.XMLException;
import com.uzelac.model.xml.ServerSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;

@Component
public class XMLParser
{
    private static final Logger log = LoggerFactory.getLogger(XMLParser.class);

    @Autowired
    private XMLValidator xmlValidator;

    @Value("${xml.validation}")
    private boolean xmlValidation;

    public ServerSpecs getServerSpecsFromXML(String xmlFilePath) throws XMLException
    {
        try
        {
            if (xmlValidation)
            {
                xmlValidator.validate(xmlFilePath);
            }

            JAXBContext context = JAXBContext.newInstance(ServerSpecs.class);
            Unmarshaller um = context.createUnmarshaller();

            ServerSpecs serverSpecs;
            // If xmlFilePath is null default file should be read from jar file.
            // Only for demonstration, should be removed for prod
            if (xmlFilePath == null)
            {
                serverSpecs = (ServerSpecs) um.unmarshal(
                        ClassLoader.getSystemResourceAsStream(ApplicationConstants.SERVER_SPEC_XML));
            }
            else
            {
                serverSpecs = (ServerSpecs) um.unmarshal(new FileReader(xmlFilePath));
            }

            return serverSpecs;
        }
        catch (Exception e)
        {
            log.debug(e.toString());
            throw new XMLException("Error reading XML file.");
        }
    }
}
