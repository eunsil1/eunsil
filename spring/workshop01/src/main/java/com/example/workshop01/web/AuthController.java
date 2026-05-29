package com.example.workshop01.web;

import com.example.workshop01.security.SecurityUtils;
import com.example.workshop01.service.UserService;
import com.example.workshop01.web.dto.RegisterForm;
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
