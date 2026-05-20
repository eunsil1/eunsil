package com.example.roomfit.service;

import com.example.roomfit.repository.CartRepository;
import com.example.roomfit.repository.ProductRepository;
import com.example.roomfit.repository.ProductReviewRepository;
import com.example.roomfit.repository.WishlistRepository;
import com.example.roomfit.domain.*;
import com.example.roomfit.exception.BusinessException;
import com.example.roomfit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final WishlistRepository wishlistRepository;
    private final ProductReviewRepository productReviewRepository;
    private final MemberService memberService;
    private final UserProfileService userProfileService;

    //쇼핑목록
    public List<Product> listAll(){
        return productRepository.findByOnSaleTrueOrderByAvgRatingDesc();
    }

    //상품 상세
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
    }

    //맞춤 소품 추천
    public List<Product> recommendProducts(Long memberId){
        UserProfile profile = userProfileService.findByMemberId(memberId);
        if(profile == null){
            return productRepository.findByOnSaleTrueOrderByAvgRatingDesc().stream().limit(8).toList();
        }
        //분기1 - 프로필 없음
        //판매중 상품 전체 -> 평점순 -> 상위 8개


        int maxPrice = profile.getBudget() * 10000;
        return productRepository.findByOnSaleTrueAndStyleTagAndPriceLessThanEqualOrderByAvgRatingDesc(profile.getPreferredStyle(), maxPrice)
                .stream()
                .limit(8)
                .toList();
        //분기2 - 프로필 있음
        //PreferredStyle - 같은 스타일 태그 budget() 예산 이하(budget() * 10000) 정렬 평점 높은 순
        //ex) budget = 150 (150만원) -> 150만원 이하 선호 스타일 상품만 추천
    }

    //회원 장바구니 있으면 그대로 반환
    //없으면 Cart 새로 생성 후 저장
    //회원 1명당 cartItem
    @Transactional
    public Cart getOrCreateCart(Long memberId){
        return cartRepository.findByMemberId(memberId).orElseGet(() -> {
            Member member = memberService.findById(memberId);
            return cartRepository.save(Cart.builder().member(member).build());
        });
    }

    @Transactional
    public void addToCart(Long memberId, Long productId, int quantity){
        Cart cart = getOrCreateCart(memberId); //장바구니 조회(없으면 생성)
        Product product = getProduct(productId); //상품을 조회
        CartItem item = cart.getItems().stream()//이미 장바구니 있는 상품인지 검사
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
        if(item == null){ //없으면 새로 추가
            cart.getItems().add(CartItem.builder().cart(cart).product(product).quantity(quantity).build());
        }else{ //수량만 추가
            item.setQuantity(item.getQuantity() + quantity);
        }

    }

//    @Transactional
//    public void addToCart(Long memberId, Long productId, int quantity) {
//
//        // 1. 장바구니 조회 (없으면 생성)
//        Cart cart = getOrCreateCart(memberId);
//
//        // 2. 상품 조회
//        Product product = getProduct(productId);
//
//        // 3. cart 안에 같은 상품이 있는지 찾기
//        CartItem foundItem = null;
//
//        for (CartItem cartItem : cart.getItems()) {
//
//            Long itemProductId = cartItem.getProduct().getId();
//
//            if (itemProductId.equals(productId)) {
//                foundItem = cartItem;
//                break;
//            }
//        }
//
//        // 4. 장바구니에 없으면 새로 추가
//        if (foundItem == null) {
//
//            CartItem newItem = new CartItem();
//
//            newItem.setCart(cart);
//            newItem.setProduct(product);
//            newItem.setQuantity(quantity);
//
//            cart.getItems().add(newItem);
//
//        }
//
//        // 5. 이미 있으면 수량 증가
//        else {
//
//            int currentQuantity = foundItem.getQuantity();
//
//            foundItem.setQuantity(
//                    currentQuantity + quantity
//            );
//        }
//    }

    //장바구니 화면을 조회
    @Transactional
    public Cart getCartWithItems(Long memberId){
        return cartRepository.findWithItemsByMemberId(memberId)
                .orElseGet(() -> getOrCreateCart(memberId));
    } //@EntityGraph로 items + product 한번에 로드
    //없으면 빈카트 생성
    //장바구니 + 장바구니 상품 + 상품 정보

    @Transactional
    public void toggleWishlist(Long memberId, Long productId){
        var existing = wishlistRepository.findByMemberIdAndProductId(memberId, productId);
        if(existing.isPresent()){ //이미 찜이 되어있으면
            wishlistRepository.delete(existing.get()); //delete - 찜해제
            return;
        }
        Member member = memberService.findById(memberId);
        Product product = getProduct(productId);
        wishlistRepository.save(Wishlist.builder().member(member).product(product).build()); //찜등록

    }

    //찜여부 확인
    public boolean isWished(Long memberId, Long productId) {
        return wishlistRepository.existsByMemberIdAndProductId(memberId, productId);
    }
    //상품 상세페이지에서 버튼 표시용(true / false)
    //읽기만 가능

    @Transactional
    public void addReview(Long memberId, Long productId, int rating, String content){
        if(rating < 1 || rating > 5){ //평점 1~5 허용
            throw new BusinessException("평점은 1~5 사이입니다.");
        }
        Product product = getProduct(productId);
        Member member = memberService.findById(memberId);
        if(productReviewRepository.findByProductIdAndMemberId(memberId,productId).isPresent()){ //상품당 회원 1 리뷰
            throw new BusinessException("이미 리뷰를 작성했습니다.");
        }
        productReviewRepository.save(ProductReview.builder()
                .product(product)
                .member(member)
                .rating(rating)
                .content(content)
                .build());
        recalculateRating(product);

    }

    private void recalculateRating(Product product){
        List<ProductReview> reviews = productReviewRepository.findByProductIdOrderByCreatedAtDesc(product.getId());
        //해당 상품 리뷰 전체 조회
        double avg = reviews.stream().mapToInt(ProductReview::getRating).average().orElse(0);
        product.setAvgRating(avg); //평균 평점
        product.setReviewCount(reviews.size()); //리뷰수
        //해당 상품 리뷰 전체조회 평균 평점 리뷰수를 product 반영 -> 목록, 추천 정렬(OrderByAvgRatingDesc)에 사용함
    }

    //리뷰목록(최신순 - member 포함)
    public List<ProductReview> getReviews(Long productId) {
        return productReviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
}
