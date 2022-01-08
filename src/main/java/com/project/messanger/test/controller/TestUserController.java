package com.project.messanger.test.controller;

import com.project.messanger.test.Model.User;
import com.project.messanger.test.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TestUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{userid}")
    public User getUserByUserId(@PathVariable String userid){
        return userServiceImpl.getUserByUserId(userid);
    }

    @PostMapping("")
    public User registerUser(@RequestBody User user){
        return userServiceImpl.registerUser(user);
    }

    @PutMapping("/{userid}")
    public void modifyUser(@PathVariable String userid, @RequestBody User user){
        userServiceImpl.modifyUser(userid, user);
    }

    @DeleteMapping("/{userid}")
    public void removeUser(@PathVariable String userid){
        userServiceImpl.removeUser(userid);
    }
}
