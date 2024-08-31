package com.towermanagement.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.annotations.OpenAPI31;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPI31
public class NotificationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

 @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
