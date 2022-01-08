package com.project.messanger.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int idx;
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String email;

}
