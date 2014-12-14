package com.uzelac.commands;

import com.uzelac.InputParameters;
import com.uzelac.commands.impl.*;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandFactory
{
    @Autowired
    private ServerService serverService;

    public Command enteredCommand(String inputString) throws ValidationException
    {
        InputParameters inputParameters = new InputParameters(inputString);
        String action = inputParameters.getAction();

        if (action.equals(Actions.HELP.getValue()))
        {
            return new Help();
        }
        else if (action.equals(Actions.QUIT.getValue()))
        {
            return new Quit();
        }
        else if (action.equals(Actions.COUNT_SERVERS.getValue()))
        {
            return new CountServers(serverService);
        }
        else if (action.equals(Actions.ADD_SERVER.getValue()))
        {
            return new AddServer(serverService, inputParameters);
        }
        else if (action.equals(Actions.DELETE_SERVER.getValue()))
        {
            return new DeleteServer(serverService, inputParameters);
        }
        else if (action.equals(Actions.EDIT_SERVER.getValue()))
        {
            return new EditServer(serverService, inputParameters);
        }
        else if (action.equals(Actions.LIST_SERVERS.getValue()))
        {
            return new ListServers(serverService);
        }

        return new InvalidAction();
    }
}
