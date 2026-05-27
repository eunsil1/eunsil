package com.example.workshop1.controller;

import com.example.workshop1.dto.PostDto;
import com.example.workshop1.security.LoginUserDetails;
import com.example.workshop1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /* ── 목록 (공개) ─────────────────────────── */
    @GetMapping
    public ResponseEntity<Page<PostDto.SummaryResponse>> findAll(
            @PageableDefault(size = 10, sort = "id",
                             direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    /* ── 상세 (공개) ─────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    /**
     * 글 등록 — 작업형 3: multipart/form-data
     * POST /api/posts
     * title, content (form-data 텍스트)
     * files (선택, jpg/png, 최대 3장)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDto.Response> create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) List<MultipartFile> files,
            @AuthenticationPrincipal LoginUserDetails user) throws IOException {

        if (files != null && files.size() > 3)
            return ResponseEntity.badRequest().build();

        PostDto.Response res = postService.create(
            title, content, user.getMemberId(), files);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /* ── 수정 (본인만) ───────────────────────── */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto.Response> update(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @AuthenticationPrincipal LoginUserDetails user) {
        postService.validateAuthor(id, user.getMemberId());
        return ResponseEntity.ok(postService.update(id, title, content));
    }

    /* ── 삭제 (본인만) ───────────────────────── */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal LoginUserDetails user) {
        postService.validateAuthor(id, user.getMemberId());
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
