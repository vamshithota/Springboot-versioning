package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.entities.*;
import com.springboot.Springbootversioning.repository.InstructorDAO;
import com.springboot.Springbootversioning.repository.InstructorEntityJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    @Autowired
    InstructorDAO instructorDAO;
    @Autowired
    InstructorEntityJPARepository instructorEntityRepository;
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

    @GetMapping("/getInstructorNCourses/{id}")
    public ResponseEntity<InstructorEntity> getInstructorNCourse(@PathVariable Integer id){
        InstructorEntity ins = instructorDAO.findInstructorByIdJoinFetch(id);
        return new ResponseEntity<>(ins,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstructorEntity> updateInstructor(@PathVariable Integer id){
        InstructorEntity instructor = instructorDAO.findInstructorById(id);
        instructor.setEmail("harikachanda@gmail.com");
        instructorDAO.update(instructor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<InstructorEntity> updateCourse(@PathVariable Integer id){
        try{
            Course course = instructorDAO.findCourseById(id);
            course.setTitle("Jazz Music");
            instructorDAO.updateCourse(course);
        }catch (Exception ex){
        logger.error("Exception caught while saving course with id " +  id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/createInsCourseNReviews")
    public ResponseEntity<InstructorEntity> createInstructorCourseAndReviews(){
        try{
            // Create new instructor, instructor detail, Course and its reviews
            InstructorEntity instructorEntity = new InstructorEntity("Raj", "Vootkur","raj@gmail.com");
            // Create InstructorDetail for Instructor
            InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/raj","Eating");
            instructorEntity.setInstructorDetail(instructorDetail);
            // Create Course for the InstructorEntity
            Course course = new Course("Hadoop");
            instructorEntity.add(course);
            // Create Reviews for course
            Review review = new Review("Excellent Course Work!!");
            Review review2 = new Review("Beautifully Architected!!");
            Review review3 = new Review("Well Done!!");
            course.addReview(review);
            course.addReview(review2);
            course.addReview(review3);
            instructorDAO.save(instructorEntity);

        }catch (Exception ex){
          //  logger.error("Exception caught while saving course with id " +  id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createCourseNStudent")
    public ResponseEntity<InstructorEntity> createCourseAndStudent(){
        try{
            // Create new instructor, instructor detail, Course and its reviews
            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the course and associated students
            System.out.println("Saving the course: " + tempCourse);
            System.out.println("associated students: " + tempCourse.getStudents());
            instructorDAO.save(tempCourse);


            System.out.println("Done!");

        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
