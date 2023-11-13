package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    public List<Account> findAccounts();
}
