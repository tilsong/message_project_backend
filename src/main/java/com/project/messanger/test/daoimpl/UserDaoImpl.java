package com.project.messanger.test.daoimpl;

import com.project.messanger.test.Model.User;
import com.project.messanger.test.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    public static List<User> users = new ArrayList<>();

    static {

        users.add(new User(1, "testName1", "testId1", "1234"));
        users.add(new User(2, "testName2", "testId2", "1234"));
        users.add(new User(3, "testName3", "testId3", "1234"));
        users.add(new User(4, "testName4", "testId4", "1234"));
        users.add(new User(5, "testName5", "testId5", "1234"));

    }

    @Override
    public List<User> getAllUsers(){

        return users;
    }

    @Override
    public User getUserByUserId(String userId){
        return users.stream().filter(user -> user.getUserId().equals(userId)).findAny().orElse(new User(-1, "", "",""));
    }

    @Override
    public User insertUser(User user){
        System.out.println(user.toString());

        users.add(user);

        return user;
    }

    @Override
    public void updateUser(String userId, User user) {
        users
                .stream()
                .filter(curUser -> curUser.getUserId().equals(userId))
                .findAny()
                .orElse(new User(-1, "","",""))
                .setUserName(user.getUserName());
    }

    @Override
    public void deleteUser(String userId){
        users.removeIf(user -> user.getUserId().equals(userId));
    }
}
