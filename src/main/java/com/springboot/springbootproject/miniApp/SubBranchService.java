package com.springboot.springbootproject.miniApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubBranchService {
    private final SubBranchRepository subBranchRepository;

    @Autowired
    public SubBranchService(SubBranchRepository subBranchRepository) {
        this.subBranchRepository = subBranchRepository;
    }

    public List<SubBranch> getSubBranchs() {
        return subBranchRepository.findAll();
    }

    public List<SubBranch> createSubBranchs(List<SubBranch> subBranchs) {
        return subBranchRepository.saveAll(subBranchs);
    }

    public SubBranch createSubBranch(SubBranch subBranch) {
        return subBranchRepository.save(subBranch);
    }

    public SubBranch updateSubBranch(String id, SubBranch subBranch) {
        return subBranchRepository.findById(id)
                .map(existingSubBranch -> {
                    existingSubBranch.setName(subBranch.getName());
                    existingSubBranch.setLocation(subBranch.getLocation());
                    return subBranchRepository.save(existingSubBranch);
                })
                .orElseThrow(() -> new RuntimeException("SubBranch not found with id: " + id));
    }

    public void deleteSubBranch(String id) {
        subBranchRepository.deleteById(id);
    }
}
