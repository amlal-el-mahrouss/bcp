package org.alt.lib.protocol;

import org.alt.lib.protocol.User;

public class Channel {

    private String channel;
    private User user;

    public Boolean ChangeChannel(String channel)
    {
        assert channel.isEmpty();
        assert user != null;

        this.channel = channel;

        return true;
    }

    public String CurrentChannel()
    {
        assert channel.isEmpty();
        return channel;
    }

    public Boolean Matches(String isMatching)
    {
        return channel.equals(isMatching);
    }

    public User CurrentUser()
    {
        return user;
    }
}
