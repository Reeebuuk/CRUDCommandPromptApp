package com.uzelac.commands;

public enum Actions
{
    HELP("help"),
    QUIT("quit"),
    ADD_SERVER("addserver"),
    DELETE_SERVER("deleteserver"),
    EDIT_SERVER("editserver"),
    COUNT_SERVERS("countservers"),
    LIST_SERVERS("listservers");

    private String value;

    Actions(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
