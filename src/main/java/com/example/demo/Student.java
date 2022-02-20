package com.example.demo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name="Student")
@Table(
        name="student",
        uniqueConstraints = {@UniqueConstraint(name="student_email_unique", columnNames = "email")}
)
public class Student {
    @Id
    @SequenceGenerator(
            name="student_generator",
            sequenceName = "student_generator",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_generator"
    )
    private Long id;

    @Column(
            name= "firstName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name= "lastName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name= "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name= "dateofbirth",
            nullable = false
    )
    private LocalDate dob;

    @Transient
    private int age;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
