package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.Course;
import com.springboot.Springbootversioning.entities.InstructorDetail;
import com.springboot.Springbootversioning.entities.InstructorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InstructorDAOImpl implements InstructorDAO {
    Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    EntityManager entityManager;

    // creating constructor injection
    @Autowired
    public InstructorDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(InstructorEntity theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public InstructorEntity findInstructorById(int theId) {
        InstructorEntity entity = entityManager.find(InstructorEntity.class, theId);
        return entity;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        try{
            InstructorEntity entity = entityManager.find(InstructorEntity.class, theId);
            List<Course> courses = entity.getCourses();
            //break the association between course and instructor as Primary key is associated
            for(Course course: courses){
                course.setInstructor(null);
            }
            entityManager.remove(entity);
        }catch(Exception ex){
            ex.printStackTrace();
            throw  ex;
        }
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        return instructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        try{
            InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
            // remove the associated object reference
            // break bi-directional link
            // updates instructor to null first
            instructorDetail.getInstructor().setInstructorDetail(null);
            // then delets instructor detail row only not associated instructor
            entityManager.remove(instructorDetail);
        }catch(Exception ex){
            logger.error("Error while deleting record with id " + theId);
            logger.error(ex.getMessage());
            throw ex;
        }
    }
    @Override
    @Transactional
    public List<Course> findCourseByInstructorId(int id) {
        // find course by instructor id
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", id);
        List<Course> resultSet = query.getResultList();
        return resultSet;
    }

    @Override
    public InstructorEntity findInstructorByIdJoinFetch(int id) {
        TypedQuery<InstructorEntity> query = entityManager.createQuery(
                "select i from InstructorEntity i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", InstructorEntity.class);
        query.setParameter("data", id);
        InstructorEntity result = query.getSingleResult();
        return result;
    }
    @Override
    @Transactional
    public void update(InstructorEntity instructor) {
        entityManager.merge(instructor);
        logger.info("updated instructor " + instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find( Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
        logger.info("updated instructor " + course);
    }

    @Override
    @Transactional
    public void save(Course tempCourse) {
        entityManager.persist(tempCourse);
        logger.info("Saved Course Entity to DB " +  tempCourse);
    }
}
