package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainBranchRepository extends MongoRepository<MainBranch, String> {

}
