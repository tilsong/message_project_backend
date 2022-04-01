package com.project.messanger.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int groupId;
    private String groupName;
    private String groupInfo;
    private int userId;
}
