package com.se21.calbot.Calendar;
import org.json.simple.JSONObject;

public interface Calendar {

    void initCal();
    JSONObject authenticate(JSONObject auth);
    org.json.JSONObject retrieveEvents();
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents();
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();
    String getTokens(String authToken);
}
