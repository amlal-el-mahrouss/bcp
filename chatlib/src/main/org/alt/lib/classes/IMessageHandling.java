package org.alt.lib.classes;

/*
* Purpose :
* MessageHandler is used for message handling
*
* File :
* MessageHandling.java
*
* Abstract Methods:
* OnMessageSend
* OnMessage
* ServerBroadcast
*/

import org.alt.lib.protocol.User;

public interface IMessageHandling
{
    EStates OnMessageSend(User user, String message);
    String OnMessage(User user);
    EStates OnServerBroadcast(User user);
}
