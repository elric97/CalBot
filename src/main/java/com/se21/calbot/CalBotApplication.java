package com.se21.calbot;

import com.se21.calbot.repositories.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories(basePackages = "com.se21.calbot.repositories")
@EntityScan
public class CalBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalBotApplication.class, args);
    }
}
