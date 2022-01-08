package com.project.messanger.main.service;

import com.project.messanger.main.dao.UserMapper;
import com.project.messanger.main.model.Group;
import com.project.messanger.main.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserMapper userMapper;

    public UserService(UserMapper mapper){
        this.userMapper = mapper;
    }

    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public User getUser(String id){
        return userMapper.getUser(id);
    }

    public List<Group> getGroupes(String idx) {
        return userMapper.getGroupes(idx);
    }
}
