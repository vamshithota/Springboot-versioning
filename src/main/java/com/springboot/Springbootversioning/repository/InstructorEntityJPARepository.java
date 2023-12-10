package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.Course;
import com.springboot.Springbootversioning.entities.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InstructorEntityJPARepository extends JpaRepository<InstructorEntity,Integer> {
    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId")
    List<Course> findCoursesByInstructorId(@Param("instructorId") Integer instructorId);
}
