package ru.vikigatz.service;

import ru.vikigatz.entity.Student;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class StudentService {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public void create(Student item) {
        logger.info("Created message mapping: " + item);
        entityManager.merge(item);
    }

    public void  update (Student item){
        logger.info("Updated message mapping: " + item);
        entityManager.merge(item);
    }

    public void delete (Student item){
        logger.info("Deleted message mapping: " + item);
        entityManager.remove(item);
    }
}