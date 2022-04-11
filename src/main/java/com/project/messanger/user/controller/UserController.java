package com.project.messanger.user.controller;


import com.project.messanger.user.model.Group;
import com.project.messanger.user.model.PromiseInfo;
import com.project.messanger.user.model.User;
import com.project.messanger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("messanger/v1")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // Test
//    @GetMapping("")
//    public List<User> getAllUsers(){
//        List<User> userList = userService.getAllUsers();
//
//        return userList;
//    }

    @GetMapping("user/main")
    public @ResponseBody HashMap main(@RequestBody User user){

        System.out.println("========== test 'user/main' start ==========");
        System.out.println("'user/main' id >> " + user.getId());

        int groupConut = userService.getAllGroup(user.getId());
        ArrayList groupMemberList = userService.getAllGroupMembers(user.getId());
        ArrayList groupName = userService.getAllGroupName(user.getId());
        int user_id = userService.getUserID(user.getId());
        /*
        * 1. JWT토큰을 통하여 인증이 된 사용자들만 볼 수 있는 화면
        * 2. 여기서 사용자가 선택한 페이지로 이동
        * 3. createPromise , createGroup
        * */

        HashMap mainDataMap = new HashMap();
        mainDataMap.put("groupCount" , groupConut);
        mainDataMap.put("groupMemberList", groupMemberList);
        mainDataMap.put("groupName" , groupName);
        mainDataMap.put("userId", user_id);

        for(int i=0; i < mainDataMap.size(); i++){
            System.out.println("mainDataMap groupCount >> " + mainDataMap.get("groupCount"));
            System.out.println("mainDataMap groupMemberList >> " + mainDataMap.get("groupMemberList"));
            System.out.println("mainDataMap groupName >> " + mainDataMap.get("groupName"));
        }
        System.out.println("mainDataMap user-id >> " + mainDataMap.get("userId"));
        return mainDataMap;
    }

    @GetMapping("user")
    public @ResponseBody String userMain(){
        /*
        * JWT토큰을 통해 인증이 된 사용자!
        * */
        return "user login success!";
    }

    @Secured("ROLE_USER")
    @PostMapping("user/post")
    public @ResponseBody String userMainPost(){

        User user = new User();
        return "POST >> user login success!";
    }

    @PostMapping("login")
    public RedirectView  login(@RequestBody User user){
        System.out.println("login user id >> " + user.getId());
        System.out.println("login user pwd >> " + user.getPwd());

        /*
        * 0. 가장 먼저 보여야 한다
        * 1. 로그인 페이지 이동
        * 2. 회원이 아니라면 join페이지로 이동
        *
        *  1. 로그인을 할 때, id - password 데이터 전달 받음
         * 2. JWT 토큰 새로 생성
         * 3. JWT 토큰으로 저장
         * 4. 이후 main 페이지로 이동
        * */
//        String url = userService.login(user);
        return new RedirectView(userService.login(user));
    }
    @PostMapping("join")
    public User join(@RequestBody User user){
        System.out.println("===== join start =====");

        System.out.println("join user id >> " + user.getId());
        System.out.println("join user pwd >> " + user.getPwd());
        System.out.println("join user pwd >> " + user.getName());
        System.out.println("join user pwd >> " + user.getEmail());

        System.out.println("====== new user join ======");
        user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
        user.setRole("ROLE_USER");

        System.out.println("new user >>> " + user.toString());

        User returnUser = userService.newUser(user);

        return returnUser;
        /*
         * 1. joinForm()를 통하여 User객체로 된 데이터를 전달 받음
         * 2. UserService로 데이터를 전달하여 DB에 저장하도록 한다
         * 3. 저장에 성공을 하게되면 로그인페이지로 이동 redirec:loginform
         * */
    }

    @GetMapping("user/createPromiseForm")
//    public HashMap createPromiseForm(@RequestParam("groupId") String groupId, @RequestParam("userID") String userID){
    public HashMap createPromiseForm(@RequestParam("groupId") String groupId){

        System.out.println("===== createPromiseForm start =====");
        System.out.println(groupId);
        int gidx = Integer.parseInt(groupId);
        String id = null;
        HashMap groupInfo = userService.getGroupInfo(gidx);

        return groupInfo;
        /*
        * 1. 계정 정보, 계정이 속한 그룹 중 약속을 생성 할 그룹의 정보를 가져온다
        * 2. 새로운 약속 생성 페이지로 전달
        * */
    }

    @PostMapping("user/createPromise")
    public @ResponseBody ArrayList createPromise(@RequestBody HashMap groupInfo){

        System.out.println("===== user/createPromise start ======");
//        System.out.println("groupInfo group_id > " + groupInfo.get("group_id"));
//        System.out.println("groupInfo group_name > " + groupInfo.get("group_name"));
//        System.out.println("groupInfo promise_name > " + groupInfo.get("promise_name"));
//        System.out.println("groupInfo date > " + groupInfo.get("date"));
//        System.out.println("groupInfo info > " + groupInfo.get("info"));
//        System.out.println("groupInfo location > " + groupInfo.get("location"));

        HashMap promise = userService.newPromise(groupInfo);

        if(promise !=null ){

            // 새로운 약속 생성 성공 후 회원의 전체 그룹들과 약속 정보들을 리턴
            System.out.println("group_id >> " + promise.get("groupId"));
            System.out.println("promise_id >> " + promise.get("promiseId"));

            int group_id = Integer.parseInt(promise.get("groupId").toString());

            return userService.getAllPromise(group_id);

        }else{
            return null;
        }
        /*
        * 1. 약속 생성 페이지에서 데이터 받음
        * 2. UserService에서 데이터를 DB에 저장
        * 3. 약속 리스트 페이지로 이동  redirect:promiseList
        * */
    }

    @GetMapping("user/updatePormiseForm")
    public HashMap updatePromiseForm(@RequestParam("group_id") String groupId, @RequestParam("promise_id") String promiseId ){
        System.out.println("===== updatePromise start ======");
        int group_id = Integer.parseInt(groupId);
        int promise_id = Integer.parseInt(promiseId);
        HashMap updatePromiseMap =  userService.getUpdateGroupInfo(group_id, promise_id);

        return updatePromiseMap;
    }

    @PostMapping("user/updatePromise")
    public int updatePromise(@RequestBody PromiseInfo updatePromise){
        System.out.println("====== user/updatePromise start ======");

        return userService.updatePromise(updatePromise);
    }

    @PostMapping("user/createGroupForm")
    public Map createGroupForm(@RequestBody User user){
        System.out.println("===== createGroupForm start ======");
        System.out.println("user id >> " + user.getId());

        int user_id = userService.getUserID(user.getId());
        HashMap data = new HashMap();
        data.put("userId" , user_id);
        System.out.println("userId : " + data.get("userId"));

        return data;
    }

    @PostMapping("user/createGroup")
    public HashMap createGroup(@RequestBody HashMap newGroup) {
        System.out.println("====== createGroup start =====");
        System.out.println(newGroup.get("userId").getClass());
        System.out.println(newGroup.get("groupName"));
        System.out.println(newGroup.get("groupInfo"));

        HashMap resultMap = userService.newGroup(newGroup);

        return resultMap;
    }

    @PostMapping("user/createGroup/searchMemberForm")
    public ArrayList searchMemberForm(@RequestParam("userId") String userId){
        System.out.println("====== searchMemberForm start =====");

        System.out.println("userId >> " + userId);
        ArrayList resultList = userService.getAllKnowMember(userId);


        for(int i=0; i<resultList.size(); i++){
            System.out.println("resultList " +  i + " >> "  + resultList.get(i));
        }

        return resultList;
    }
    /*
    * 파라미터로 리스트를 받을 때 마다 JSON parse error: Cannot deserialize value of type `java.util.ArrayList<java.lang.String>` from Object value (token `JsonToken.START_OBJECT`)
    * 라는 에러가 난다.
    * 검색으로 헤결 해보려 했지만 해결이 되지 않는다.
    * */
    @PostMapping("user/createGroup/searchMember")
    public String searchMember(@RequestBody ArrayList<String>  name){
        System.out.println("======= searchMember start =======");

        System.out.println("names > " +  name);

        return "searchMember";
    }

    @PostMapping("user/createGroup/updateGroupForm")
    public HashMap updateGroupForm(@RequestParam("groupId") String groupId,@RequestParam("id") String id){
        System.out.println("===== updateGroupForm start =====");
        System.out.println("groupId > " + groupId);
        System.out.println("id > " + id);

        int gidx = Integer.parseInt(groupId);
        HashMap groupInfo = userService.getGroupInfo(gidx);
        ArrayList<User> userList =  userService.getGroupMember(groupId, id);

        for(int i=0; i < userList.size(); i++){
            groupInfo.put("user" + i , userList.get(i));
        }


        return groupInfo;
    }

    @PostMapping("user/createGroup/updateGroup")
    public int updateGroup(@RequestBody HashMap updateGroup){
        System.out.println("====== CONTROLLER updateGroup start ======");
        for(int i=0; i< updateGroup.size(); i++){
            System.out.println("updateGroup groupId >> " + updateGroup.get("groupId"));
            System.out.println("updateGroup groupName >> " + updateGroup.get("groupName"));
            System.out.println("updateGroup groupInfo >> " + updateGroup.get("groupInfo"));
            System.out.println("updateGroup id >> " + updateGroup.get("id"));
            System.out.println("updateGroup groupMember >> " + updateGroup.get("user"+i));
        }
        /*
        * 1 = 모든 프로세스 정상 실행
        * 0 = 프로세스 장애 발생
        * -1 = 인원에 대한 변경사항이 없을 때 진행
        *
        * */
        int result = userService.updateGroup(updateGroup);
        System.out.println("updateGroup result : " + result);


        return result;
    }
}
