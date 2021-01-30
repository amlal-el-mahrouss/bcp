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

public interface IMessageHandling
{
    EStates OnMessageSend();
    EStates OnMessage();
    EStates ServerBroadcast();
}
