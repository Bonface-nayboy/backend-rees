package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
public class RegisterService {
    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public List<Register> getRegisters() {
        return registerRepository.findAll();
    }

    public List<Register> saveAll(List<Register> registers) {
        return registerRepository.saveAll(registers); // Return the saved list
    }


}
