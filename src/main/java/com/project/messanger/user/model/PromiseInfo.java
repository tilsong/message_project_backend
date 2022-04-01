package com.project.messanger.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PromiseInfo {
    private int promise_id;
    private String promise_name;
    private  String promise_info;
    private Date promise_date;
    private String location;
}
