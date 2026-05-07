package com.example.imggallery.web;

import com.example.imggallery.service.GalleryImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GalleryController {

    private final GalleryImageService galleryImageService;

    public GalleryController(GalleryImageService galleryImageService) {
        this.galleryImageService = galleryImageService;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/gallery";
    }

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("images", galleryImageService.findAllForGallery());
        return "gallery";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> file(@PathVariable Long id){
        Resource resource = galleryImageService.loadAsResource(id);
        String name = resource.getFilename() != null ? resource.getFilename() : "image";
        //파일을 브라우저에 보여주거나 다운로드 하는 응답생성
        return ResponseEntity.ok()
                //inline 브라우저바로표시(이미지 pdf) attachment -> 강제 다운
                //inline
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + name + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream")) // /image/jpeg
                .body(resource);


    }

//    String contentType = Files.probeContentType(filePath);
//    String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
//
//return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION,
//            "inline; filename*=UTF-8''" + encodedName)
//    .contentType(MediaType.parseMediaType(
//            contentType != null ? contentType : "application/octet-stream"))
//            .body(resource);

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra){
        try{
            galleryImageService.deleteById(id);
            ra.addFlashAttribute("msg", "삭제되었습니다.");
        }catch (RuntimeException e){
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/admin";
    }


}
