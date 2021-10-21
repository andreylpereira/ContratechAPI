package com.senai.contratech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ContratechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratechApplication.class, args);
	}

}
