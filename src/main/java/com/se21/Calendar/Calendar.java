package com.se21.Calendar;

import org.json.simple.JSONObject;

public interface Calendar {

    JSONObject authenticate(JSONObject auth);
    JSONObject retrieveEvents(JSONObject req);
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents();
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();

}
