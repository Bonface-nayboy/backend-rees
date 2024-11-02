package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public List<Register> getRegisters() {
        return registerService.getRegisters(); // Use getRegisters() here
    }

    @PostMapping
    public List<Register> createRegister(@RequestBody List<Register> registers) {
        return registerService.saveAll(registers);
    }


}
