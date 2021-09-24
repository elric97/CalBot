package com.se21.calbot.listeners;

import com.se21.calbot.Calendar.Calendar;
import com.se21.calbot.Calendar.CalendarFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.se21.calbot.Calendar.Enums.calendarType.googleCalendar;


@RestController
public class RouteController {

    Calendar calObj;
    public RouteController() {
        calObj = CalendarFactory.getUserCalendar(googleCalendar);
    }

    @GetMapping("/redirect")
    @ResponseBody
    public String getFooByIdUsingQueryParam(@RequestParam String state, @RequestParam String code) {
        String token = calObj.getTokens(code);
        if(token == null)
            return "Seems like there is some problem in our system! Can you please retry command !oauth once again";
        else
            return "Auth Successful, you can close this window and get back to discord";
    }
}