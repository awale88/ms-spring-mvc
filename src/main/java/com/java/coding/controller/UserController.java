package com.java.coding.controller;

import com.java.coding.entity.User;
import com.java.coding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }
    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


}
