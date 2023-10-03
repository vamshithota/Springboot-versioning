package com.springboot.Springbootversioning.domain;

import com.springboot.Springbootversioning.validators.ContactNumberConstraint;

public class Phone {

    @ContactNumberConstraint
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
