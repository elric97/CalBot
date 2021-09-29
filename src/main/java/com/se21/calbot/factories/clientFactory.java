package com.se21.calbot.factories;

import com.se21.calbot.interfaces.ClientManager;
import com.se21.calbot.ClientManager.Discord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory class to generate object for type of client used by user(eg: discord, telegram etc).
 * This class is one of responsible to help achieve factory pattern in project.
 */
@Component
public class clientFactory {

    @Autowired
    Discord discordcleint;

    /**
     * Provides object of ClientManager used by current user
     * @param channel requested GUI client type
     * @return specific client type object will be typecast with ClientManager interface class (like Discord obj will be typecast to ClientManager object)
     */
    public ClientManager getClient(String channel) {

        switch(channel) {
            case "Discord":
                return discordcleint;
            default:
                return null;
        }

    }
}
