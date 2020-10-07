package ru.vikigatz.controller;

import ru.vikigatz.entity.Student;
import ru.vikigatz.repository.StudentRepository;
import ru.vikigatz.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/main")
public class MainController {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private StudentService studentService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudent(){
        return studentRepository.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getUserById(@PathParam("id") long id){
        return  studentRepository.getById(id);
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public String hello() {
        return "hello";
    }
}
