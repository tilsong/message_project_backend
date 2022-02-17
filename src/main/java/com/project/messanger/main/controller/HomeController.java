package com.project.messanger.main.controller;


import com.project.messanger.main.model.Group;
import com.project.messanger.main.model.User;
import com.project.messanger.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/{idx}")
    public List<User> getAllUsers(@PathVariable String idx){

        System.out.println("getAllUsers start");


        int varidx = Integer.parseInt(idx);
        System.out.println("===== idx : " + varidx + " =====");

        List<User> userList = userService.getAllUsers();

        int countGroup = userService.getCountGroups(varidx);
        return userList;
    }

    @PostMapping("/{idx}")
    public int mainHompage(@PathVariable String idx){

        int user_idx = Integer.parseInt(idx);
        System.out.println("user idx : " + user_idx);

        int numberOfGroups = userService.getCountGroups(user_idx);

        return numberOfGroups;
    }

//    @GetMapping("/{id}")
//    public User getUser(@PathVariable String id){
//
//        return userService.getUser(id);
//    }
//
//    @GetMapping("/{idx}")
//    public List<Group> getGroup(@PathVariable String idx){
//        System.out.println("idx : " + idx);
//        List<Group> userGroupList = userService.getGroupes(idx);
//
//        return userGroupList;
//    }




}
