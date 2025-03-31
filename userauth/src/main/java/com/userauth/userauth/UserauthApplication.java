package com.userauth.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaRepositories
public class UserauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserauthApplication.class, args);
	}

}
