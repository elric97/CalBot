package com.se21.calbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan
@EnableAutoConfiguration
public class CalBotApplication implements ApplicationRunner {

    @Autowired
    GoogleCalendar calendar;

    public static void main(String[] args) { SpringApplication.run(CalBotApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        calendar.authenticate();
    }
}
