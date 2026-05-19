package com.example.roomfit.web;

import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.Member;
import com.example.roomfit.dto.InteriorPostFormDto;
import com.example.roomfit.service.InteriorPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/interior")
@RequiredArgsConstructor
public class InteriorController {

    private final InteriorPostService interiorPostService;

//    /interior?style=MINIMAL
//    /interior?page=1
//    /interior?style=SCANDINAVIAN&page=0

    @GetMapping
    public String List(
            @RequestParam(name = "style", required = false) InteriorStyle style,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model
            ){
        var pageable = PageRequest.of(page, 12);
        model.addAttribute("page", interiorPostService.listForView(style, pageable));
        //글목록 + 썸네일 조회
        model.addAttribute("style", style);
        model.addAttribute("styles", InteriorStyle.values());
        return "interior/list";
    }

    // /interior/1
    @GetMapping("/{id}")
    public String detail(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal Member member,
            Model model) {
        Long memberId = member != null ? member.getId() : null; //로그인하면 회원 아니면 null
        var view = interiorPostService.loadDetailPage(id, memberId);
        //노출글 조회(visible, images, author) + 조회수 + 1 + 작성자 썸네일 미리로드, 댓글 목록, 좋아요 눌렀는지
        model.addAttribute("post", view.post());
        model.addAttribute("comments", view.comments());
        model.addAttribute("liked", view.liked());
        return "interior/detail";
    }

    @PostMapping("/write")
    public String write(
            @AuthenticationPrincipal Member member,
            @Valid @ModelAttribute("form") InteriorPostFormDto form,
            BindingResult bindingResult,
            @RequestParam(name = "image", required = false) MultipartFile image,
            Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("styles", InteriorStyle.values());
            return "interior/form";
        }
        Long id = interiorPostService.create(member.getId(), form, image);
        return "redirect:/interior/" + id;
    }

    @PostMapping("/{id}/like")
    public String like(@PathVariable("id") Long id, @AuthenticationPrincipal Member member) {
        interiorPostService.toggleLike(id, member.getId());
        return "redirect:/interior/" + id;
    } //아직 안눌림 PostLike 저장, likeCount + 1

    //이미 눌림 PostLike 삭제, likeCount - 1

    //댓글, 대댓글 등록
    @PostMapping("/{id}/comment")
    public String comment(
            @PathVariable("id") Long id, //댓글 달 인테리어 글번호
            @AuthenticationPrincipal Member member, //댓글 작성자
            @RequestParam("content") String content, //댓글 본문
            @RequestParam(name = "parentId", required = false) Long parentId) {
        //parentId 없으면 -> 일반 댓글 있으면 -> 해당 댓글에 답글
        interiorPostService.addComment(id, member.getId(), content, parentId);
        //글 존재 확인 -> 댓글 저장(PostType = interior, postId = id, author = member)
        //parentId 있으면 Comment.parent 연결
        //commentCount를 db에서 다시 세어 글에 반영
        return "redirect:/interior/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal Member member, //로그인 사용자(필수)
            RedirectAttributes ra) {
        interiorPostService.delete(id, member.getId());
        ra.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/interior";
    }
}
