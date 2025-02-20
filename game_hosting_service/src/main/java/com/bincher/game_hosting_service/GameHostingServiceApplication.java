package com.bincher.game_hosting_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GameHostingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameHostingServiceApplication.class, args);
	}

}
