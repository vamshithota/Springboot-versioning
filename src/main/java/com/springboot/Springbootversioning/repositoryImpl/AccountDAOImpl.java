package com.springboot.Springbootversioning.repositoryImpl;

import com.springboot.Springbootversioning.domain.Account;
import com.springboot.Springbootversioning.repository.AccountDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
    private String name;
    private String serviceCode;
    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }
    @Override
    public boolean doWork() {

        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {

        List<Account> accounts =  new ArrayList<>();
        accounts.add(new Account("Vamshi", "Platinum"));
        accounts.add(new Account("Madhu", "Silver"));
        accounts.add(new Account("Luca", "Gold"));
        logger.info("Records returning are " + accounts);
        return accounts;
    }
}
