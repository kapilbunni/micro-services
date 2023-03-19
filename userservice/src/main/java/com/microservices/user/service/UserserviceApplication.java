package com.microservices.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
// I'm using <spring-cloud.version>2022.0.1</spring-cloud.version>
// for version 2021.0.5 @enableEurekaClient annotation is required
public class UserserviceApplication {

	//we can declare beans here or inside the main method or in config class with @Configuration annotation

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
