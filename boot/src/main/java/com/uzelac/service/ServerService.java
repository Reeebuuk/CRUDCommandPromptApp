package com.uzelac.service;

import com.uzelac.model.domain.ServerViewModel;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;

import java.util.List;

public interface ServerService
{

    /**
     * Reads XML file located on path specified in xmlSourcePath.
     * Saves it to the DB
     *
     * @param xmlSourcePath
     * @return Saved server object
     */
    public ServerViewModel addServer(String xmlSourcePath) throws ValidationException, XMLException;

    /**
     * Edits server name of server with server id specified in serverId
     *
     * @param serverId
     * @param serverName
     * @return Edited server object, throws ValidationException if server doesn't exist
     */
    public ServerViewModel editServer(String serverId, String serverName) throws ValidationException;

    /**
     * Deletes server with specified serverId from DB
     *
     * @param serverId Deletes the server from DB, throws ValidationException if server doesn't exit
     */
    public void deleteServer(String serverId) throws ValidationException;

    /**
     * Count number of server in DB
     *
     * @return Number of servers in DB
     */
    public long countServers();

    /**
     * @return List of all servers from DB
     */
    public List<ServerViewModel> listServers();
}
