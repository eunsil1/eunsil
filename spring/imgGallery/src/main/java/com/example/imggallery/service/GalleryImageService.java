package com.example.imggallery.service;

import com.example.imggallery.domain.GalleryImage;
import com.example.imggallery.repository.GalleryImageRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;

    private static final Set<String> ALLOWED_EXT = Set.of(".jpg", ".jpeg", ".png", ".gif", ".webp");

    @Value("${app.upload-dir}")
    private String uploadDir;


    public GalleryImageService(GalleryImageRepository galleryImageRepository) {
        this.galleryImageRepository = galleryImageRepository;
    }

    public List<GalleryImage> findAllForGallery() {
        return galleryImageRepository.findAllByOrderByCreatedAtDesc();

    }

    @Transactional
    public GalleryImage save(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일을 선택하세요.");
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }
        String original = file.getOriginalFilename();
        if (original == null || original.isBlank()) {
            original = "image";
        }
        String ext = extension(original);
        if (!ALLOWED_EXT.contains(ext)) {
            throw new IllegalArgumentException("허용 확장자: jpg, jpeg, png, gif, webp");
        }
        String stored = UUID.randomUUID() + ext;
        Path dest = resolveStoredFile(stored);

        try {
            Files.createDirectories(dest.getParent());
            file.transferTo(dest); //실제 이미지 저장(파일저장)
        } catch (IOException e) {
            throw new RuntimeException("저장 실패", e);
        }
        GalleryImage entity = new GalleryImage(original, stored, Instant.now());
        return galleryImageRepository.save(entity); //DB 저장
    }

    //경로 조작을 검증
    private Path resolveStoredFile(String storedFilename) {
        Path base = Paths.get(normalizeDir(uploadDir)).toAbsolutePath().normalize();
        //toAbsolutePath().normalize() -> c:/project/upload
        Path file = base.resolve(storedFilename).normalize();
        if (!file.startsWith(base)) {
            throw new IllegalArgumentException("잘못된 경로입니다.");
        }
        return file;

    }

    private String normalizeDir(String dir){

        if (dir == null || dir.isBlank()) {
            return "";
        }
        return dir.endsWith("/") || dir.endsWith("\\") ? dir : dir + File.separator;
    }

    //확장명 추출
    private static String extension(String filename){
        int i = filename.lastIndexOf('.'); //abc.j  pg
        if (i < 0 || i == filename.length() - 1) {
//            .(확장자가 없거나), 점이 파일명의 맨 마지막에 있는 경우 (filename.)
            return "";
        }
        return filename.substring(i).toLowerCase(Locale.ROOT);
        //점을 포함한 이후 문자열만 잘라낸다.(소문자로)
    }

    @Transactional(readOnly = true)
    public Resource loadAsResource(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이미지가 없습니다."));
        //경로 확인
        Path file = resolveStoredFile(image.getStoredFilename());
        if (!Files.isRegularFile(file)) { //실제 존재여부를 확인
            throw new IllegalStateException("파일이 없습니다: " + image.getStoredFilename());
        }
        try {
            return new UrlResource(file.toUri());
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽을 수 없습니다.", e);
        }
    }

    public void deleteById(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("이미지가 없습니다."));
        Path file = resolveStoredFile(image.getStoredFilename());
        try{
            Files.deleteIfExists(file);//이미지삭제
        }catch (IOException ignored){
            //DB 삭제는 진행
        }
        galleryImageRepository.delete(image); //삭제
    }
}
