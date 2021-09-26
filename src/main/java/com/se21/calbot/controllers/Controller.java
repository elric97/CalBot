package com.se21.calbot.controllers;
import com.se21.calbot.enums.Enums;
import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.model.User;
import com.se21.calbot.repositories.TokensRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Log
@Getter
@Setter
@Service
public class Controller {

    String clientId;
    Enums.calendarType calendarId;
    String calendarToken; //check later
    Enums.operationType operationId;
    Calendar calObj;

    @Autowired
    CalendarFactory calendarFactory;
    @Autowired
    TokensRepository tokensRepository;
    User user;

    public void initDb(String discordId)
    {
        user = tokensRepository.findById(discordId).orElse(null);
        if(user == null)//To add element in db for first time
        {
            user = new User(discordId,
                    "", "",0L, "",
                    "", "Google","");
            tokensRepository.save(user);
        }
        calObj = calendarFactory.getCalendar("Google");
        calObj.setUserVariable();
    }
    public void getCalToken()
    {

    }
    public void addCalToken()
    {

    }

    //TODO: Change return type to JSON objects
    public String arrangeEvents() throws Exception {
        JSONArray scheduledEventList = calObj.retrieveEvents("primary").getJSONArray("items");
        JSONArray unScheduledEventList = calObj.retrieveEvents(this.user.getCalId()).getJSONArray("items");

        //Filter events for this week


        //Make a list of all events

        //Prioritise all unscheduled events


        //Send the list to client to display
        String events = "";
        for (int i = 0; i < scheduledEventList.length(); i++) {
            org.json.JSONObject jsonLineItem = scheduledEventList.getJSONObject(i);
            events += jsonLineItem.getString("summary") + "    " + jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
        }
        // Unscheduled events set in bot created aPAS calendar
        for (int i = 0; i < unScheduledEventList.length(); i++) {
            org.json.JSONObject jsonLineItem = unScheduledEventList.getJSONObject(i);
            events += jsonLineItem.getString("summary") + "    " + jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
        }
        return events;
    }

    //Todo return type need to be changed to Json objects to make controller and client independent
    @SneakyThrows
    public String dataOperation(Enums.operationType opType, String ... msgParam){
        switch(opType)
        {
            case Add:
            {
                if(msgParam.length != 3)
                {
                    //Some exception is needed and need to indicate user to enter in correct format
                }

                calObj.addEvents(msgParam[0], msgParam[1], msgParam[2]);//!Add title hoursNeeded deadline
                return "done";
            }

            case Delete:
            case Create:
            case Update:
                break;
            case Optimise:
            {
                return this.arrangeEvents();
            }

            case Retrieve:
            {
                try {
                    //  Scheduled events set in primary calendar
                    JSONArray itemArray = calObj.retrieveEvents("primary").getJSONArray("items");
                    String events= "";
                    for (int i = 0; i < itemArray.length(); i++) {
                        org.json.JSONObject jsonLineItem = itemArray.getJSONObject(i);
                        events += jsonLineItem.getString("summary") + "    " + jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
                    }
                    // Unscheduled events set in bot created aPAS calendar
                    itemArray = calObj.retrieveEvents(user.getCalId()).getJSONArray("items");
                    for (int i = 0; i < itemArray.length(); i++) {
                        org.json.JSONObject jsonLineItem = itemArray.getJSONObject(i);
                        events += jsonLineItem.getString("summary") + "    " + jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
                    }
                    return events;
                } catch (Exception e) {
                    log.severe("Google auth URL exception - " + e.getMessage());
                }
            }

            break;
            default:
                throw new IllegalStateException("Unexpected value: " + opType);
        }
        return "Failure!";
    }
    public String getUrl(String discordId, String calType) {
        try {
            return calendarFactory.getCalendar(calType).authenticate(discordId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }
}

