package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}
