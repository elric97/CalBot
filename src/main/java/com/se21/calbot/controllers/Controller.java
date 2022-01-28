package com.se21.calbot.controllers;
import com.se21.calbot.enums.Enums;
import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.model.User;
import com.se21.calbot.model.events;
import com.se21.calbot.repositories.TokensRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se21.calbot.controllers.schedulerAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * This is main controller class for whole aPAS. In block diagram of architecture, you can see this
 * class at the center. It serves various purposes, but major functionality is to provide some
 * algorithm to suggest most optimal activities to be done by user.
 */
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

    /**
     * It will create a new entry in db for current user if it already doesn't exist.
     * Else it will retrieve all existing data from db.
     * @param discordId
     */
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


    /**
     * This function contains the logic to re-arrange all events on the basis of priority and
     * suggest most optimal ones for today/week.
     * @return
     * @throws Exception
     */
    //TODO: Change return type to JSON objects
    public String arrangeEvents() {
        JSONArray scheduledEventList = null;
        JSONArray unScheduledEventList = null;
        try {
            scheduledEventList = calObj.retrieveEvents("primary").getJSONArray("items");
            unScheduledEventList = calObj.retrieveEvents(this.user.getCalId()).getJSONArray("items");
            List<events> scheduled = new ArrayList<events>(), unscheduled = new ArrayList<events>();
            scheduled = schedulerAlgo.jsonToEventObjects(scheduledEventList, false);
            unscheduled = schedulerAlgo.jsonToEventObjects(unScheduledEventList, true);
            System.out.println(unScheduledEventList);
            for (int i = 0; i < scheduled.size(); i++) {

                System.out.println(scheduled.get(i).title + scheduled.get(i).deadline);
            }
            for (int i = 0; i < unscheduled.size(); i++) {

                System.out.println(unscheduled.get(i).title + unscheduled.get(i).deadline);
            }
            String suggestion = schedulerAlgo.createBuckets(scheduled, unscheduled);
            if(suggestion == "")
            {
                return "Something is wrong!";
            }
            else
            {
                return suggestion;
            }
        } catch (Exception e) {
            log.severe("Data read exception - " + e.getMessage());

        }
        return "Try !Oauth, your auth token seems to be expired.";
    }

    /**
     * This is single interface function for clientManager layer to directly call some
     * CRUD operation for calendar layer.
     * @param opType Add, delete, Retrieve.......etc.
     * @param msgParam the user given parameters
     * @return Output received after operation execution
     */
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
                    if(events == "")
                        events = "Nothing to show!";
                    return events;
                } catch (Exception e) {
                    log.severe("Google auth URL exception - " + e.getMessage());
                }
            }

            break;
            default:
                throw new IllegalStateException("Unexpected value: " + opType);
        }
        return "Try !Oauth, your auth token seems to be expired.";
    }

    /**
     * To get URL for OAUTH 2.0
     * @param discordId
     * @param calType
     * @return URL
     */
    public String getUrl(String discordId, String calType) {
        try {
            return calendarFactory.getCalendar(calType).authenticate(discordId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }
}

