package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends MongoRepository<Stock, String> {

    Optional<Stock> findByProductId(String productId);
    Optional<Stock> findByProductIdAndEmail(String productId, String email); // Find stock by product ID and email
    List<Stock> findByEmail(String email);
}
