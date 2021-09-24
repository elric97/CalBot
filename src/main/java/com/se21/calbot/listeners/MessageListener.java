package com.se21.calbot.listeners;

import com.se21.calbot.services.GoogleCalendarService;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public abstract class MessageListener {
    @Autowired
    GoogleCalendarService googleCalendarService;

    public Mono<Void> processCommand(Message eventMessage) {

        Mono<String> cmd = Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .map(Message::getContent);

        User author = eventMessage.getAuthor().orElse(null);
        if(author == null)
            return Mono.empty().then();

        String url = getUrl(author.getUsername()+author.getDiscriminator());
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Your Auth token is " + url ))
                .then();
    }

    private String getUrl(String discordId) {
        try {
            return googleCalendarService.authenticate(discordId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }
}
