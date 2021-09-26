package com.se21.calbot.factories;

import com.se21.calbot.ClientManager.ClientManager;
import com.se21.calbot.ClientManager.Discord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class clientFactory {

    @Autowired
    Discord discordcleint;

    public ClientManager getClient(String channel) {

        switch(channel) {
            case "Discord":
                return discordcleint;
            default:
                return null;
        }

    }
}
