package com.example.web;

import com.example.service.DemoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    private final DemoItemService demoItemService;

    public DemoController(DemoItemService demoItemService) {
        this.demoItemService = demoItemService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pageTitle","Thymeleaf 예제홈");
        return "index";
    }

    @GetMapping("/demo/text")
    public String text(Model model){
        model.addAttribute("plain", "일반 텍스트");
        model.addAttribute("withTag","<b>굵게</b> 태그 포함");
        model.addAttribute("pageTitle","th:text / th:utext");
        return "demo/text";
    }

    @GetMapping("/demo/condition")
    public String condition(Model model,
                            @RequestParam(name = "role", defaultValue = "guest") String role){
        model.addAttribute("role", role);
        model.addAttribute("count",3);
        model.addAttribute("pageTitle","th:if / th:switch");
        return "demo/condition";
    }

    @GetMapping("/demo/loop")
    public String loop(Model model){
        model.addAttribute("items", demoItemService.findAllOrdered());
        model.addAttribute("pageTitle","th:each (DB: demo_items)");
        return "demo/loop";
    }

    @GetMapping("/demo/form")
    public String formGet(Model model){
        model.addAttribute("pageTitle", "폼 th:object / th:field");
        model.addAttribute("demoForm", new DemoForm());
        return "demo/form";
    }

    @PostMapping("/demo/form")
    public String formPost(@ModelAttribute("demoform") DemoForm demoForm, Model model){
        model.addAttribute("pageTitle", "폼 제출 결과");
        model.addAttribute("demoForm",demoForm);
        model.addAttribute("submittedAt", LocalDateTime.now());
        return "demo/form-result";
    }

    @GetMapping("/demo/fragment")
    public String fragment(Model model){
        model.addAttribute("pageTitle", "프래그먼트");
        model.addAttribute("boxTitle","삽입된 박스");
        return "demo/fragment-page";
    }
}
