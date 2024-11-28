package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/uploads")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("email") String email) {
        try {
            Upload upload = uploadService.saveFile(file, email);
            return ResponseEntity.ok("File uploaded successfully: " + upload.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Upload upload = uploadService.getFile(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + upload.getFileName() + "\"")
                .body(upload.getData());
    }

    @GetMapping
    public ResponseEntity<List<Upload>> getFilesByEmail(@RequestParam String email) {
        try {
            List<Upload> uploads = uploadService.getFilesByEmail(email);
            return ResponseEntity.ok(uploads);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(null);
        }
    }
}
