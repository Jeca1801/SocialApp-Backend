package com.example.socialapp.api.controller;

import com.example.socialapp.api.exception.UserServiceException;
import com.example.socialapp.api.models.User;
import com.example.socialapp.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController( UserService userService){
        this.userService = userService;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            throw new UserServiceException(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}