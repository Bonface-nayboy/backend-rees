package com.springboot.springbootproject.miniApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/mainbranch")
public class MainBranchController {
    private final MainBranchService mainBranchService;

    @Autowired
    public MainBranchController(MainBranchService mainBranchService) {
        this.mainBranchService = mainBranchService;
    }

    @GetMapping
    public List<MainBranch> getMainBranchs() {
        return mainBranchService.getMainBranchs();
    }

    @PostMapping
    public List<MainBranch> createMainBranchs(@RequestBody List<MainBranch> mainBranchs) {
        return mainBranchService.saveAll(mainBranchs);
    }

    @PostMapping("/single")
    public ResponseEntity<String> createMainBranch(@RequestBody MainBranch mainBranch) {
        try {
            mainBranchService.save(mainBranch); // Use save() for a single object
            return ResponseEntity.ok("Main branch registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
