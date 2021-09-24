package com.se21.calbot.listeners;
import com.se21.calbot.Controller.Controller;
import discord4j.core.object.entity.Message;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
public abstract class MessageListener {

    @Autowired
    Controller controllerObj;

    public Mono<Void> processCommand(Message eventMessage) {
        System.out.println(eventMessage.getAuthor());

        if(eventMessage.getContent().equalsIgnoreCase("!oauth")) {
            //HttpGet request = new HttpGet("https://accounts.google.com/o/oauth2/v2/auth");\
            String temp = "https://accounts.google.com/o/oauth2/v2/auth?client_id=311852341920-0p94jk9fjl1vkbk1c8b1jocpcfjmufeb.apps.googleusercontent.com&redirect_uri=http://localhost:8081/redirect&response_type=code&scope=https://www.googleapis.com/auth/calendar&access_type=offline&state=";
            String url = temp + eventMessage.getUserData().id().toString();
            return Mono.just(eventMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().equalsIgnoreCase("!oauth"))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage("\n" + url + "\n" + "Once successfully authorised you can check your current events using command '!events'"))
                    .then();
        }
        else if(eventMessage.getContent().equalsIgnoreCase("!event"))
        {
            String eventsList = controllerObj.getEvents();
            return Mono.just(eventMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().equalsIgnoreCase("!event"))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage("" + eventsList))
                    .then();
        }
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Things to do today:\n - write a bot\n - eat lunch\n - play a game"))
                .then();
    }
}
