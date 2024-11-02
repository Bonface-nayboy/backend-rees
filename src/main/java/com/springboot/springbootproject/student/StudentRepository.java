package com.springboot.springbootproject.student;

import org.springframework.data.mongodb.repository.MongoRepository;

// Define as an interface, not a class
public interface StudentRepository extends MongoRepository<Student, String> {
    // You can add custom query methods here if necessary
}
