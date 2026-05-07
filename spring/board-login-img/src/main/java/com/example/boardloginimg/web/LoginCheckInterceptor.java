package com.example.boardloginimg.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//컨트롤러 읽기전에, 세션이라는 입장권이 있는지 검사
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        //이미 있는 세션을 가져오되, 없으면 새로만들지 말라
        if (session == null || session.getAttribute(AuthController.SESSION_USER) == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        //session == null 처음온사람 또는 세션만료된사람,
        //session.getAttribute(AuthController.SESSION_USER) == null 세션은 있지만 로그인성공증 표가 없는사람
        return true; //로그인 확인됐으면 통과
    }
}
