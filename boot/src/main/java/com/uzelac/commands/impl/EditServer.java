package com.uzelac.commands.impl;

import com.uzelac.InputParameters;
import com.uzelac.commands.Command;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.service.ServerService;
import com.uzelac.validator.ParametersValidator;

public class EditServer implements Command
{
    private ServerService serverService;
    private String serverId;
    private String serverName;

    public EditServer(ServerService serverService, InputParameters inputParameters) throws ValidationException
    {
        ParametersValidator.rightNumberOfParameters(inputParameters.getLength(), 2);
        ParametersValidator.serverValid(inputParameters.getFirstParam(), inputParameters.getSecondParam());

        this.serverService = serverService;
        this.serverId = inputParameters.getFirstParam();
        this.serverName = inputParameters.getSecondParam();
    }

    @Override
    public String execute() throws ValidationException
    {
        return serverService.editServer(serverId, serverName).toString();
    }
}