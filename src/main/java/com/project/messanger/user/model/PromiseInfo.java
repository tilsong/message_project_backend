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
    private int promiseId;
    private String promiseName;
    private  String promiseInfo;
    private Date promiseDate;
    private String location;
}
