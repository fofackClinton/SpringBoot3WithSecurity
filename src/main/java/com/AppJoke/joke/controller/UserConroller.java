package com.AppJoke.joke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.AppJoke.joke.entities.User;
import com.AppJoke.joke.service.UserService;

@Controller
@CrossOrigin(origins = "*")
public class UserConroller {

    @Autowired
    UserService userService;

    @GetMapping("all")
    @PreAuthorize("hasAuthority('ADMIN')")  
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
