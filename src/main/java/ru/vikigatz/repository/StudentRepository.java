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


/**
 * Репозиторий для сущности Студент
 *
 * @author ViktoriaGatsulia
 * @version 1.0
 */
@Transactional
@ApplicationScoped
public class StudentRepository {

    /**
     * Переменная логирования
     */
    @Inject
    private Logger logger;

    /**
     * Переменная менеджера сущностей
     */
    @Inject
    private EntityManager entityManager;

    /**
     * Метод для поиска студента по id
     *
     * @param id - идентификатор искомого студента
     * @return - найденный студент
     */
    public Student getById(long id) {
        logger.info("Get Student by id: " + id);
        return entityManager.find(Student.class, id);
    }

    /**
     * Метод для поиска всех студентов
     *
     * @return - список найденных студентов
     */
    public List<Student> getAll() {
        logger.info("Get all student");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = criteriaBuilder.createQuery(Student.class);
        Root<Student> element = criteria.from(Student.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Метод для удаления студента по id
     *
     * @param id - идентификатор удаляемого студента
     */
    public void removeStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (Objects.isNull(student)) {
            logger.info("Student from id=" + id + "does not exist, remove impossible");
            return;
        }
        entityManager.remove(student);
        logger.info("Remove student by id=" + id);
    }

    /**
     * Метод для добавления нового студента
     *
     * @param student - добавляемый студент
     */
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

