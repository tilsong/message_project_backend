package com.project.messanger.config.auth;

import com.project.messanger.user.dao.UserMapper;
import com.project.messanger.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===== PrincipalDetailsService의 loadUserByUsername 실행 =====");

        System.out.println("loadUserByUsername username >> " + username);
        User user = userMapper.getUser(username);

        System.out.println("loadUserByUsername user >> " + user.toString());

        return new PrincipalDetails(user);
    }
}
