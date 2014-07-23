package io.benbaxter.guestbook.web.config;


import io.benbaxter.guestbook.model.Person;
import io.benbaxter.guestbook.repository.PersonRepository;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses={PersonRepository.class})
@EntityScan(basePackageClasses=Person.class)
public class RepositoryConfig {

}
