package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegisterRepository extends MongoRepository<Register, String> {
 Optional<Register> findByEmail(String email);
 // Repository method to find user by email
 Optional<Register> findByUsername(String username);
}
