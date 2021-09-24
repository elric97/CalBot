package com.se21.calbot.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.se21.calbot.Calendar.Enums.calendarType.googleCalendar;

@Service
public class CalendarFactory {

    private static GoogleCalendar googleCalObj;

    public CalendarFactory(GoogleCalendar googleCalObj) {
        this.googleCalObj = googleCalObj;
    }

    public static Calendar getUserCalendar(Enums.calendarType calType) {

        if (calType == googleCalendar)
        {
            return googleCalObj;
        }
        else
        {
            //Keep adding further
            return null;
        }
    }
}
