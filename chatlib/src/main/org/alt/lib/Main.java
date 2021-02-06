package org.alt.lib;

import org.alt.lib.ui.Tray;

import java.awt.*;

public class Main
{
    public static void main(String... args)
    {
        Tray.SafeDisplay("Not Connected", TrayIcon.MessageType.INFO);
    }
}
