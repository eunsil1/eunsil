package com.shop.service;


import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename(); //my_cat.jpg 실제 파일명
        String imgName = "";
        String imgUrl = "";

        //파일업로드
        if(!StringUtils.isEmpty(oriImgName)){ //파일명이 비어있지 않으면 실행
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
            //예: /images/item/456789.jpg - 웹 브라우저에서 이 이미지 접근 경로
        }
        //상품 이미지 정보 저장
        //db에 저장하기 전, 원본파일명, 저장된 파일명, 경로정보 객체 채워놓고
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg); //jpa 사용해서 해당정보를 item_img 테이블에 저장
    }

    //기존의 이미지 파일을 새 파일로 교체 - DB정보만 바꾸는게 아니라, 실제 물리적 파일도 삭제하고 새로 올리는 과정
    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        //새로운 파일이 업로드 되었는지 확인 - 수정 시 파일을 선택하지 않았다면 아무 작업도 않고 넘어감
        if(!itemImgFile.isEmpty()){
            //기존이미지 정보 조회 - DB에서 수정할 이미지의 기존 정보를 가져옴
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);
            //기존이미지 삭제
            //기존에 저장된 파일명이 있다면, fileService를 이용해서 서버폴더에 실제 파일을 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())){
                fileService.deleteFile(itemImgLocation+"/"+
                        savedItemImg.getImgName());
            }
            //새로운 파일 업로드
            //사용자가 새로 올린 파일을 서버 폴더에 저장하고, 새로운 고유 파일명을 생성
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation,
                    oriImgName, itemImgFile.getBytes());

            String imgUrl = "/images/item/" + imgName; //웹 브라우저에서 불러온 주소 생성
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
            //DB에서 조회해온 기존 이미지 엔티티(savedItemImg)의 정보를 새로운 값들로 교체
            //변경 감지를 통해 저장
        }
    }
}
