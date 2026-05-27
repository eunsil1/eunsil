package com.example.boardloginimgsecurityoauth.web;

import com.example.boardloginimgsecurityoauth.security.SecurityUtils;
import com.example.boardloginimgsecurityoauth.service.UserService;
import com.example.boardloginimgsecurityoauth.web.dto.RegisterForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    public static final  String SESSION_USER = "loginUser";

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(HttpSession session){
        if(SecurityUtils.isAuthenticated()){
            return "redirect:/home";
        }
        return "login";
    }
//    @PostMapping("/login")
//    public String login(@RequestParam(required = false) String username,
//                        @RequestParam(required = false) String password,
//                        HttpSession session,
//                        Model model){
//        if (username == null || username.isBlank() || password == null || password.isBlank()) {
//            model.addAttribute("error", "아이디와 비밀번호를 입력하세요.");
//            return "login";
//        }
//        User user = userService.login(username.trim(), password);
//        if(user == null){
//            model.addAttribute("error","아이디 또는 비번이 올바르지 않아요");
//            model.addAttribute("username", username);
//            return "login";
//        }
//        session.setAttribute(SESSION_USER,user.getUsername());
//        session.setAttribute("loginName", user.getName());
//        return "redirect:/home";
//    }
    //Post/login (직접 UserService.login + 세션)
//    폼 로그인은 SecurityConfig의
//    http.formLogin(form -> form.loginPage("/login") ... )
//    때문에, 브라우저가 **POST /login**으로 아이디·비밀번호를내면 스프링 시큐리티 필터 체인이 받습니다.
//    인증은 **LoginUserDetailsService + PasswordEncoder**로 처리되고, 성공 시 **시큐리티가 세션에 SecurityContext(로그인 상태)**를 넣습니다.
//    그래서 session.setAttribute(SESSION_USER, ...) 같은 코드가 필요 없습니다.

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/posts";
    }






    @GetMapping("/register")
    public String registerForm(HttpSession session, Model model){
        if(SecurityUtils.isAuthenticated()){
            return "redirect:/home";
        }
        model.addAttribute("form", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("form") RegisterForm form,
                           BindingResult bindingResult,
                           Model model){
        if(bindingResult.hasErrors()){
            return "register";
        }
        if(!form.getPassword().equals(form.getPasswordConfirm())){
            model.addAttribute("error","비밀번호 불일치");
            return "register";
        }
        try{
            userService.register(form.getUsername(),form.getPassword(),form.getName());
        }catch(IllegalArgumentException e){
            model.addAttribute("error",e.getMessage());
            return "register";
        }
        return "redirect:/login?registered";
    }

}
