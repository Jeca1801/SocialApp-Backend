package com.example.socialapp.api.controller;

import com.example.socialapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(){
        return "hej";
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User</h1>");
    }
    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }
}
