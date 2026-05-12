package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {
    //c:/shop/item - uploadpath
    //실제 바이트 - 이미지 내용 자체 byte[] fileData

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID(); //파일 이름 충돌 방지
        String extension= originalFileName.substring(originalFileName.lastIndexOf("."));
        //파일명에서 마지막 .의 위치를 찾고 그 위치부터 끝까지 잘라냄 .jpg 같은 확장자만 추출
        String savedFileName = uuid.toString() + extension; //465789132.jpg
        String fileUploadFullUrl = uploadPath + "/" + savedFileName; //c:/shop/item/465789132.jpg
        FileOutputStream fos = new FileOutputStream((fileUploadFullUrl)); //파일 출력 스트림생성(파일쓰기 통로열기)
        fos.write(fileData); //실제 파일 저장
        fos.close();
        return savedFileName; //db에는 465789132.jpg 저장
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath); //전달받은 경로 filePath를 이용한 파일 객체 생성
        if (deleteFile.exists()) { //파일이 존재하면
            deleteFile.delete(); //파일 삭제
            log.info("파일을 삭제하였습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
    }

}
