package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.Gender;
import com.springboot.Springbootversioning.domain.PersonCurrent;
import com.springboot.Springbootversioning.domain.PersonOld;
import com.springboot.Springbootversioning.mapper.PersonMapper;
import com.springboot.Springbootversioning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonMapper mapper;
    @Autowired
    PersonRepository repository;


    @PostMapping({"/v1.0", "/v1.1"})
    public PersonOld add(@RequestBody PersonOld person) {
        return (PersonOld) repository.add(person);
    }

    @PostMapping("/v1.2")
    public PersonCurrent add(@RequestBody PersonCurrent person) {
        return mapper.map((PersonOld) repository.add(person));
    }

    @PutMapping("/v1.0")
    @Deprecated
    public PersonOld update(@RequestBody PersonOld person) {
        return (PersonOld) repository.update(person);
    }

    @PutMapping("/v1.1/{id}")
    public PersonOld update(@PathVariable("id") Long id, @RequestBody PersonOld person) {
        return (PersonOld) repository.update(person);
    }

    @PutMapping("/v1.2/{id}")
    public PersonCurrent update(@PathVariable("id") Long id, @RequestBody PersonCurrent person) {
        return mapper.map((PersonOld) repository.update(person));
    }

    @GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
    public PersonOld findByIdOld(@PathVariable("id") Long id) {
        return (PersonOld) repository.findById(id);
    }
    @GetMapping("/v1.2/{id}")
    public PersonCurrent findById(@PathVariable("id") Long id) {
        return mapper.map((PersonOld) repository.findById(id));
    }

    @DeleteMapping({"/v1.0/{id}", "/v1.1/{id}", "/v1.2/{id}"})
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }
}
