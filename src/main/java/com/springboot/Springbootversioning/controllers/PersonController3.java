package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.Person;
import com.springboot.Springbootversioning.domain.PersonCurrent;
import com.springboot.Springbootversioning.domain.PersonOld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personParam")
public class PersonController3 {
    /*
    *  Controller class for Request Parameter Versioning
    * */
    @GetMapping(value = "/getPerson", params = "version=1")
    public PersonOld getPerson(){
        return new PersonOld();
    }

    @GetMapping(value = "/getPerson", params = "version=2")
    public PersonCurrent getPerson2(){
        return new PersonCurrent();
    }
}
