package com.se21.calbot;

import com.se21.calbot.model.Tokens;
import com.se21.calbot.repositories.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication()
@EnableMongoRepositories(basePackages = "com.se21.calbot.repositories")
@EntityScan
public class CalBotApplication {

    @Autowired
    TokensRepository tokensRepository;

    public static void main(String[] args) {
        SpringApplication.run(CalBotApplication.class, args);
    }
}
