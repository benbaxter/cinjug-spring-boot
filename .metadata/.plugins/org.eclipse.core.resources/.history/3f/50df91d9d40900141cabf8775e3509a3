package io.benbaxter.guestbook.model;

import static org.springframework.util.StringUtils.hasText;

public class Person {

	String firstName;
	String lastName;

	public String getFullName() {
		if( hasText(firstName) ) {
			if( hasText(lastName) ) {
				return firstName + " " + lastName;
			} else {
				return firstName;
			}
		} else {
			if( hasText(lastName) ) {
				return lastName;
			} else {
				return "";
			}
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
