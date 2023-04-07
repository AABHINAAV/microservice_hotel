package com.examMS.UserMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserMsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("User ms started");
	}
}
