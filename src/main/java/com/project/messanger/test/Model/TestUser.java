package com.project.messanger.test.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class TestUser {
    private int idx;
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String email;

    public TestUser() {
    }

    // @AllArgsConstructor 이것으로 대체 가능
//    public TestUser(int idx, String id, String pwd, String name, String phone, String email) {
//        this.idx = idx;
//        this.id = id;
//        this.pwd = pwd;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//    }

    // get, set 은 @Data로 대체 가능
//    public int getIdx() {
//        return idx;
//    }
//
//    public void setIdx(int idx) {
//        this.idx = idx;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

//    @Override
//    public String toString() {
//        return "TestUser{" +
//                "idx=" + idx +
//                ", id='" + id + '\'' +
//                ", pwd='" + pwd + '\'' +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
