package com.example.roomfit.web;

import com.example.roomfit.domain.Member;
import com.example.roomfit.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("products", shopService.listAll());
        return "shop/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, @AuthenticationPrincipal Member member, Model model){
        model.addAttribute("product", shopService.getProduct(id)); //상품 1건
        model.addAttribute("reviews", shopService.getReviews(id)); //리뷰 리스트
        if(member != null){
            model.addAttribute("wished", shopService.isWished(member.getId(), id));
        }
        return "shop/detail";
    }

    @PostMapping("/{id}/cart")
    public String addCart(@PathVariable("id") Long id, @AuthenticationPrincipal Member member){
        shopService.addToCart(member.getId(), id, 1);
        return "redirect:/shop/cart";
    }
    //@PathVariable("id") - 상품을 담을 id


    //장바구니 페이지
    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal Member member, Model model) {
        model.addAttribute("cart", shopService.getCartWithItems(member.getId()));
        return "shop/cart";
    } // getCartWithItems - cart + items + product 한번에 조회

    //찜 토글
    @PostMapping("/{id}/wish")
    public String wish(@PathVariable("id") Long id, @AuthenticationPrincipal Member member) {
        shopService.toggleWishlist(member.getId(), id);
        return "redirect:/shop/" + id;
    }

    //로그인한 사용자가 상품에 리뷰(평점 + 글)를 등록하고, 다시 그 상품 상세페이지로 돌아가는 컨트롤러
    @PostMapping("/{id}/review")
    public String review(
            @PathVariable("id") Long id, //상품번호
            @AuthenticationPrincipal Member member, //현재 로그인한 사용자
            @RequestParam("rating") int rating, //폼의 평점
            @RequestParam("content") String content){ //후기내용
        shopService.addReview(member.getId(), id, rating, content);
        return "redirect:/shop/" + id;
    }
    //addReview(member.getId(), id, rating, content)
    //평점 1~5 , 이미 리뷰를 썼는지 검사(상품당 1번), ProductReview db에 저장
    //Product 평균 평점, 리뷰수 재계산 -> 상세페이지 이동
}

