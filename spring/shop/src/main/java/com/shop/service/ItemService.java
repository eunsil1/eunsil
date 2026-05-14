package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto,
                          List<MultipartFile> itemImgFileList) throws Exception {
        //상품등록
        //화면에 입력한 데이터가 담긴 Dto를 실제 DB와 연결될 item 엔티티로 변환
        Item item = itemFormDto.createItem();
        itemRepository.save(item); //상품에 기본 정보 저장 - 그래야 상품 id 생김

        //이미지등록 - 이미지 갯수만큼 반복하면서
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item); //이미지가 어떤 상품에 속하는지 연결(외래키 설정)

            //대표 이미지 설정
            if (i == 0) //첫번째 이미지 대표이미지 (index = 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");
            //이전에 분석했던 saveItemImg 메서드 호출 후 저장
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    //특정상품 상세정보를 조회하여 화면(수정페이지)에 전달하여 entity -> dto
    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId){
        //해당 상품에 연결된 이미지들을 id 오름차순으로 조회
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        //이미지 엔티티(itemImg) 리스트를 dto(itemImgDto)리스트로 변환
        for(ItemImg itemImg : itemImgList ){
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg); //변환메서드 호출
            itemImgDtoList.add(itemImgDto);
        }
        //상품의 기본정보 조회(없으면 에러발생)
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        //상품 엔티티(item) -> dto로 변환
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        //위에 만든 이미지 dto리스트를 상점 dto에 집어 넣음
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto,
                           List<MultipartFile> itemImgFileList) throws Exception{
        //상품 수정
        //기존의 상품 엔티티 조회
        //수정할 상품의 id를 이용해 db에서 기존데이터를 가져옵니다
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        //상품 기본정보 업데이트
        //dto에 담긴 수정정보(이름 가격 상세설명)를 엔티티에 반영
        //jpa에서 변경감지(Dirty Checking) 덕분에 데이터가 자동으로 업데이트 됩니다.
        item.updateItem(itemFormDto);
        //이미지 고유 id 리스트 추출
        //수정페이지에서 넘어온 이미지들의 고유 식별자(id) 리스트를 가져옵니다.
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        //이미지 등록
        //이미지 갯수만큼 반복하며 이미지 수정 작업 수행
        for(int i = 0; i < itemImgFileList.size(); i++){
            //이미지 서비스의 updateItemImg 호출합니다.
            //(기존이미지 id, 새로 업로드한 파일)을 넘겨서 교체 작업을 진행합니다.
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }
        return  item.getId();

    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable1) {
        return itemRepository.getMainItemPage(itemSearchDto, pageable1);
    }
}