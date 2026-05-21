package com.example.roomfit.service;

import com.example.roomfit.domain.Product;
import com.example.roomfit.dto.ProductFormDto;
import com.example.roomfit.exception.ResourceNotFoundException;
import com.example.roomfit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    //관리자 상품 목록에 쓰는 조회 메서드
    public Page<Product> findProducts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return productRepository.findAllByOrderByIdDesc(pageable); //전체상품 id 내림차순
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByIdDesc(keyword.trim(), pageable);
        //이름 부분 일치(대소문자 무시), id 내림차순
    }

    //상품 1건 조회 -> 수정 시 사용
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
    }

    //entity -> dto
    public ProductFormDto getForm(Long id){
        Product product = getProduct(id);
        ProductFormDto dto = new ProductFormDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setStyleTag(product.getStyleTag());
        dto.setOnSale(product.isOnSale());
        return dto;
    }

    @Transactional
    public Long create(ProductFormDto form, MultipartFile image) throws IOException{
        String imagePath = resolveImagePath(image, null);
        Product product = Product.builder()
                .name(form.getName())
                .price(form.getPrice())
                .stock(form.getStock())
                .styleTag(form.getStyleTag())
                .onSale(form.isOnSale())
                .imagePath(imagePath)
                .build();
        return productRepository.save(product).getId();
    }

    private String resolveImagePath(MultipartFile image, String currentPath) throws IOException{
        String uploaded = fileStorageService.storeProductImage(image);
        if(uploaded != null){
            return  uploaded;
        }
        return  currentPath;
        //파일 있음 -> 디스크 저장 - /uploads/product/uuid.jpg 반환 -> 사용
        //null / 비어있음 -> null 반환 -> currentPath 그대로 사용
    }

    @Transactional
    public void update(Long id, ProductFormDto form, MultipartFile image) throws IOException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());
        product.setStyleTag(form.getStyleTag());
        product.setOnSale(form.isOnSale());
        String imagePath = resolveImagePath(image, product.getImagePath());
        if(imagePath != null){
            product.setImagePath(imagePath);
        }
    }
    //상품 조회 -> 상품 없으면 예외 -> name, price, stock 수정
    //이미지 처리 - 이미지 없음 -> 기존유지 이미지 있음 -> 새파일로 저장
    //트랜잭션 종료
    //update sql - 변경 감지

    //판매중지(소프트 삭제)
    @Transactional
    public void discontinue(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        product.setOnSale(false);
    }


}
