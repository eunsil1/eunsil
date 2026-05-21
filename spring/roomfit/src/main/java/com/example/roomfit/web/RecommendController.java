package com.example.roomfit.web;

import com.example.roomfit.domain.Member;
import com.example.roomfit.service.RecommendService;
import com.example.roomfit.service.ShopService;
import com.example.roomfit.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;
    private final UserProfileService userProfileService;
    private final ShopService shopService;

    @GetMapping
    public String survey(@AuthenticationPrincipal Member member){
        if(userProfileService.findByMemberId(member.getId()) == null){
            return "redirect:/member/profile";
        }
        return "redirect:/recommend/result";
    }

    @GetMapping("/result")
    public String result(@AuthenticationPrincipal Member member, Model model){
        if(userProfileService.findByMemberId(member.getId()) == null){
            return "redirect:/member/profile";
        }
        model.addAttribute("result", recommendService.getRecommendations(member.getId()));
        //추천 게시글 15개, 컬러 팔레트, 레이아웃 조건 선호 스타일
        model.addAttribute("products", shopService.recommendProducts(member.getId()));
        //회원 맞춤 상품 추천 - 미니멀 스타일 좋은방 화이트톤 이면 화이트색상 접이식 침대 미니멀 스탠드 추천
        return "recommend/result";
    }

}
