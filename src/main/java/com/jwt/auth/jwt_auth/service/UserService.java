package com.jwt.auth.jwt_auth.service;

import com.jwt.auth.jwt_auth.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> allUsers = new ArrayList<>();

    public UserService() {
        allUsers.add(new User(UUID.randomUUID().toString(), "user1", "user1@gmail.com"));
        allUsers.add(new User(UUID.randomUUID().toString(), "user2", "user2@gmail.com"));
    }
    public List<User> getAllUsers(){
        return this.allUsers;
    }
}
