package com.project.messanger.test.Model;

public class User {
    private int userNo;
    private String userName;
    private String userId;
    private String userPassword;

    public User() {
    }

    public User(int userNo, String userName, String userId, String userPassword) {
        this.userNo = userNo;
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
