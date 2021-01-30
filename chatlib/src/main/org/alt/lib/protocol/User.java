package org.alt.lib.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class User
{

    Socket client;
    InetAddress address;
    String username;

    public User(Socket client)
    {
        this.client = client;
    }

    public void NewUsername(String username)
    {
        assert username.isEmpty();

        this.username = username;
    }

    /*
    * connect the clients to a bcp server
    * */
    public void Connect(String ip_address, Port port)
    {
        assert port.IsValid();

        try {
            client = new Socket(ip_address, port.Convert());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        address = client.getInetAddress();

        try
        {
            SendMessage(username, new DataOutputStream(client.getOutputStream()));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void SendMessage(String message, DataOutputStream socketStream) throws IOException
    {
        socketStream.writeUTF(message);
        socketStream.flush();
        socketStream.close();
    }

    public String ReadMessage(DataInputStream socketStream) throws IOException
    {
        String message = socketStream.readUTF();
        socketStream.close();

        return message;
    }

    public String Username()
    {
        return username;
    }

    public Socket ClientID()
    {
        return client;
    }

}
