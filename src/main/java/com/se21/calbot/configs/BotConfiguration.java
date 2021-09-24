package com.se21.calbot.configs;

import com.se21.calbot.listeners.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BotConfiguration {
    // @Value("${token}")
    private String botToken = "ODg5MjI2NTgwMzkwNDYxNDkx.YUeKyQ.6veYA1eF8JEVMtgenPSs2WtEJSQ";

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(botToken)
                .build().gateway().login().block();


        System.out.println("Initializing event listeners");
        for(EventListener<T> listener : eventListeners) {
            assert client != null;
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }

        return client;
    }
}
