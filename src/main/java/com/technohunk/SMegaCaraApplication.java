package com.technohunk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@EnableEurekaClient
public class SMegaCaraApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SMegaCaraApplication.class, args);
	}

}	
