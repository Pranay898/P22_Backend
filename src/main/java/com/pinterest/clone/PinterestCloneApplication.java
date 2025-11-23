package com.pinterest.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pinterest.clone.repository")
@EntityScan(basePackages = "com.pinterest.clone.entity")
public class PinterestCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinterestCloneApplication.class, args);
	}

}
