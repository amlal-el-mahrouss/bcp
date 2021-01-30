package org.alt.lib.socket.serverside;


import org.alt.lib.protocol.Port;
import org.alt.lib.protocol.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {

    ServerSocket self;
    Port port;
    Set<User> clients = new HashSet<>();

    Server(Port port, ServerSocket serverSocket)
    {
        this.port = port;
        this.self = serverSocket;
    }

    public String AddClient(User client)
    {
        clients.add(client);
        return client.Username() + " Connected!";
    }

    public String RemoveClient(User client)
    {
        clients.remove(client);
        return client.Username() + " Disconnected!";
    }

    public void BroadcastAll(User excludeClient, String message) throws IOException
    {
        for (User client: clients)
        {
            if (!client.equals(excludeClient)) {
                client.SendMessage("SERVER: " + message, new DataOutputStream(client.ClientID().getOutputStream()));
            }
        }
    }

    public void SendChannelMessage(String channel, String message, Set<User> clients) throws IOException
    {
        for (User client : clients)
        {
            client.SendMessage(channel + message, new DataOutputStream(client.ClientID().getOutputStream()));
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

    public void AddUser(Socket user) throws IOException
    {
        user = self.accept();
        User client = new User(user);
        clients.add(client);
    }
}