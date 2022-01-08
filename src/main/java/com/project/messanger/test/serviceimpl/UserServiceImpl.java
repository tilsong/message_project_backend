package com.project.messanger.test.serviceimpl;

import com.project.messanger.test.Model.User;
import com.project.messanger.test.daoimpl.UserDaoImpl;
import com.project.messanger.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDaoImpl userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getgAllUsers() {
        return null;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public User registerUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public void modifyUser(String userId, User user) {
        userDao.updateUser(userId, user);
    }

    @Override
    public void removeUser(String userId) {
        userDao.deleteUser(userId);
    }
}
