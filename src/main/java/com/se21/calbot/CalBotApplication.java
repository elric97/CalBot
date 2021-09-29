/**
 * It holds complete application
 */
package com.se21.calbot;

import com.se21.calbot.controllers.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the main class of the project.
 * It contains the main function.
 */
@SpringBootApplication()
@EntityScan
@EnableJpaRepositories("com.se21.calbot.repositories")
public class CalBotApplication {

    /**
     * Runs the Spring application
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CalBotApplication.class, args);
    }
}
