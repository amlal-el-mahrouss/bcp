package org.alt.lib.protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
* The Server class
* HANDLES
* everything related to the server
* it is used by the server
* but it is included in the client application if you want to host a server
* */

public class Server
{

    ServerSocket self;
    Port port;
    HashSet<User> clients = new HashSet<>();
    HashMap<User, String> usernames = new HashMap();

    Server(Port port)
    {
        this.port = port;
    }

    Server(Port port, ServerSocket serverSocket)
    {
        this.port = port;
        this.self = serverSocket;
    }

    public String AddClient(Socket user, String username)
    {
        User client = new User(user);

        clients.add(client);
        usernames.put(client, username);

        return client.Username() + " Connected!";
    }

    public String RemoveClient(User client)
    {
        clients.remove(client);
        usernames.remove(client);
        return client.Username() + " Disconnected!";
    }

    /*
    * Send to all channels (using the /broadcast command)
    * excluding the client himself
    * */
    public void BroadcastAll(User excludeClient, String message) throws IOException
    {
        for (User client : clients)
        {
            if (!client.equals(excludeClient)) {
                client.SendMessage("SERVER: " + message, new DataOutputStream(client.ClientID().getOutputStream()));
            }
        }
    }

    /*
     * Send a message to a specific channel
     * excluding the client himself
     * */
    public void SendChannelMessage(User sender, String channel, String message, Set<User> clients)
    {
        assert channel != null;
        assert message != null;

        for (User client : clients)
        {
            if (!client.equals(sender))
            {
                try {
                    client.SendMessage(channel + message, new DataOutputStream(client.ClientID().getOutputStream()));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void SendPrivateMessage(String message, User client)
    {
        try {
            client.SendMessage("Private message from " + client.username + ": " + message, new DataOutputStream(client.ClientID().getOutputStream()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void AcceptConnections() throws IOException
    {
        assert port.IsValid();

        InetSocketAddress infos = new InetSocketAddress(port.Convert());

        self = new ServerSocket();
        self.bind(infos);
        self.accept();
    }

    public void AcceptConnections(String domain) throws IOException
    {
        assert port.IsValid();
        assert domain.isEmpty();

        InetSocketAddress infos = new InetSocketAddress(domain, port.Convert());

        self = new ServerSocket();
        self.bind(infos);
        self.accept();
    }

    /*
     * CloseConnections
     * a brutal way to close the connection
     * shouldn't be used in a w+m1 way
     */

    public void CloseConnections()
    {
        clients.clear();
        try {
            self.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}