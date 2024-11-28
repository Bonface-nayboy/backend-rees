package com.springboot.springbootproject.miniApp;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UploadRepository extends MongoRepository<Upload, String> {
    List<Upload> findByEmail(String email);
}
