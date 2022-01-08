package com.project.messanger.test.controller;

import com.project.messanger.test.Model.TestUser;
import com.project.messanger.test.serviceimpl.MariadbTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mariadb")
public class MariadbTestContorller {

    @Autowired
    private MariadbTestServiceImpl mariadbTestService;

//    public MariadbTestContorller(MariadbTestServiceImpl mariadbTestService) {
//        this.mariadbTestService = mariadbTestService;
//    }

    @GetMapping("")
    public List<TestUser> allUser(){

        System.out.println("=====================MatiaDB Test Call of allUser Method!=====================");

        List<TestUser> userList = mariadbTestService.getAllUsers();

        return userList;
    }
}
