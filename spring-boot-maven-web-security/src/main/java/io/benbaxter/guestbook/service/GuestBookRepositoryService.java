package io.benbaxter.guestbook.service;

import io.benbaxter.guestbook.model.Person;
import io.benbaxter.guestbook.repository.PersonRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

public class GuestBookRepositoryService implements GuestBookService {

	PersonRepository repository;

	@Autowired
	public GuestBookRepositoryService(PersonRepository repository) {
		this.repository = repository;
	}
	
	public List<Person> getPeople() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public List<Person> findCertainPeople(String name) {
		return Lists.newArrayList(repository.findByFullName(name));
	}

	@Override
	@Transactional
	public void addPerson(Person person) {
		repository.save(person);
	}

	@Override
	@Transactional
	public void deletePerson(Person person) {
		repository.delete(person);
	}
	
}
