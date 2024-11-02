package com.springboot.springbootproject.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        "1",  // MongoDB ids are usually strings
                        "bonface",
                        "bonnie@gmail.com",
                        LocalDate.of(2000, Month.APRIL, 2)
                )
        );
    }

    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
