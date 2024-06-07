package com.example.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlobalSolutionJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalSolutionJavaApplication.class, args);
		System.out.println("Spring Boot rodando na porta 8080!");
	}

}
