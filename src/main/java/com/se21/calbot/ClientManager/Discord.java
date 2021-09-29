package com.se21.calbot.ClientManager;

import com.se21.calbot.controllers.Controller;
import com.se21.calbot.interfaces.ClientManager;
import com.se21.calbot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.se21.calbot.enums.Enums.operationType.*;


/**
 * Discord class manages implementation specific to discord users
 */
@Service
public class Discord implements ClientManager {

    String clientId;
    User user;
    @Autowired
    Controller controller;

    @Override
    public void update() {

    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void show() {

    }

    @Override
    public void suggestForNow() {

    }

    @Override
    public void suggestWeeklyUnscheduled() {

    }

    @Override
    public String processInput(String msg) {
        if(controller.getUser() == null )
        {
            controller.initDb(clientId);
            if(controller.getUser().getToken().isEmpty()) {
                String url = controller.getUrl(clientId, "Google");
                return "Please authorise aPAS to use your calendar\n" + url + "\n" + "Once done please proceed with below commands" +
                        "\n\n" + "!event: To see your scheduled events\n" + "!add taskName hoursNeeded deadline(mm/dd/yyy) : To add new event with given details\n" +
                        "!show: To display tasks you should perform today considering priority of all events";
            }
        }

        String[] commands =msg.split("\n");
        for(String eachCommand: commands)
        {
            String[] tokens = eachCommand.split(" ");
            switch(tokens[0])
            {
                case "!add":
                {
                    //The add logic will be added into add function later on.
                    return controller.dataOperation(Add, tokens[1], tokens[2], tokens[3]);
                }
                case "!event":
                {
                    return controller.dataOperation(Retrieve);
                }
                case "!oauth":
                {
                    String url = controller.getUrl(clientId, "Google");
                    return "Please authorise aPAS to use your calendar\n" + url + "\n" + "Once done please proceed with below commands" +
                            "\n\n" + "!event: To see your scheduled events\n" + "!add taskName hoursNeeded deadline(mm/dd/yyy) : To add new event with given details\n" +
                            "!show: To display tasks you should perform today considering priority of all events";
                }
                case "!show":
                {
                    return controller.dataOperation(Optimise);
                }
                default:
                    System.out.println("Kuch to gadbad hai daya!");
            }

        }
        return "";
    }

    @Override
    public void getLastMessageDataObject() {

    }
}
