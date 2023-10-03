package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.Phone;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/phone")
public class PhoneController {

    @GetMapping("/validatePhone")
    public String loadFormPage(Model m) {
        m.addAttribute("validatedPhone", new Phone());
        return "phone Home";
    }


    @PostMapping(value = "/savePhone")
    public String saveNumber(@Valid @RequestBody Phone phone, BindingResult result) {
        if (result.hasErrors()) {
            return "please enter valid number";
        }
        return "Saved Num Succesfully!";
    }
}
