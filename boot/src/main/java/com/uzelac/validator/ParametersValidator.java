package com.uzelac.validator;

import com.uzelac.constants.ApplicationConstants;
import com.uzelac.model.exception.ValidationException;

import static java.lang.Integer.parseInt;

public class ParametersValidator
{
    public static void serverValid(String serverId, String serverName) throws ValidationException
    {
        validateServerId(serverId);
        validateServerName(serverName);
    }

    public static void rightNumberOfParameters(int parameterNumber, int expectedParameterNumber) throws ValidationException
    {
        if (parameterNumber - 1 != expectedParameterNumber)
        {
            throw new ValidationException("Invalid number of parameters!");
        }
    }

    public static void validateServerId(String serverId) throws ValidationException
    {
        try
        {
            parseInt(serverId);
        }
        catch (NumberFormatException e)
        {
            throw new ValidationException("Server id value is not a number!");
        }
    }

    public static void validateServerName(String serverName) throws ValidationException
    {
        if (serverName.length() > ApplicationConstants.SERVER_NAME_LENGTH)
        {
            throw new ValidationException("Server name is too long. Max length is " + ApplicationConstants.SERVER_NAME_LENGTH + "! ");
        }
    }
}
