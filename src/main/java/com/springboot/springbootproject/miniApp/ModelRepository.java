package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

// ModelRepository.java
public interface ModelRepository extends MongoRepository<Model, String> {
    List<Model> findByCategory(String category);
    Optional<Model> findByProductId(String productId);
   List<Model> findByEmail(String email);
}
