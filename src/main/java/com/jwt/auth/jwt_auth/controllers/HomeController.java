package com.jwt.auth.jwt_auth.controllers;


import com.jwt.auth.jwt_auth.models.User;
import com.jwt.auth.jwt_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String Home(){
        return "Home Page";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }
}
