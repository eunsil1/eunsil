package com.example.roomfit.web;

import com.example.roomfit.domain.CommunityBoardType;
import com.example.roomfit.domain.Member;
import com.example.roomfit.service.CommunityService;
import com.example.roomfit.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final ReportService reportService;

    @GetMapping
    public String list(
            @RequestParam(name = "board", defaultValue = "FREE")CommunityBoardType board,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model ){
        model.addAttribute("board", board); //선택한 게시판(탭강조 - 파란색)
        model.addAttribute("boards", CommunityBoardType.values()); // free/qna/review - 상단탭 버튼3개
        model.addAttribute("page",communityService.list(board, PageRequest.of(page, 15)));
        return "community/list";
        //communityService.list() - VISIBLE 글만 , author 포함 조회
    }
    //board - 게시판 종류, page - 페이지 번호(0부터  시작)
//→ GET /community
//→ GET /community?board=QNA
//→ GET /community?board=REVIEW&page=1

    @GetMapping("/write")
    public String writeForm(@RequestParam("board") CommunityBoardType board, Model model){
        model.addAttribute("board", board);
        return "community/form";
    }
    //writeForm - 커뮤니티 글쓰기 화면
    //→ GET /community/write?board=FREE
    //→ GET /community/write?board=QNA
    //→ GET /community/write?board=REVIEW

    @PostMapping("/write")
    public String write(
            @AuthenticationPrincipal Member member, //로그인한 Member
            @RequestParam("board") CommunityBoardType board,
            @RequestParam("title") String title,
            @RequestParam("content") String content){
        Long id = communityService.create(member.getId(), board, title, content);
        return "redirect:/community/" + id;
    }

    @GetMapping("/{id:\\d+}")
    public String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("post", communityService.getDetail(id));
        return "community/detail";
    }

    //신고접수
    @PostMapping("/report")
    public String report(
            @AuthenticationPrincipal Member member,
            @RequestParam("targetType") String targetType, //COMMUNITY_POST
            @RequestParam("targetId") Long targetId, //post.id - 현재 글 id
            @RequestParam("reason") String reason){ //신고 사유 - 사용자 입력
        reportService.report(member.getId(), targetType, targetId, reason);
        return "redirect:/community";
    }


}
