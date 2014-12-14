package com.uzelac.model.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverType", propOrder = {
        "id",
        "name"
})
@XmlRootElement(name = "server")
public class ServerSpecs
{
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
        return "ServerSpecs{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
