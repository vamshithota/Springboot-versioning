package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.PersonCurrent;
import com.springboot.Springbootversioning.domain.PersonOld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personCustomHeader")
public class PersonController4 {
    /*
     * Custom Header Versioning Controller class
     * */

    @GetMapping(value = "/getPerson", headers = "X-API-VERSION=1")
    public PersonCurrent getPerson() {
        return new PersonCurrent();
    }

    @GetMapping(value = "/getPerson", headers = "X-API-VERSION=2")
    public PersonOld getPerson2() {
        return new PersonOld();
    }
}
