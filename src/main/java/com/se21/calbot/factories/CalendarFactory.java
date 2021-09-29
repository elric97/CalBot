package com.se21.calbot.factories;


import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.services.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory class to generate object for type of calendar used by user.
 * This class is one of responsible to help achieve factory pattern in project.
 */
@Component
public class CalendarFactory {

    @Autowired
    GoogleCalendarService googleCalendarService;

    /**
     * Provides object of calendar used by current user
     * @param channel requested calendar type
     * @return specific calendar type object will be typecast with Calendar interface class (like GoogleCalendarService obj will be typecast to Calendar object)
     */
    public Calendar getCalendar(String channel) {

        switch(channel) {
            case "Google":
                return googleCalendarService;
            default:
                return null;
        }

    }
}
