package com.uzelac.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler implements ErrorHandler
{
    private static final Logger log = LoggerFactory.getLogger(XMLErrorHandler.class);

    public void warning(SAXParseException e)
    {
        log.debug("Warning at line " + e.getLineNumber() + ": ");
        log.debug(e.getMessage());
    }

    public void error(SAXParseException e)
    {
        log.debug("Error at line " + e.getLineNumber() + ": ");
        log.debug(e.getMessage());
    }

    public void fatalError(SAXParseException e)
    {
        log.debug("Fatal error at line " + e.getLineNumber() + ": ");
        log.debug(e.getMessage());
    }
}
