package com.uzelac.commands.impl;

import com.uzelac.InputParameters;
import com.uzelac.commands.Command;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.uzelac.service.ServerService;
import com.uzelac.validator.ParametersValidator;

public class AddServer implements Command
{
    private ServerService serverService;
    private String xmlSourcePath;

    public AddServer(ServerService serverService, InputParameters inputParameters) throws ValidationException
    {
        if (inputParameters.hasParameters())
        {
            ParametersValidator.rightNumberOfParameters(inputParameters.getLength(), 1);
            this.xmlSourcePath = inputParameters.getFirstParam();

        }
        this.serverService = serverService;
    }

    @Override
    public String execute() throws ValidationException, XMLException
    {
        return serverService.addServer(xmlSourcePath).toString();
    }
}
