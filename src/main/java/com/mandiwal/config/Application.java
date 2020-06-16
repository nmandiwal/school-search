package com.mandiwal.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mandiwal.persistence.dao")
@EntityScan("com.mandiwal.persistence.model")
@ComponentScan("com.mandiwal")
//public class Application extends SpringBootServletInitializer {
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}