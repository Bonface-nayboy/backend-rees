package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesRepository extends MongoRepository<Sales, String> {
}
