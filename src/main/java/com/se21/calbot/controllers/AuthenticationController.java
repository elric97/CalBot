package com.se21.calbot.controllers;

import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
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
    public String token(@RequestParam String code, @RequestParam String state) {
        Calendar calendar = calendarFactory.getCalendar("Google");
        calendar.saveAccessToken(code, state);
        return "Auth completed successfully, please close this window and get back to discord bot!";
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public void summary() throws Exception{
        Calendar calendar = calendarFactory.getCalendar("Google");
        //calendar.retrieveEvents(new JSONObject());
    }
}
