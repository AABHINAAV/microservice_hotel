package com.examMS.HotelMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelMsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HotelMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hotel MS Started");
	}
}
