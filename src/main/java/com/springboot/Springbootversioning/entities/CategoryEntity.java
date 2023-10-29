package com.springboot.Springbootversioning.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;
    @Column(name="name")
    private String name;
    //@DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name="last_update")
    @JsonFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.DATE) // Make sure to use TemporalType.DATE
    Date last_update;
}
