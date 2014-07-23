package io.benbaxter.guestbook.web.controller.rest;


import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import io.benbaxter.guestbook.model.Person;
import io.benbaxter.guestbook.service.GuestBookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestBookController {

	@Autowired
	GuestBookService service;
	
	@RequestMapping("/people")
	public List<Person> getPeople() {
		return service.getPeople();
	}

	@RequestMapping("/people/{name}")
	public List<Person> findCertainPeople(@PathVariable String name) {
		return service.findCertainPeople(name);
	}

	@RequestMapping(value="/people", method=PUT)
	public List<Person> addPerson(@RequestBody Person person) {
		service.addPerson(person);
		return getPeople();
	}

	@RequestMapping(value="/people", method=DELETE)
	public List<Person> deletePerson(@RequestBody Person person) {
		service.deletePerson(person);
		return getPeople();
	}
	
}