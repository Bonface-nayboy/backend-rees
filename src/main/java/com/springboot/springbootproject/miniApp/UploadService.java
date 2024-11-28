package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UploadService {

    private final UploadRepository uploadRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public UploadService(UploadRepository uploadRepository, RegisterRepository registerRepository) {
        this.uploadRepository = uploadRepository;
        this.registerRepository = registerRepository;
    }

    public Upload saveFile(MultipartFile file, String email) throws IOException {
        // Validate email with RegisterRepository
        Optional<Register> user = registerRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Email not found in registered users.");
        }

        Upload upload = new Upload();
        upload.setFileName(file.getOriginalFilename());
        upload.setFileType(file.getContentType());
        upload.setData(file.getBytes());
        upload.setEmail(email); // Set validated email
        return uploadRepository.save(upload);
    }

    public Upload getFile(String id) {
        return uploadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<Upload> getFilesByEmail(String email) {
        return uploadRepository.findByEmail(email);
    }
}
