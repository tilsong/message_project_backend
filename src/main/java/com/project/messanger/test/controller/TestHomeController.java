package com.project.messanger.test.controller;

import com.project.messanger.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class TestHomeController {

    @Autowired
    private TestService ts;

    @GetMapping("")
    public String test(){
        return "root url call";
    }

    @GetMapping("/json")
    public Map<String, String> jsonTest(){
        Map<String, String> res = new HashMap<>();
        res.put("test", "hihi");

        return res;
    }

    @GetMapping("/test")
    public Map<String, String> testMethod() {
        Map<String, String> res = this.ts.getTest();

        return res;
    }

}
