package org.alt.lib.ui;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Tray
{
    /*
    * Please consider before using this function if the tray is even supported
    * */
    public void DisplayMessage(String information, MessageType type) throws AWTException
    {
        SystemTray appTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource("poke.png"));
        TrayIcon icon = new TrayIcon(image, "BCP Notification");
        icon.setImageAutoSize(true);
        appTray.add(icon);

        icon.displayMessage("Incoming Message", information, type);
    }

    public static void SafeDisplay(String information, MessageType type)
    {
        if (SystemTray.isSupported())
        {
            try {
                new Tray().DisplayMessage(information, type);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.err.println("[FATAL] SystemTray isn't supported");
        }
    }
}
