package ru.vikigatz.controller;

import ru.vikigatz.entity.Student;
import ru.vikigatz.repository.StudentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Web-контроллер
 *
 * @author ViktoriaGatsulia
 * @version 1.0
 */
@RequestScoped
@Path("main")
public class MainController {

    /**
     * Переменная для логирования
     */
    @Inject
    private Logger logger;

    /**
     * Репозиторий студентов
     */
    @Inject
    private StudentRepository studentRepository;

    /**
     * Get-метод для поиска всех студентов
     * <p>
     * Пример curl запроса:
     * curl 'http://127.0.0.1:8080/brandMaker/app/main/all'
     *
     * @return - список найденных студентов
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudent() {
        logger.info("Call /all");
        return studentRepository.getAll();
    }

    /**
     * Get-метод для поиска студента по id
     * <p>
     * Пример curl запроса:
     * curl 'http://127.0.0.1:8080/brandMaker/app/main/1'
     *
     * @return - найденный студент или ошибка 404 если пользователя не существует
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        logger.info("Call /{" + id + "}");
        Student student = studentRepository.getById(id);
        if (Objects.isNull(student))
            return Response.status(404).build();
        return Response.ok(student).build();
    }

    /**
     * Delete-метод для удаления студента по id
     * <p>
     * Пример curl запроса:
     * curl -X DELETE 'localhost:8080/brandMaker/app/main/delete_id=4'
     */
    @DELETE
    @Path("delete_id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudentById(@PathParam("id") Long id) {
        logger.info("Call /delete_id={" + id + "}");
        studentRepository.removeStudentById(id);
    }

    /**
     * Put-метод для добавления студента
     * <p>
     * Пример curl запроса:
     * curl -X PUT -H 'Content-Type:application/json' -d '{"dateOfBirth" : "2000-07-31", "email" : "vova@gmail.com", "firstName" : "Vladimir", "lastName" : "Gatsulia"}' 'localhost:8080/brandMaker/app/main/add'
     */
    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public void add(Student student) {
        logger.info("Call /add");
        studentRepository.addStudent(student);
    }

}
/*
mvn clean install
mvn wildfly:deploy
mvn wildfly:redeploy
mvn wildfly:undeploy
 */
