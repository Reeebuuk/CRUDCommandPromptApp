package com.uzelac.constants;

public class MessagesToDispay
{
    public static final String SERVER_DELETED = "Server deleted!";
    public static final String INVALID_ACTION = "Invalid action!";
    public static final String HELP = "help - to display this message\n" +
            "countServers - to display the current number of servers present\n" +
            "addServer {pathToXML} - to read the info from XML and persist to DB\n" +
            "editServer {serverId} {serverName} - to change the name of a server identified by id (takes 2 additional args - the id and the new name)\n" +
            "deleteServer {serverId} - to delete a server (takes one more arg - the id of the server to delete)\n" +
            "listServers - to display details of all servers\n";
}
