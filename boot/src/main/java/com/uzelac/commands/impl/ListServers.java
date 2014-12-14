package com.uzelac.commands.impl;

import com.uzelac.commands.Command;
import com.uzelac.model.domain.ServerViewModel;
import com.uzelac.service.ServerService;

import java.util.List;

public class ListServers implements Command
{
    private ServerService serverService;

    public ListServers(ServerService serverService)
    {
        this.serverService = serverService;
    }

    @Override
    public String execute()
    {
        List<ServerViewModel> servers = serverService.listServers();
        return toString(servers);
    }

    private String toString(List<ServerViewModel> servers)
    {
        StringBuilder listServerAsString = new StringBuilder();
        listServerAsString.append("List of servers:\n");
        servers.forEach(listServerAsString::append);
        return listServerAsString.toString();
    }
}
