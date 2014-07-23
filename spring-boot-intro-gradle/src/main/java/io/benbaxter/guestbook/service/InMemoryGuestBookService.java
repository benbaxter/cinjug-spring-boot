package io.benbaxter.guestbook.service;

import io.benbaxter.guestbook.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryGuestBookService implements GuestBookService {

	List<Person> people;

	public List<Person> getPeople() {
		if( people == null ) {
			people = new ArrayList<>();
		}
		return people;
	}

	@Override
	public List<Person> findCertainPeople(String name) {
		return getPeople()
				.stream()
				.filter( p -> p.getFullName().toUpperCase().contains(name.toUpperCase()) )
				.collect(Collectors.toList());
	}

	@Override
	public void addPerson(Person person) {
		getPeople().add(person);
	}

	@Override
	public void deletePerson(Person person) {
		getPeople().remove(person);
	}
	
}
