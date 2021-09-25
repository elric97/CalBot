package com.se21.calbot.listeners;

import com.se21.calbot.controllers.Controller;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.se21.calbot.enums.Enums.operationType.Retrieve;

@Service
public abstract class MessageListener {
    @Autowired
    Controller controller;

    public Mono<Void> processCommand(Message eventMessage) {

        Mono<String> cmd = Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .map(Message::getContent);

        User author = eventMessage.getAuthor().orElse(null);
        if(author == null)
            return Mono.empty().then();

        if(eventMessage.getContent().equalsIgnoreCase("!event"))
        {
            String eventsList = controller.dataOperation(Retrieve);
            return Mono.just(eventMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().equalsIgnoreCase("!event"))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage("" + eventsList))
                    .then();
        }

        // Option can be added to ask user which calendar he/she wants to integrate
        String url = controller.getUrl(author.getUsername()+author.getDiscriminator(), "Google");
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!oauth"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Please authorise aPAS to use your calendar" + url + "\n" + "Once done please proceed with below commands" +
                        "\n" + "!events: To see your scheduled events\n" + "!addEvent----------------------- : To add new event with given details"))
                .then();



    }

}
