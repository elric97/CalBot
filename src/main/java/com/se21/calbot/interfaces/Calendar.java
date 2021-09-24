package com.se21.calbot.interfaces;

import com.se21.calbot.enums.Enums;
import org.json.simple.JSONObject;

public interface Calendar {

    String authenticate(String id);
    void saveAccessToken(String code, String id);
    JSONObject retrieveEvents(JSONObject req) throws Exception;
    Enums.calApiResponse updateEvents(JSONObject req);
    Enums.calApiResponse addEvents();
    Enums.calApiResponse deleteEvents();
    Enums.calApiResponse createNewUnscheduledCalendar();

}
