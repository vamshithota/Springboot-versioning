package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.domain.Account;
import com.springboot.Springbootversioning.repository.AccountDAO;
import com.springboot.Springbootversioning.services.TrafficFortuneService;
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
    @Autowired
    TrafficFortuneService trafficFortuneService;

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
            //tripWire set to false to execute @AfterThrowing Aspect
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        }catch(Exception ex){
            System.out.println("Exception Caught: " + ex.getMessage());
        }
        return accounts;
    }

    @GetMapping("/findAllAfter")
    public List<Account> findAllAccountsAfter(){
        // String name = accountDAO.getName();
        List<Account> accounts = new ArrayList<>();
        try{
            //tripWire set to false to execute @After Aspect
            boolean tripWire = false;
            accounts = accountDAO.findAccounts(tripWire);
        }catch(Exception ex){
            System.out.println("Exception Caught: " + ex.getMessage());
        }
        return accounts;
    }
    @GetMapping("/getFort")
    public ResponseEntity<String> getFortuneData(){
        boolean tripWire = false;
        String result="";
        try{
            result = trafficFortuneService.getFortune(tripWire);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity("Exception occurred in " + getClass(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
