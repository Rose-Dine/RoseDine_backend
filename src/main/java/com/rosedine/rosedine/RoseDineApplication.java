package com.rosedine.rosedine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoseDineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoseDineApplication.class, args);
	}

}
