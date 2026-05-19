package com.example.roomfit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path root;
    //application.yml 저장된 경로
    //C:/Users/tj/roomfit-uploads

    public FileStorageService(@Value("${app.upload-dir}") String uploadDir) throws IOException {
        this.root = Paths.get(uploadDir);
        Files.createDirectories(root.resolve("interior"));
        Files.createDirectories(root.resolve("product"));
    }

    public String storeInteriorImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) { //파일 없으면 종료
            return null;
        }
        String ext = getExtension(file.getOriginalFilename()); //.png - 확장명 추출
        String filename = UUID.randomUUID() + ext; //랜덤파일명.png
        Path target = root.resolve("interior").resolve(filename); //C:/Users/tj/roomfit-uploads/interior/
        Files.copy(file.getInputStream(), target); //실제 저장
        return "/uploads/interior/" + filename; //db 저장용 url
    }

    //소품저장
    public String storeProductImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String ext = getExtension(file.getOriginalFilename());
        String filename = UUID.randomUUID() + ext;
        Path target = root.resolve("product").resolve(filename);
        Files.copy(file.getInputStream(), target);
        return "/uploads/product/" + filename;
    }

    //확장명 추출하는 메서드
    private String getExtension(String name) {
        if (name == null || !name.contains(".")) {
            return ".jpg";
        }
        return name.substring(name.lastIndexOf('.')); //.포함 뒤에 확장명 추출

    }
}
