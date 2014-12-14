package com.uzelac.parser;

import com.uzelac.constants.ApplicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class XMLValidator
{
    @Value("${xml.validator.parser}")
    private String xmlValidatorParser;

    @Value("${xml.validator}")
    private String xmlValidator;

    public void validate(String xmlFilePath) throws URISyntaxException, ParserConfigurationException, SAXException, IOException
    {
        if (isSAXParser())
        {
            SAXValidation(xmlFilePath);
        }
        else if (isDOMParser())
        {
            DOMValidation(xmlFilePath);
        }
    }

    private void SAXValidation(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException, URISyntaxException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        if (isXSDValidator())
        {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            factory.setSchema(schemaFactory.newSchema(new StreamSource(ClassLoader.getSystemResourceAsStream(ApplicationConstants.SERVER_SPEC_XSD))));
            factory.setValidating(false);
        }
        else
        {
            factory.setValidating(true);
        }

        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        reader.setErrorHandler(new XMLErrorHandler());
        // If xmlFilePath is null default file should be read from jar file.
        // Only for demonstration, should be removed for prod
        if (xmlFilePath == null)
        {
            reader.parse(new InputSource(ClassLoader.getSystemResource(ApplicationConstants.SERVER_SPEC_XML).toURI().toString()));
        }
        else
        {
            reader.parse(new InputSource(xmlFilePath));
        }
    }

    private void DOMValidation(String xmlFilePath) throws IOException, SAXException, ParserConfigurationException, URISyntaxException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        if (isXSDValidator())
        {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            factory.setSchema(schemaFactory.newSchema(new StreamSource(ClassLoader.getSystemResourceAsStream(ApplicationConstants.SERVER_SPEC_XSD))));
            factory.setValidating(false);
        }
        else
        {
            factory.setValidating(true);
        }

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new XMLErrorHandler());
        // If xmlFilePath is null default file should be read from jar file.
        // Only for demonstration, should be removed for prod
        if (xmlFilePath == null)
        {
            builder.parse(new InputSource(ClassLoader.getSystemResource(ApplicationConstants.SERVER_SPEC_XML).toURI().toString()));
        }
        else
        {
            builder.parse(new InputSource(xmlFilePath));
        }
    }

    private boolean isDOMParser()
    {
        return xmlValidatorParser.equalsIgnoreCase("dom");
    }

    private boolean isSAXParser()
    {
        return xmlValidatorParser.equalsIgnoreCase("sax");
    }

    private boolean isXSDValidator()
    {
        return xmlValidator.equalsIgnoreCase("xsd");
    }
}
