package com.towermanagement.sertry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SertryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SertryApplication.class, args);
	}

}
