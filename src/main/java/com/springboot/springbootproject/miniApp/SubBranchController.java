package com.springboot.springbootproject.miniApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/subbranch")
public class SubBranchController {
    private final SubBranchService subBranchService;

    @Autowired
    public SubBranchController(SubBranchService subBranchService) {
        this.subBranchService = subBranchService;
    }

    @GetMapping
    public List<SubBranch> getSubBranchs() {
        return subBranchService.getSubBranchs();
    }

    @PostMapping
    public List<SubBranch> createSubBranchs(@RequestBody List<SubBranch> subBranchs) {
        return subBranchService.createSubBranchs(subBranchs);
    }

    @PostMapping("/single")
    public SubBranch createSubBranch(@RequestBody SubBranch subBranch) {
        return subBranchService.createSubBranch(subBranch);
    }

    @PutMapping("/single/{id}")
    public SubBranch updateSubBranch(@PathVariable String id, @RequestBody SubBranch subBranch) {
        return subBranchService.updateSubBranch(id, subBranch);
    }

   @DeleteMapping("/single/{id}")
public ResponseEntity<String> deleteSubBranch(@PathVariable String id) {
    try {
        subBranchService.deleteSubBranch(id);
        return ResponseEntity.ok("SubBranch deleted successfully");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}


}
