package com.springboot.springbootproject.miniApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainBranchService {
    private final MainBranchRepository mainBranchRepository;

    @Autowired
    public MainBranchService(MainBranchRepository mainBranchRepository){
        this.mainBranchRepository = mainBranchRepository;
    }

    public List<MainBranch> getMainBranchs() {
        return mainBranchRepository.findAll();
    }

    public List<MainBranch> saveAll(List<MainBranch> mainBranchs) {
        return mainBranchRepository.saveAll(mainBranchs);
    }

    public MainBranch save(MainBranch mainBranch) {
        return mainBranchRepository.save(mainBranch);
    }
    
}
