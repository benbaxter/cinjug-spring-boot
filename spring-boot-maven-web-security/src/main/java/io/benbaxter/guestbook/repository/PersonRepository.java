package io.benbaxter.guestbook.repository;

import io.benbaxter.guestbook.model.Person;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastNameContaining(String lastName);

    @Query("select p from Person p "
    		+ " where upper(p.firstName) like upper(concat ('%', :name, '%')) "
    		+ " or upper(p.lastName) like upper(concat ('%', :name, '%')) ")
    List<Person> findByFullName(@Param("name") String name);
    
}
