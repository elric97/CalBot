package com.se21.calbot.factories;


import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.services.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarFactory {

    @Autowired
    GoogleCalendarService googleCalendarService;

    public Calendar getCalendar(String channel) {

        switch(channel) {
            case "Google":
                return googleCalendarService;
            default:
                return null;
        }

    }
}
