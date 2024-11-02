package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}
