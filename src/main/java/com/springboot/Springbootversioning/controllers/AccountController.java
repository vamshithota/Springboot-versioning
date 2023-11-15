package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.Account;
import com.springboot.Springbootversioning.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("/getAccount")
    public ResponseEntity<Account> findAccount(){
      // String name = accountDAO.getName();
       accountDAO.setName("Vamshi");
       return new ResponseEntity<Account>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<Account> findAllAccounts(){
        // String name = accountDAO.getName();
        List<Account> accounts = accountDAO.findAccounts();
        return accounts;
    }

    @GetMapping("/findAll/throws")
    public List<Account> findAllAccountsAfterThrowing(){
        // String name = accountDAO.getName();
        List<Account> accounts = new ArrayList<>();
        try{
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        }catch(Exception ex){
            System.out.println("Exception Caught: " + ex.getMessage());
        }
        return accounts;
    }
}
