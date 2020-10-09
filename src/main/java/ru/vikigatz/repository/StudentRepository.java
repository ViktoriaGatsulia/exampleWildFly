package ru.vikigatz.repository;

import ru.vikigatz.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Transactional
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
            return;
        }
        entityManager.remove(student);
        logger.info("Remove student by id=" + id);
    }

    public void addStudent(Student student) {
        if (Objects.isNull(student)) {
            logger.info("Impossible add incorrect student");
            return;
        }
        if (Objects.isNull(student.getDateOfBirth()) || Objects.isNull(student.getEmail()) || Objects.isNull(student.getFirstName()) || Objects.isNull(student.getLastName())) {
            logger.info("Impossible add incorrect student");
            return;
        }
        entityManager.merge(student);
        logger.info("Add new student: " + student.toString());
    }

}

