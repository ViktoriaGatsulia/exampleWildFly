package ru.vikigatz.repository;

import ru.vikigatz.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;


@ApplicationScoped
public class StudentRepository {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public Student getById(long id) {
        logger.info("Get Student by id: " + id);
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAll() {
        logger.info("Get all student");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = criteriaBuilder.createQuery(Student.class);
        Root<Student> element = criteria.from(Student.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public void removeStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (Objects.isNull(student)) {
            logger.info("Student from id=" + id + "does not exist, remove impossible");
        }
        entityManager.remove(student);
        logger.info("Remove student by id=" + id);
//        entityManager.flush();
//        entityManager.clear();
    }

    public void addStudent(Student student) {
        logger.info("SOOOOOOOOOOOOS + " + Objects.isNull(student));
        entityManager.persist(student);
        logger.info("Add new student: " + student.toString());
    }

    public void updateStudent(Student student) {
        entityManager.persist(student);
        logger.info("Update student: " + student.toString());
    }
}

