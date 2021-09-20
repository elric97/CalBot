package com.se21.Calendar;

import org.json.simple.JSONObject;

import java.security.GeneralSecurityException;

public interface mainCalendar {

    void authenticate() throws Exception;
    JSONObject retrieveEvents(JSONObject req);
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents();
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();

}
