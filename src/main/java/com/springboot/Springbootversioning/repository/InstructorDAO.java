package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.InstructorDetail;
import com.springboot.Springbootversioning.entities.InstructorEntity;

public interface InstructorDAO {

    void save(InstructorEntity theInstructor);

    InstructorEntity findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
