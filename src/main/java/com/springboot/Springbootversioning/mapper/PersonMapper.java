package com.springboot.Springbootversioning.mapper;

import com.springboot.Springbootversioning.domain.PersonCurrent;
import com.springboot.Springbootversioning.domain.PersonOld;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PersonMapper {

	public PersonCurrent map(PersonOld person) {
		int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears();
		return new PersonCurrent(person.getId(), person.getName(), person.getGender(), age);
	}
	
}
