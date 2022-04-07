package com.project.messanger.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.messanger.user.dao.UserMapper;
import com.project.messanger.user.model.Group;
import com.project.messanger.user.model.PromiseInfo;
import com.project.messanger.user.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
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
     *   새로운 약속을 만들기 위한 그룹의 정보를 넘긴다
     *       들어오는 데이터 : 그룹ID
     * 2. newPromise
     *   새로운 약속을 그룹에 만든다
     *       들어오는 데이터 : 그룹ID, 그룹이름, 약속명, 날짜, 설명, 장소
     * 3. getAllPromise
     *   새로운 약속 생성, 모든 약속 리스트 확인을 위한 메소드
     *       들어오는 데이터 : 그룹ID, 약속ID, {계정ID}
     *
     * */
    public HashMap getGroupInfo(int gidx) {
        System.out.println("===== getGroupInfo start =====");
//        List groupInfo = userMapper.getGroupInfo(gidx);
//        if(id != null) {
//            ArrayList memeList = getGroupMember(id);
//            /*
//            * 막힘
//            * */
//        }else{
        System.out.println("gidx >> " + gidx);

        HashMap groupInfo = userMapper.getGroupInfo(gidx);

        System.out.println("informatil of group > " + groupInfo.toString());

            return groupInfo;
//        }
    }

    public HashMap newPromise(HashMap data) {
        System.out.println("===== newPromise start =====");

        PromiseInfo newPromise = new PromiseInfo();
        newPromise.setPromiseDate(Date.valueOf((String) data.get("date")));
        newPromise.setPromiseName((String) data.get("promise_name"));
        newPromise.setPromiseInfo((String) data.get("info"));
        newPromise.setLocation((String) data.get("location"));
        /*중복체크*/
        String check_promise_name = userMapper.checkPromiseId(newPromise.getPromiseName());
        if (newPromise.getPromiseName().equals(check_promise_name)) {
            System.out.println("===== 이미 존재하는 약속 =====");

            return null;
        } else {
            userMapper.newPromiseInfo(newPromise);
            Integer promiseId = userMapper.selectPromiseId(newPromise.getPromiseName());
            System.out.println("======================================================================");
            int groupId = Integer.parseInt(data.get("group_id").toString());

            System.out.println("groupId >>" +groupId);
            System.out.println("promiseId >>" + promiseId);

            int checkNewPromise = userMapper.newPromise(groupId,promiseId);

            System.out.println("checkNewPromise >> "  + checkNewPromise);
            System.out.println("======================================================================");
            HashMap promise = new HashMap();

            if(checkNewPromise != 1){
                return promise;
            }else{
                promise.put("groupId", groupId);
                promise.put("promiseId", promiseId);
                return promise;
            }
        }
    }

    //     리턴값이 null로 나온다. 사이즈도 정확한데 뭐가 문제일까?
    public ArrayList getAllPromise(int group_id) {
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

        ArrayList<Group> list = (ArrayList) userMapper.getAllKnowMember(userId);
        ArrayList<User> userList = userMapper.checkGreoupMember(userId);

        for(Group group : list){
            System.out.println("getAllKnowMember groupId >>> " + group.getGroupId());
        }
        return userList;
    }

    public ArrayList getGroupMember(String gidx, String id) {
        System.out.println("===== getGroupMember start =====");
        ArrayList<User> resultList = (ArrayList) userMapper.getGroupMember(gidx, id);
//        for (int i=0; i < resultList.size(); i++){
//            System.out.println("resultList" + i + ">> " + resultList.get(i).);
//        }

        for(User user : resultList){
            System.out.println("user userID >> " + user.getUserId());
            System.out.println("user id >> " + user.getId());
        }
        return resultList;
    }

    public void updateGroup(HashMap updateGroup) {
        System.out.println("===== SERVICE updateGroup start ======");

        ArrayList<User> userList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        int i=0;
        while (i<=updateGroup.size() ){
            System.out.println("updateGroup.get(\"user\"+i);" + updateGroup.get("user"+i));
            userList.add(mapper.convertValue(updateGroup.get("user"+i), com.project.messanger.user.model.User.class));

            if(userList.get(i) == null){
                System.out.println("user null!!!");
                break;
            }
            i++;
        }
        userList.remove(i);

        String groupId = mapper.convertValue(updateGroup.get("groupId"), String.class);
        String id = mapper.convertValue(updateGroup.get("id"), String.class);

        ArrayList<User> originMember = (ArrayList) userMapper.getGroupMember(groupId, id);
        if(userList.size() < originMember.size()){
            ArrayList<User> deleteUserLiust = originMember;

            int result = 0;
            for(int j=0; j < originMember.size(); j++){
                for(int k=0; k < originMember.size(); k++){
                    if(userList.get(j).getUserId() == originMember.get(k).getUserId()){
                        deleteUserLiust.remove(k);
                        System.out.println("remain Group User >> " + deleteUserLiust.toString());
                    }
                }
            }
            System.out.println("deleteUserList >> " + deleteUserLiust.toString());
            int delGroupId = mapper.convertValue(updateGroup.get("groupId"), Integer.class);
            for(User user : deleteUserLiust){
                result = userMapper.delGroupUser(user.getUserId(), delGroupId);
            }

        }else if(userList.size() > originMember.size()){
            ArrayList<User> addUserList = new ArrayList<>();
            int k=0;
            int result = 0;
             for(int j=0; j <= userList.size(); j++){
                if(originMember.size() < j){
                    System.out.println("you have to add member");
                    System.out.println("update Member >>> " + userList.get(j-1));
                    if(userList.size() < j){
                        System.out.println("더 이상 추가 되는 인원은 없음");
                        break;
                    }
                    addUserList.add(userList.get(j-1));
                    System.out.println("addUserList check >>> " + addUserList.get(k));

                    int addUserId = addUserList.get(k).getUserId();
                    int addGroupId = mapper.convertValue(updateGroup.get("groupId"), Integer.class);

                    result = userMapper.addGroupUser(addUserId, addGroupId);
                    System.out.println("db result >> " + result);
                    k++;
                }
            }
//             return result;
        }else{
            System.out.println("인원 변경 없음");
        }
    }

}
