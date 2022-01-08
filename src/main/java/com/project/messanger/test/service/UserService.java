package com.project.messanger.test.service;

import com.project.messanger.test.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    public List<User> getgAllUsers();

    public User getUserByUserId(String userId);

    public User registerUser(User user);

    public void modifyUser(String UserId, User user);

    public void removeUser(String userId);

}
