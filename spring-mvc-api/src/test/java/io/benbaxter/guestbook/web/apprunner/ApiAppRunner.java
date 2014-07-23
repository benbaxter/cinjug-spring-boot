package io.benbaxter.guestbook.web.apprunner;

import io.benbaxter.guestbook.web.config.AppConfig;

import org.springframework.boot.SpringApplication;

public class ApiAppRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppConfig.class, args);
	}
}
