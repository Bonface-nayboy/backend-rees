package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubBranchRepository extends MongoRepository <SubBranch ,String> {
    
}
