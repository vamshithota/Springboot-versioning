package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.Course;
import com.springboot.Springbootversioning.entities.InstructorDetail;
import com.springboot.Springbootversioning.entities.InstructorEntity;

import java.util.List;

public interface InstructorDAO {

    void save(InstructorEntity theInstructor);

    InstructorEntity findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCourseByInstructorId(int id);

    InstructorEntity findInstructorByIdJoinFetch(int id);

    void update(InstructorEntity instructor);

    Course findCourseById(int id);

    void updateCourse(Course course);

    void save(Course tempCourse);
}
