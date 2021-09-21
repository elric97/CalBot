package com.se21.calbot.controllers;

import com.se21.calbot.model.Token;
import com.se21.calbot.repositories.TokensRepository;
import com.se21.calbot.services.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    GoogleCalendarService googleCalendarService;

    @Autowired
    TokensRepository tokensRepository;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public void authenticateTest() throws Exception {
//        googleCalendarService.authenticate();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getToken(@RequestParam String code, @RequestParam String state) {
        Token token = new Token(code, state);
        tokensRepository.save(token);
        return "Code is " + code + " state is " + state;
    }
}
