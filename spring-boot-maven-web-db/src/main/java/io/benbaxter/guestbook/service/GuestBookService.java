package io.benbaxter.guestbook.service;

import io.benbaxter.guestbook.model.Person;

import java.util.List;

public interface GuestBookService {
	
	List<Person> getPeople();
	
	List<Person> findCertainPeople(String name);
	
	void addPerson(Person person);
	
	void deletePerson(Person person);
}
