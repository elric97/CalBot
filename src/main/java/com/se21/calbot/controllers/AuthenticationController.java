package com.se21.calbot.controllers;

import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.interfaces.Calendar;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class serves as routing path controller. Any call to application/{route} will be handled here.
 * For this project we need to expose one endpoint for Google Oauth 2.0 to share auth code. So that is being handled by this class.
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    CalendarFactory calendarFactory;

    /**
     * This function will be executed whenever a call is made to https://{application endpoint}/test
     * It is responsible to extract auth token and user id from url parameters and save it in the db for future use.
     * @param code auth code
     * @param state userId
     * @return Msg for user
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String token(@RequestParam String code, @RequestParam String state) {
        Calendar calendar = calendarFactory.getCalendar("Google");
        calendar.saveAccessToken(code, state);
        return "Auth completed successfully, please close this window and get back to discord bot!";
    }

    /**
     * Test endpoints, feel free to use them during development
     * @throws Exception
     */
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String authenticateTest() {
        Calendar calendar = calendarFactory.getCalendar("Google");
        return calendar.authenticate("Xero978387");
    }

    /**
     * Test endpoints, feel free to use them during development
     * @throws Exception
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public void summary() throws Exception{
        Calendar calendar = calendarFactory.getCalendar("Google");
        //calendar.retrieveEvents(new JSONObject());
    }
}
