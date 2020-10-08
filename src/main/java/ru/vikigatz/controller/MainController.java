package ru.vikigatz.controller;

import ru.vikigatz.entity.Student;
import ru.vikigatz.repository.StudentRepository;
import ru.vikigatz.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RequestScoped
@Path("main")
public class MainController {

    @Inject
    private Logger logger;

    @Inject
    private StudentRepository studentRepository;

    /*
    http://127.0.0.1:8080/brandMaker/app/main/all
    */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudent() {
        logger.info("Call /all");
        return studentRepository.getAll();
    }

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

    @DELETE
    @Path("delete_id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudentById(@PathParam("id") Long id) {
        logger.info("Call /delete_id={" + id + "}");
        studentRepository.removeStudentById(id);
    }

    /*
    curl -h" : "2000-07-31", "email" : "vova@gmail.com", "firstName" : "Vladimir", "lastName" : "Gatsulia"}' 'localhost:8080/brandMaker/app/main/add'
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
mvn wildfly:deploy
mvn wildfly:redeploy
mvn wildfly:undeploy
 */
