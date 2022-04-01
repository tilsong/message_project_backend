package com.project.messanger.user.dao;
import com.project.messanger.user.model.Group;
import com.project.messanger.user.model.PromiseInfo;
import com.project.messanger.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Transactional
public interface UserMapper {

    public User getUser(String id);

    int newUser(User user);

    User checkUser(String id);
//    ArrayList getAllPromise(String id);

    public Integer getAllGroup(String id);

    public List getAllGroupMember(String id);

    public List getAllGroupName(String id);

    public HashMap getGroupInfo(int gidx);

    void newPromiseInfo(PromiseInfo newPromise);

    public int selectPromiseId(String promise_name);

    String checkPromiseId(String promise_name);

    void newPromise(HashMap promise);

    int getUserID(String id);
    public ArrayList<PromiseInfo> getAllPromise(String group_id);

    public  ArrayList<PromiseInfo> testPormiseInfo();

    HashMap getUpdateGroupInfo(int group_id, int promise_id);

    int updatePromise(PromiseInfo updatePromise);

    int newGroupInfo(Group newGroup);

    int getGroupId(Object group_name);

    int newGroup(Group newCreateGroup);

    List getAllKnowMember(String userId);
    
    List getGroupMember(String id);


}
