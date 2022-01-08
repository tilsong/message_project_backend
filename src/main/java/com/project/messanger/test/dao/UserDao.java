package com.project.messanger.test.dao;

import com.project.messanger.test.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    public List<User> getAllUsers();

    public User getUserByUserId(String userId);

    public User insertUser(User user);

    public void updateUser(String UserId, User user);

    public void deleteUser(String UserId);
}
