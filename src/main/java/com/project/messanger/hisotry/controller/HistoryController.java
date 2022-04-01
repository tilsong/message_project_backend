package com.project.messanger.hisotry.controller;

import com.project.messanger.user.model.Promise;
import com.project.messanger.user.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/{idx}")
    public List<Promise> getPromiseHistory(@PathVariable int idx){
        System.out.println("History Promises idx : " + idx);

        List<Promise> historyPromiseList = historyService.getPromiseHistory();

        return historyPromiseList;
    }

}
