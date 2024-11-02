package com.springboot.springbootproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
   return studentService.getStudents();
    }

    @PostMapping
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentService.saveAll(students);
    }

}
