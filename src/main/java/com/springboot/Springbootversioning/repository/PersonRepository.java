package com.springboot.Springbootversioning.repository;


import com.springboot.Springbootversioning.domain.Gender;
import com.springboot.Springbootversioning.domain.Person;
import com.springboot.Springbootversioning.domain.PersonCurrent;
import com.springboot.Springbootversioning.domain.PersonOld;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class PersonRepository {

	private List<Person> persons = new ArrayList<>();
	@PostConstruct
	private void doPreinit(){
		//doing post construct
		System.out.println("Executing post contruct method");
		persons.add(new PersonOld(1L,"Vamshi", Gender.MALE, LocalDate.now()));
		persons.add(new PersonOld(2L,"Harika", Gender.FEMALE,LocalDate.now()));
		persons.add(new PersonOld(3L,"Ajay", Gender.MALE,LocalDate.now()));

		// adding personcurrent objects
		persons.add(new PersonCurrent(1L,"Vamshi", Gender.MALE,23));
		persons.add(new PersonCurrent(2L,"Harika", Gender.MALE,21));
		persons.add(new PersonCurrent(3L,"Ajay", Gender.MALE,30));
	}
	public Person add(Person person) {
		person.setId((long) (persons.size()+1));
		persons.add(person);
		return person;
	}
	
	public Person update(Person person) {
		persons.set(person.getId().intValue() - 1, person);
		return person;
	}
	
	public Person update(Long id, Person person) {
		persons.set(id.intValue() - 1, person);
		return person;
	}
	
	public Person findById(Long id) {
		Optional<Person> person = persons.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (person.isPresent())
			return person.get();
		else
			return null;
	}
	
	public void delete(Long id) {
		persons.remove(id.intValue());
	}
	
}
