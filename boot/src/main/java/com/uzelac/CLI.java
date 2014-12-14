package com.uzelac;

import com.uzelac.commands.CommandFactory;
import com.uzelac.commands.impl.Help;
import com.uzelac.helper.Output;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CLI {

    @Autowired
    private CommandFactory commandFactory;

    public void readInput() throws IOException
    {
        Output.show(new Help().execute());
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            String option = in.readLine().toLowerCase().trim();

            String result;
            try
            {
                result = commandFactory.enteredCommand(option).execute();
            }
            catch (ValidationException | XMLException e)
            {
                result = e.getMessage();
            }

            Output.show(result);
        }
    }
}
