package com.se21.calbot.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class PingController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "PONG PONG 2";
    }

}
