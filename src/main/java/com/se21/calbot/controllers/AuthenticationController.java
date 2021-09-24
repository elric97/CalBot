package com.se21.calbot.controllers;

import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.repositories.TokensRepository;
import com.se21.calbot.services.GoogleCalendarService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    CalendarFactory calendarFactory;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String authenticateTest() {
        Calendar calendar = calendarFactory.getCalendar("Google");
        return calendar.authenticate("Xero978387");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void token(@RequestParam String code, @RequestParam String state) {
        Calendar calendar = calendarFactory.getCalendar("Google");
        calendar.saveAccessToken(code, state);
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public void summary() throws Exception{
        Calendar calendar = calendarFactory.getCalendar("Google");
        calendar.retrieveEvents(new JSONObject());
    }
}
