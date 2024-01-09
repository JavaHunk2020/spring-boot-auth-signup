package com.technohunk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableSwagger2
//@EnableEurekaClient
public class SMegaCaraApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SMegaCaraApplication.class, args);
	}

}	
