package com.project.messanger.config.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        System.out.println("req.getHeader check >> " +req.getHeader("Authorization"));

        // 토큰 : 코스 -> 토큰을 만들어야 한다
        // 언제? id, pwd가 정상적으로 들어와서 로그인이 완료 되면 토클을 만들어 주고 그걸 응답을 해준다.
        // 요청 할 때마다 header에 Authorizartion에 values값으로 토큰을 가지고 온다
        // 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 된다 ( RSA , HS256 )
        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println("headerAuth >>> " + headerAuth);
            System.out.println("필터");

            if(headerAuth.equals("messnager")){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                PrintWriter outPrintWriter = res.getWriter();
                outPrintWriter.println("no access!!");
            }
        }else{
            System.out.println("GET 요청 됨");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
