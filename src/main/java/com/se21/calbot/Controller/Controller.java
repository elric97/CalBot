package com.se21.calbot.Controller;
import com.se21.calbot.Calendar.Calendar;
import com.se21.calbot.Calendar.CalendarFactory;
import com.se21.calbot.Calendar.Enums;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import static com.se21.calbot.Calendar.Enums.calendarType.googleCalendar;

@Service
public class Controller {

    String clientId;
    Enums.calendarType calendarId;
    String calendarToken; //check later
    Enums.operationType operationId;
    Calendar calObj;

    public Controller() {
        this.calObj = CalendarFactory.getUserCalendar(googleCalendar);
        this.calObj.initCal();


    }

    public String getEvents()
    {
        JSONArray obj = calObj.retrieveEvents().getJSONArray("items");
        JSONObject obj2 = (JSONObject) obj.get(0);
        String ans = "";
        for(Object a: obj)
        {
            JSONObject jsonLineItem = (JSONObject) a;
            ans += jsonLineItem.getString("summary") +"    "+ jsonLineItem.getJSONObject("start").getString("dateTime") + "\n";
        }
        //System.out.println(obj2.get(ans));
        return ans;
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
    public void dataOperation()
    {

    }
}

