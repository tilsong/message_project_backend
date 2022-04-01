package com.project.messanger.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Promise {

    private int pro_idx;
    private int uidx;
     private int gidx;
    /*
    * pro_idx = PK로 약속 구분
    * udix = User의 idx, 이를 통하여 하나의 약속에 어떤 유저들이 있는지 조회가 가능!
    * gidx는 아직 미정..
    * 좀 더 확인하고 진행하도록!
    * */
}
