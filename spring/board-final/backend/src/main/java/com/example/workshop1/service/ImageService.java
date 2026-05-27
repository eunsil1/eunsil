package com.example.workshop1.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 작업형 3 — 이미지 업로드 + 썸네일 생성
 */
@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final List<String> ALLOWED_EXT = List.of("jpg", "jpeg", "png");
    private static final long MAX_SIZE = 10 * 1024 * 1024L; // 10MB

    public record SavedImage(String originalName, String storedName, String thumbnailName) {}

    public SavedImage save(MultipartFile file) throws IOException {
        validate(file);

        String ext          = getExtension(file.getOriginalFilename());
        String storedName   = UUID.randomUUID() + "." + ext;
        String thumbName    = "thumb_" + storedName;

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // 원본 저장
        File dest = new File(dir, storedName);
        file.transferTo(dest);

        // 썸네일 생성 (200×200)
        Thumbnails.of(dest)
                  .size(200, 200)
                  .toFile(new File(dir, thumbName));

        return new SavedImage(file.getOriginalFilename(), storedName, thumbName);
    }

    public void delete(String storedName, String thumbnailName) {
        new File(uploadDir, storedName).delete();
        new File(uploadDir, thumbnailName).delete();
    }

    private void validate(MultipartFile file) {
        if (file.getSize() > MAX_SIZE)
            throw new IllegalArgumentException("파일 크기는 10MB 이하여야 합니다.");

        String ext = getExtension(file.getOriginalFilename());
        if (!ALLOWED_EXT.contains(ext.toLowerCase()))
            throw new IllegalArgumentException("jpg, png 파일만 업로드 가능합니다.");
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains("."))
            throw new IllegalArgumentException("파일 확장자를 확인하세요.");
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
