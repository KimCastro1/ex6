package com.castro.ex6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex6Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex6Application.class, args);
	}

}
