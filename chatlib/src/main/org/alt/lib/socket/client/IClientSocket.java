package org.alt.lib.socket.client;

import org.alt.lib.ESocketStates;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;

public interface IClientSocket {
    void Send(DataOutputStream dOut, String message);
    ESocketStates IsReady();
    void Connect() throws SocketException;
    void CloseConnection() ;
}
