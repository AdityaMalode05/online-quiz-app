package com.example.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.quizapp")

public class OnlineQuizApplicationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizApplicationBackendApplication.class, args);
	}

}
