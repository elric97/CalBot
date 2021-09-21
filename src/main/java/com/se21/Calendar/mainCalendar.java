package com.se21.Calendar;

import com.se21.calbot.enums.Enums;
import org.json.simple.JSONObject;

public interface mainCalendar {

    void authenticate() throws Exception;
    JSONObject retrieveEvents(JSONObject req);
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents();
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();

}
