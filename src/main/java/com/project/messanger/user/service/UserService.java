package com.project.messanger.user.service;

import com.project.messanger.user.dao.UserMapper;
import com.project.messanger.user.model.Group;
import com.project.messanger.user.model.PromiseInfo;
import com.project.messanger.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUser(String id) {

        return userMapper.getUser(id);
    }

    public User newUser(User user) {
        System.out.println("SERVICE newuser object >> " + user);
        User returnUser = new User();

        returnUser = userMapper.checkUser(user.getId());
        if (returnUser != null) {
            System.out.println("기존에 존재하는 계정입니다.");
            return null;
        } else {
            int result = userMapper.newUser(user);
            if (result == 1) {
                System.out.println("SERVICE newuser insert success!");
                returnUser = userMapper.getUser(user.getId());
                System.out.println("return User >> " + returnUser.toString());

                return returnUser;
            } else {
                System.out.println("SERVICE newuser insert fail!");

                return null;
            }
        }

    }
    /*
     * 새로운 계정 회원가입
     * */

    /*
     * user의 로그인
     * */
    public String login(User user) {
        System.out.println("SERVICE login user id >> " + user.getId());
        System.out.println("SERVICE login user pwd >> " + user.getPwd());

        if (user.getPwd() != null) {
            return "/messanger/v1/user";
        }
        return "/messanger/v1/joinForm";
    }

    /*
     * Main Page
     * 1. getAllGroup
     *   계정이 속한 모든 그룹의 수를 가져온다
     * 2. getAllGroupMembers
     *   계정이 속한 각 그룹별 모든 멤버의 id를 가져온다
     * 3. getAllGroupName
     *   계정이 속한 각 그룹의 이름을 가져온다
     * 4. getUserID
     *   계정의 userID 를 가져온다
     * */
    public int getAllGroup(String id) {
        System.out.println("===== getAllGroup start =====");
        return userMapper.getAllGroup(id);
    }

    public ArrayList getAllGroupMembers(String id) {
        System.out.println("====== getAllGroupMembers start ======");
        ArrayList dataList = (ArrayList) userMapper.getAllGroupMember(id);

        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(id + "의 그룹 멤버 이름 group_id >> " + dataList.get(i));
        }

        return dataList;
    }

    public ArrayList getAllGroupName(String id) {
        System.out.println("====== getAllGroupName start ======");
        ArrayList returnList = (ArrayList) userMapper.getAllGroupName(id);

        for (int i = 0; i < returnList.size(); i++) {
            System.out.println("groupname data group_id : " + returnList.get(i));
        }

        return returnList;
    }

    public int getUserID(String id) {
        return userMapper.getUserID(id);
    }

    /*
     * New Promise Page
     * 1. getGroupInfo
     *   새로운 약속을 만들기 위해 약속을 만들기 위한 그룹의 정보를 넘긴다
     *       들어오는 데이터 : 그룹ID
     * 2. newPromise
     *   새로운 약속을 그룹에 만든다
     *       들어오는 데이터 : 그룹ID, 그룹이름, 약속명, 날짜, 설명, 장소
     * 3. getAllPromise
     *   새로운 약속 생성, 모든 약속 리스트 확인을 위한 메소드
     *       들어오는 데이터 : 그룹ID, 약속ID, {계정ID}
     *
     * */
    public HashMap getGroupInfo(int gidx, String id) {
        System.out.println("===== getGroupInfo start =====");
//        List groupInfo = userMapper.getGroupInfo(gidx);
//        if(id != null) {
//            ArrayList memeList = getGroupMember(id);
//            /*
//            * 막힘
//            * */
//        }else{
            HashMap groupInfo = userMapper.getGroupInfo(gidx);

            System.out.println("group_id > " + groupInfo.get("groupId"));
            System.out.println("group_name > " + groupInfo.get("groupName"));

            return groupInfo;
//        }
    }

    public HashMap newPromise(HashMap data) {
        System.out.println("===== newPromise start =====");

        PromiseInfo newPromise = new PromiseInfo();
        newPromise.setPromise_date(Date.valueOf((String) data.get("date")));
        newPromise.setPromise_name((String) data.get("promise_name"));
        newPromise.setPromise_info((String) data.get("info"));
        newPromise.setLocation((String) data.get("location"));
        /*중복체크*/
        String check_promise_name = userMapper.checkPromiseId(newPromise.getPromise_name());
        if (newPromise.getPromise_name().equals(check_promise_name)) {
            System.out.println("===== 이미 존재하는 약속 =====");

            return null;
        } else {
            userMapper.newPromiseInfo(newPromise);
            Integer promise_id = userMapper.selectPromiseId(newPromise.getPromise_name());

            HashMap promise = new HashMap();
            promise.put("group_id", data.get("group_id"));
            promise.put("promise_id", promise_id);

            userMapper.newPromise(promise);

            return promise;
        }
    }

    //     리턴값이 null로 나온다. 사이즈도 정확한데 뭐가 문제일까?
    public ArrayList getAllPromise(String group_id) {
        System.out.println("====== getAllPromise start ======");

        System.out.println("group_id >> " + group_id);
        ArrayList<PromiseInfo> promiseList = userMapper.getAllPromise(group_id);
        ArrayList<PromiseInfo> testlist = userMapper.testPormiseInfo();
//        for(int i=0; i < promiseList.size(); i++){
//            System.out.println("promise list " + i + "번째 >> " + promiseList.get(i).getPromise_id());
//            System.out.println("promise list " + i + "번째 >> " + promiseList.get(i).getPromise_name());
//        }

        return promiseList;
    }

    /*
     * Update Promise Page
     *
     * 1. getUpdateGroupInfo
     *   특정 약속을 수정하기 위해 그룹의 정보와 약속의 정보를 가져온다
     * */
    public HashMap getUpdateGroupInfo(int group_id, int promise_id) {
        System.out.println("===== getUpdateGroupInfo start ======");
        System.out.println("getUpdateGroupInfo group_id >> " + group_id);
        System.out.println("getUpdateGroupInfo promise_id >> " + promise_id);

        HashMap updateMap = userMapper.getUpdateGroupInfo(group_id, promise_id);
        System.out.println("updateMap promise_id >> " + updateMap.get("promise_id"));
        System.out.println("updateMap promise_name >> " + updateMap.get("promise_name"));
        System.out.println("updateMap promise_info >> " + updateMap.get("promise_info"));
        System.out.println("updateMap group_id >> " + updateMap.get("group_id"));

        return updateMap;
    }

    public int updatePromise(PromiseInfo updatePromise) {
        System.out.println("===== updatePromise start ======");
        System.out.println("udpatePromise >> " + updatePromise.toString());

        int result = userMapper.updatePromise(updatePromise);
        return result;
    }

    public HashMap newGroup(HashMap newGroup) {
        System.out.println("====== newGroup start ======");
        Group newCreateGroup = new Group();
        newCreateGroup.setGroupName(newGroup.get("groupName").toString());
        newCreateGroup.setGroupInfo(newGroup.get("groupInfo").toString());
        int userId = Integer.parseInt(newGroup.get("userId").toString());
        newCreateGroup.setUserId(userId);

        System.out.println("newCreateGroup >> " + newCreateGroup.toString());

        int mid = userMapper.newGroupInfo(newCreateGroup);
        int result = 0;
        HashMap resultMap = new HashMap();

        System.out.println("mid result >> " + mid);
        if (mid == 1) {
            System.out.println("group update 시작");
            newCreateGroup.setGroupId(userMapper.getGroupId(newGroup.get("groupName")));
            System.out.println("groupID >> " + newCreateGroup.getGroupId());
            result = userMapper.newGroup(newCreateGroup);

            resultMap.put("successResult", result);
            resultMap.put("groupId", newCreateGroup.getGroupId());
            resultMap.put("userId", userId);

            return resultMap;
        } else {
            System.out.println("newGroup 생성 실패");
            resultMap.put("successResult", 0);
            resultMap.put("groupId", "null");
            resultMap.put("userId", userId);
            return resultMap;
        }
    }

    public ArrayList getAllKnowMember(String userId) {
        System.out.println("====== getAllKnowMember start =======");

        ArrayList userList = (ArrayList) userMapper.getAllKnowMember(userId);


        for(Object userMap : userList){
            System.out.println("resultMap groupId >>> " + userMap.toString());
        }
        return userList;
    }

    public ArrayList getGroupMember(String id) {
        System.out.println("===== getGroupMember start =====");
        return (ArrayList) userMapper.getGroupMember(id);
    }


//    public ArrayList getAllPromise(String id){
//        ArrayList allPromises = userMapper.getAllPromise(id);
//
//        for(Object promise : allPromises){
//            System.out.println(id + "의 약속 >> " + promise.toString());
//        }
//        return allPromises;
//    }

}
