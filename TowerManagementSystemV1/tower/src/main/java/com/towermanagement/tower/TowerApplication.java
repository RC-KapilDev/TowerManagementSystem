package com.towermanagement.tower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.models.annotations.OpenAPI31;

@SpringBootApplication
@EnableDiscoveryClient
// @EnableJpaAuditing
@OpenAPI31
public class TowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowerApplication.class, args);
	}

}
