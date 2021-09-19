package com.se21.Calendar;

import org.json.simple.JSONObject;

public class GoogleCalendar implements Calendar{

    String accessToken;
    String refreshToken;
    String authToken;

    @Override
    public JSONObject authenticate(JSONObject auth) {

        return null;
    }

    @Override
    public JSONObject retrieveEvents(JSONObject req) {

        return null;
    }

    @Override
    public Enums.calApiResponse updateEvents(JSONObject req) {

        return Enums.calApiResponse.Success;
    }

    @Override
    public Enums.calApiResponse addEvents() {

        return Enums.calApiResponse.Success;
    }

    @Override
    public Enums.calApiResponse deleteEvents() {

        return Enums.calApiResponse.Success;
    }

    @Override
    public Enums.calApiResponse createNewUnscheduledCalendar() {

        return Enums.calApiResponse.Success;
    }
}
