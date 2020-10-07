package ru.vikigatz.repository;

import ru.vikigatz.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class StudentRepository {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public Student getById(long id){
        logger.info("Get Student by id: " + id);
        return entityManager.find(Student.class, id);
    }
//
//    public Student getByStudentName(String nick_name){
//        logger.info("Get students by nick_name: " + nick_name);
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
//        Root<Student> element = criteriaQuery.from(Student.class);
//
//        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("nick_name"), nick_name));
//        return  entityManager.createQuery(criteriaQuery).getSingleResult();
//    }

    public List<Student> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = criteriaBuilder.createQuery(Student.class);
        Root<Student> element = criteria.from(Student.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}

