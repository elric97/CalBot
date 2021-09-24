package com.se21.Calendar;

import org.json.simple.JSONObject;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;    

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
        /*
            Data requirements:
                summary : name of the event
                location
                description
                start date and time
                end date and time 
                (The date and time at which you want the event to start in ISO format: YYYY-MM-DDThh:mm:ss+00:00. +00:00 is the timezone offset. 
                Timezone has been set to new york)
        */

        String summary;
        String location;
        String description;
        String startString;  //strings to give i/p to DateTime()
        String endString;

        Event event = new Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);
        
        DateTime startDateTime = new DateTime(startString);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/New_York");
        event.setStart(start);

        DateTime endDateTime = new DateTime();
        EventDateTime end = new EventDateTime()
            .setDateTime(endDateTime)
            .setTimeZone("America/New_York");
        event.setStart(end);

        //recurrence code

        //reminders code

        String calendarId="primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());

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
