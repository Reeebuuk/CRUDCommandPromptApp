package com.uzelac.model.db;

import com.uzelac.constants.ApplicationConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Server implements Serializable
{
    @Id
    private int id;

    @Column(length = ApplicationConstants.SERVER_NAME_LENGTH)
    private String name;

    public Server()
    {
    }

    public Server(String id, String name)
    {
        this.id = Integer.parseInt(id);
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setId(String id)
    {
        this.id = Integer.parseInt(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Server{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
