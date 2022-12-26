package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WishbujeokApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishbujeokApplication.class, args);
	}
 
}
