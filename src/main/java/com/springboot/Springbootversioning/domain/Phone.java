package com.springboot.Springbootversioning.domain;

import com.springboot.Springbootversioning.validators.ContactNumberConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {

    @ContactNumberConstraint
    private String phoneNumber;

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
