package com.se21.calbot.controllers;

import com.se21.calbot.GoogleCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    GoogleCalendar googleCalendar;

    @GetMapping()
    public void AuthenticateTest() throws Exception {
        googleCalendar.authenticate();
    }
}
