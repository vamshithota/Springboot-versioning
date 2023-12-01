package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.InstructorDetail;
import com.springboot.Springbootversioning.entities.InstructorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
