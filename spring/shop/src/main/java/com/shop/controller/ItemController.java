package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping(value="/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto,
                          BindingResult bindingResult,
                          Model model,
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){
        //ItemFormDto @NotBlank, @Min 등 조건에 걸리면 다시 입력 폼으로 돌려보냄
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        //2. 비즈니스 검증(첫번째 이미지 필수 체크)
        //신규등록(id == null) 인데 첫번째 이미지 파일이 비어 있으면 에러 메세지
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }
        try{
            //실제 저장 로직(서비스 호출)
            itemService.saveItem(itemFormDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }
        //성공 시 메인페이지로 이동
        return "redirect:/";
    }

    //수정상품 화면에 불러오기(이미지 포함)
    @GetMapping(value="/admin/item/{itemId}")
    public String itemDtl1(@PathVariable("itemId") Long itemId, Model model){
        try{
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }
        return "item/itemForm";
    }

    @PostMapping(value="/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,
                          BindingResult bindingResult,
                          Model model,
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){
        //ItemFormDto @NotBlank, @Min 등 조건에 걸리면 다시 입력 폼으로 돌려보냄
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        //2. 비즈니스 검증(첫번째 이미지 필수 체크)
        //신규등록(id == null) 인데 첫번째 이미지 파일이 비어 있으면 에러 메세지
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }
        try{
            //이전에 분석한 updateItem 서비스를 호출하여 DB와 파일을 갱신
            itemService.updateItem(itemFormDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }
        //성공 시 메인페이지로 이동
        return "redirect:/";
    }

    //다중 매핑 : 페이지번호가
    // 있는경우 /admin/items/1 페이지번호가 없는 경우 /admin/items
    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page,
                             Model model){
        //page.isPresent() ? page.get() : 0, 3 페이지 번호가 있으면 가져오고, 없으면 0(첫페이지) 3 한페이지 데이터 갯수(3)
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        //QueryDsl로 구현했던 getAdminItemPage 호출하여 조건에 맞는 상품데이터 Page를 가져옵니다.
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        //화면(view)에 전달
        model.addAttribute("items", items); //조회된 상품 데이터와 페이징 정보
        model.addAttribute("itemSearchDto", itemSearchDto); //검색 조건(입력한 창에 그대로 남겨두기 위함)
        model.addAttribute("maxPage", 5); //하단에 보여줄 페이지 번호의 최대갯수

        return "item/itemMng";
    }


    //상세보기
    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "item/itemDtl";

    }
}
