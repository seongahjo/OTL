package com.otl.liveapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LiveApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveApiApplication.class, args);
	}

}
