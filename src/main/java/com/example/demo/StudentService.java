package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getstudents() {
        return studentRepository.findAll();
    }

    public void addstudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent())
            throw new IllegalStateException("emial taken");
        studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("does not exist Student with id = " + studentId);
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String firstName,String lastName, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student not found for this id :: " + studentId)
        );

        if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
            if (optionalStudent.isPresent())
                throw new IllegalStateException("emial taken");
            student.setEmail(email);
        }

    }
}
