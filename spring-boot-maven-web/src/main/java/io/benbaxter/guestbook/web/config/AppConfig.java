package io.benbaxter.guestbook.web.config;


import io.benbaxter.guestbook.service.GuestBookService;
import io.benbaxter.guestbook.service.InMemoryGuestBookService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfig {

	@Bean
	public GuestBookService guestBookService() {
		return new InMemoryGuestBookService();
	}
		
}
