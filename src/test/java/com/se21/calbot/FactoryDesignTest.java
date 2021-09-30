package com.se21.calbot;

import com.se21.calbot.ClientManager.Discord;
import com.se21.calbot.factories.CalendarFactory;
import com.se21.calbot.factories.clientFactory;
import com.se21.calbot.services.GoogleCalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


@SpringBootTest()
public class FactoryDesignTest {

    /**
     * Test confirms the design is still intact
     */
    @Test
    void verifyFactoryClasses() {

        CalendarFactory calFactory = new CalendarFactory();
        clientFactory clientFactoryObj = new clientFactory();
        GoogleCalendarService googleCalObj = new GoogleCalendarService();
        Discord obj = new Discord();
        //Need to initialise Factory class before running testcase, to compare exact obj type
        Assert.isTrue(calFactory.getCalendar("Google")==null, "Object not returned correctly, design change not accepted");
        Assert.isTrue(clientFactoryObj.getClient("Discord")==null, "Object not returned correctly, design change not accepted");

    }
}
