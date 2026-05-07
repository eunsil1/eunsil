package com.example.member2.controller;

import com.example.member2.dto.MemberDto;
import com.example.member2.entity.Member;
import com.example.member2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model){
        if(!model.containsAttribute("memberDto")){
            model.addAttribute("memberDto", new MemberDto());
        }
        return "members/create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute MemberDto dto, RedirectAttributes redirectAttributes){
        if(memberService.isEmailRegistered(dto.getEmail())){
            redirectAttributes.addFlashAttribute("errorMessage","이미 가입된 이메일입니다.");
            redirectAttributes.addFlashAttribute("memberDto",dto);
            return "redirect:/members/new";
        }
        memberService.create(dto);
        return "redirect:/members";
    }

    @GetMapping
    public String listForm(Model model){
        List<Member> memberList = memberService.findAll();
        model.addAttribute("members",memberList);
        return "members/list";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Member member = memberService.findById(id);
        if(member == null){
            return "redirect:/members";
        }
        MemberDto dto = new MemberDto(member.getId(),member.getUsername(),member.getEmail(),member.getPassword());
        model.addAttribute("memberDto", dto);
        return "members/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute MemberDto dto){
        memberService.update(id,dto);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        memberService.delete(id);
        return "redirect:/members";
    }

}
