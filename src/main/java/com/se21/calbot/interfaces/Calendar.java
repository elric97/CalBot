package com.se21.calbot.interfaces;

import com.se21.calbot.enums.Enums;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

public interface Calendar {

    String authenticate(String id);
    void saveAccessToken(String code, String id);
    org.json.JSONObject retrieveEvents(String calId) throws Exception;
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents( String title, String hours, String deadline);
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();

}
