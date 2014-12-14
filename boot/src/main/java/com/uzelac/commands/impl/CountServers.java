package com.uzelac.commands.impl;

import com.uzelac.commands.Command;
import com.uzelac.service.ServerService;

public class CountServers implements Command
{
    private ServerService serverService;

    public CountServers(ServerService serverService)
    {
        this.serverService = serverService;
    }

    @Override
    public String execute()
    {
        return String.valueOf(serverService.countServers());
    }
}
