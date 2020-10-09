package ru.vikigatz.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Сушность описывающая объект Студент
 *
 * @author ViktoriaGatsulia
 * @version 1.0
 */
@XmlRootElement
@Entity
@Table(name = "student")
public class Student {

    /**
     * Идентификатор студента
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Имя студента
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия студента
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email студента
     */
    @Column(name = "email")
    private String email;

    /**
     * Дата рождения студента
     */
    @Column(name = "date_of_birth")
    private java.sql.Date dateOfBirth;


    /**
     * Пустой конструктор
     */
    public Student() {}

    /**
     * Геттер
     *
     * @return - идентификатор студента
     */
    public long getId() {
        return id;
    }

    /**
     * Сеттер
     *
     * @param id - идентификатор студента
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Геттер
     *
     * @return - имя студента
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Сеттер
     *
     * @param firstName - имя студента
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Геттер
     *
     * @return - фамилия студента
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Сеттер
     *
     * @param lastName - фамилия студента
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Геттер
     *
     * @return - email студента
     */
    public String getEmail() {
        return email;
    }

    /**
     * Сеттер
     *
     * @param email - email студента
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Геттер
     *
     * @return - дата рождения студента
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Сеттер
     *
     * @param dateOfBirth - дата рождения студента
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Метод для записи информации о студенете в виде строки
     *
     * @return - строка с информацией о студенте
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
