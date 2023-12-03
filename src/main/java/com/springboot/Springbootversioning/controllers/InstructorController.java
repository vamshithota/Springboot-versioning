package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.entities.Course;
import com.springboot.Springbootversioning.entities.InstructorDetail;
import com.springboot.Springbootversioning.entities.InstructorEntity;
import com.springboot.Springbootversioning.repository.InstructorDAO;
import com.springboot.Springbootversioning.repository.InstructorEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    @Autowired
    InstructorDAO instructorDAO;
    @Autowired
    InstructorEntityRepository instructorEntityRepository;
    @GetMapping("/save")
    public ResponseEntity<InstructorEntity> saveInstructor(){
        try{
//            InstructorEntity instructorEntity = new InstructorEntity();
//            instructorEntity.setFirstName("Vamshi");
//            instructorEntity.setEmail("vamshi@gmail.com");
//            instructorEntity.setLastName("Thota");
//            InstructorDetail instructorDetail = new InstructorDetail();
//            instructorDetail.setHobby("Cricket");
//            instructorDetail.setYoutubeChannel("http://youtube.com/vamshi");
//            instructorEntity.setInstructorDetail(instructorDetail);
            InstructorEntity instructorEntity2 = new InstructorEntity("Harika","Thota", "harika@gmail.com");
            InstructorDetail instructorDetail2 = new InstructorDetail("http://youtube.com/harika","TV");
            instructorEntity2.setInstructorDetail(instructorDetail2);
            Course course1 = new Course("Music") ;
            Course course2 = new Course("M3") ;
            instructorEntity2.add(course1);
            instructorEntity2.add(course2);
            //instructorDAO.save(instructorEntity);
            instructorDAO.save(instructorEntity2);
            logger.info("Saved instructors Successfully "+ instructorEntity2);
            logger.info("Instructor Courses are "+ instructorEntity2.getCourses());
        }catch (Exception ex){
            logger.error("Error Occured in class InstructorController while saving InstructorEntity " + ex.getMessage());
            return new ResponseEntity<InstructorEntity>(HttpStatus.BAD_REQUEST) ;
        }
        return new ResponseEntity<InstructorEntity>(HttpStatus.CREATED) ;
    }

    @GetMapping("/getInstructor/{id}")
    public ResponseEntity<InstructorEntity> getInstructor(@PathVariable int id){
        InstructorEntity instructorEntity = instructorDAO.findInstructorById(id);
        return new ResponseEntity<InstructorEntity>(instructorEntity, HttpStatus.OK);
    }

    @GetMapping("/getInstructorDetail/{id}")
    public ResponseEntity<InstructorDetail> getInstructorDetail(@PathVariable int id){
        InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
        logger.info("the associated instructor: "+ instructorDetail.getInstructor());
      //  System.out.println("the associated instructor: " + instructorDetail.getInstructor());
        return new ResponseEntity<InstructorDetail>(instructorDetail, HttpStatus.OK);
    }
    @DeleteMapping("/deleteInstructor/{id}")
    public ResponseEntity<InstructorEntity> deleteInstructor(@PathVariable int id){
         try{
            instructorDAO.deleteInstructorById(id);
        }catch(Exception ex){
            logger.error("Error while trying  to delete InstructorEntity with id " + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteInstructorDetail/{id}")
    public ResponseEntity<InstructorDetail> deleteInstructorDetail(@PathVariable int id){
        try{
            instructorDAO.deleteInstructorDetailById(id);
        }catch(Exception ex){
            logger.error("Error while trying  to delete InstructorEntity with id " + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getInstructorCourses/{id}")
    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable Integer id){
        List<Course> courses = instructorDAO.findCourseByInstructorId(id);
      //  List<Course> courses = instructorEntityRepository.findCoursesByInstructorId(id);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
