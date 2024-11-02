package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    private final RegisterService registerService;
    private final LoginRepository loginRepository; // Add LoginRepository

    @Autowired
    public LoginService(RegisterService registerService, LoginRepository loginRepository) {
        this.registerService = registerService;
        this.loginRepository = loginRepository; // Inject LoginRepository
    }

    public Optional<Register> authenticate(String username, String password) {
        // Fetch all registered users and perform authentication
        List<Register> users = registerService.getRegisters();
        for (Register user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Save the user to the Login collection
                saveLogin(user);
                return Optional.of(user); // Successful authentication
            }
        }
        return Optional.empty(); // Return empty if not found or passwords don't match
    }

    private void saveLogin(Register user) {
        // Create a new Login object
        Login login = new Login();
        login.setUsername(user.getUsername());
        login.setPassword(user.getPassword()); // Store password securely in a real application

        // Save the login entry to the Login collection
        loginRepository.save(login);
    }
}
