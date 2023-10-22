package com.springboot.Springbootversioning.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ActorDTO {


    private Long id;
    private String first_name;
    private String last_name;
    Date last_update;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public ActorDTO(Long id, String first_name, String last_name, Date last_update) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }
}
