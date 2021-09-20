package com.se21.calbot.controllers;

import com.se21.calbot.GoogleCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String AuthenticateTest() throws Exception {
        return "ping pong";
    }
}
