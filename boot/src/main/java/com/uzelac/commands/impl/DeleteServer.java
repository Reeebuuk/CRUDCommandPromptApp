package com.uzelac.commands.impl;

import com.uzelac.InputParameters;
import com.uzelac.commands.Command;
import com.uzelac.constants.MessagesToDispay;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.service.ServerService;
import com.uzelac.validator.ParametersValidator;

public class DeleteServer implements Command
{
    private ServerService serverService;
    private String serverId;

    public DeleteServer(ServerService serverService, InputParameters inputParameters) throws ValidationException
    {
        ParametersValidator.rightNumberOfParameters(inputParameters.getLength(), 1);
        ParametersValidator.validateServerId(inputParameters.getFirstParam());

        this.serverService = serverService;
        this.serverId = inputParameters.getFirstParam();
    }

    @Override
    public String execute() throws ValidationException
    {
        serverService.deleteServer(serverId);
        return MessagesToDispay.SERVER_DELETED;
    }
}