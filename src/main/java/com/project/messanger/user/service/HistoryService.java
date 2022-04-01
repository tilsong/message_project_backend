package com.project.messanger.user.service;

import com.project.messanger.user.dao.HistoryMapper;
import com.project.messanger.user.model.Promise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    HistoryMapper historyMapper;

    public List<Promise> getPromiseHistory(){

        return historyMapper.getPromiseHistory();
    }

}
