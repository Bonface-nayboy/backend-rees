package com.springboot.springbootproject.miniApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Register")
public class Register {
    @Id
     private String id;
     private String username;
     private String email;
     private String password;

    public Register() {
    }

    public Register(String id,
                    String username,
                    String email,
                    String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
