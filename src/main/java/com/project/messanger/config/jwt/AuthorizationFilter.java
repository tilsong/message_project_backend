package com.project.messanger.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.messanger.config.auth.PrincipalDetails;
import com.project.messanger.user.model.User;
import com.project.messanger.user.dao.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 시큐리티가 filter를 가지고 있는데 그 중 BasicAuthentixationFilter라는 것이 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때, 위 필터를 무조건 타게되어 있다
// 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터(BasicAuthentixationFilter라는)를 타지 않는다
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private UserMapper userMapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userRepository) {
        super(authenticationManager);
        this.userMapper = userRepository;
    }

    /*
    * 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게된다
    *
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청이 들어왔다!!!");

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        // header 가 있는지 확인
        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        System.out.println("jwtHeader >> " + jwtHeader);
        // JWT를 Header에서 받으면  검증을 해서 정상적인 사용자인지 확인\
        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
        System.out.println("AuthorizationFilter jwtToken >> " + jwtToken);

        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();
        System.out.println("AuthorizationFilter username >> " + username);
//        서명이 정상적으로 됨
        if(username != null){

            System.out.println("username 정상");

            User userEntity = userMapper.getUserById(username);

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            // Jwt토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            // 강제로 시큐리티의 세션에 접근하여 Authentication객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
