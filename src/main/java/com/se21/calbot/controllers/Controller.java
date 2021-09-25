package com.se21.calbot.controllers;
import com.se21.calbot.enums.Enums;
import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.model.User;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Controller {

    String clientId;
    Enums.calendarType calendarId;
    String calendarToken; //check later
    Enums.operationType operationId;
    Calendar calObj;
    @Autowired
    CalendarFactory calendarFactory;


    public void initDb()
    {

    }
    public void getCalToken()
    {

    }
    public void addCalToken()
    {

    }
    public JSONObject arrangeEvents(JSONObject events)
    {

        return null;
    }
    public String dataOperation(Enums.operationType opType){
        calObj = calendarFactory.getCalendar("Google");
        switch(opType)
        {
            case Add:
            case Delete:
            case Create:
            case Update:
                break;

            case Retrieve:
                try {
                    JSONArray itemArray = calObj.retrieveEvents().getJSONArray("items");
                    String events= "";
                    for (int i = 0; i < itemArray.length(); i++) {
                        org.json.JSONObject jsonLineItem = itemArray.getJSONObject(i);
                        events += jsonLineItem.getString("summary") + "    " + jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
                    }
                    return events;
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

