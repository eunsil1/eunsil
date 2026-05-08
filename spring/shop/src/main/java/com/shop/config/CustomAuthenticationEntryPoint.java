package com.shop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
//로그인 안됨 응답방식
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
    //commence - 인증 실패 시 자동 실행

    //실제 인증 실패가 발생하면 이 메서드가 호출 된다.
    //파라미터 요청/응답 객체와 예외 (AuthenticationException)가 들어옵니다.

    //인증되지 않은 요청을 받으면, 페이지 리다이렉트 같은 동작대신 401 Unauthorized 에러로 끝냄
}
