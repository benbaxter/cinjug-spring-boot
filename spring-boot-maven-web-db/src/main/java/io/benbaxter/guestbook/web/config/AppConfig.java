package io.benbaxter.guestbook.web.config;


import io.benbaxter.guestbook.repository.PersonRepository;
import io.benbaxter.guestbook.service.GuestBookRepositoryService;
import io.benbaxter.guestbook.service.GuestBookService;
import io.benbaxter.guestbook.service.InMemoryGuestBookService;

import org.h2.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(RepositoryConfig.class)
public class AppConfig {

	@Autowired
	@Qualifier("personRepository")
	private PersonRepository personRepository;
	
	@Description("This class will be the default implementation")
	//better to do: @ConditionalOnClass(name="name.of.driver.for.your.db")
	//but since this is a boolean example...
	@ConditionalOnMissingClass(name="org.h2.Driver")
	@Bean(name="guestBookService")
	public GuestBookService inMemoryGuestBookService() {
		return new InMemoryGuestBookService();
	}
	
	@Description("This class will be available if h2 is...")
	@ConditionalOnClass(value=Driver.class)
	@Bean(name="guestBookService")
	public GuestBookService h2GuestBookService() {
		return new GuestBookRepositoryService(personRepository);
	}
}
