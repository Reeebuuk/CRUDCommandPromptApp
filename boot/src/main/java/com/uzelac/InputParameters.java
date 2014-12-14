package com.uzelac;

import com.uzelac.constants.ApplicationConstants;

public class InputParameters
{
    private String[] parsedParams;

    public InputParameters(String input)
    {
        parsedParams = input.split(ApplicationConstants.INPUT_PARAMS_DELIMITER);;
    }

    public int getLength()
    {
        return parsedParams.length;
    }

    public boolean hasParameters()
    {
        return parsedParams.length > 1;
    }

    public String getAction()
    {
        return parsedParams[0];
    }

    public String getFirstParam()
    {
        return parsedParams[1];
    }

    public String getSecondParam()
    {
        return parsedParams[2];
    }
}
