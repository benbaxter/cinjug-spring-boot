package io.benbaxter.guestbook.web.apprunner;

import io.benbaxter.guestbook.web.config.AppConfig;

import org.springframework.boot.SpringApplication;

public class DBAppRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppConfig.class, args);
	}
}
