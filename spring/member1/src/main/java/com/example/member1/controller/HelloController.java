package com.example.member1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        //name 이라는 이름으로 "은실"이라는 값을 담습니다.
        model.addAttribute("name", "은실");
        return "hello"; //hello.html을 찾아 갑니다.
    }

    //주소창에 /hello/v2?name=홍길동
    @GetMapping("/hello/v2")
    public String helloV2(@RequestParam(value = "name", defaultValue = "이름없음") String name, Model model){

        model.addAttribute("name", name);
        return "hello"; //hello.html을 찾아 갑니다.
    }
}
