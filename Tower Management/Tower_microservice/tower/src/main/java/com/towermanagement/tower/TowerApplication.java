package com.towermanagement.tower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowerApplication.class, args);
	}

}
