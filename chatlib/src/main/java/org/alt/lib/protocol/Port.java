package org.alt.lib.protocol;

public class Port {

    String port;

    Port(String port)
    {
        this.port = port;
    }

    public Boolean IsValid()
    {
        return port.matches("[0-5]");
    }

    public Integer Convert()
    {
        return Integer.valueOf(port);
    }
}
