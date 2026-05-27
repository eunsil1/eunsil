package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * GET    /api/posts              — 목록 (페이징)
 * GET    /api/posts/{id}         — 상세
 * POST   /api/posts              — 등록
 * PUT    /api/posts/{id}         — 수정
 * DELETE /api/posts/{id}         — 삭제
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /* ── 목록 ──────────────────────────────────────── */
    @GetMapping
    public ResponseEntity<Page<PostDto.SummaryResponse>> findAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    /* ── 상세 ──────────────────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    /* ── 등록 ──────────────────────────────────────── */
    @PostMapping
    public ResponseEntity<PostDto.Response> create(
            @Valid @RequestBody PostDto.CreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(req));
    }

    /* ── 수정 ──────────────────────────────────────── */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto.Response> update(
            @PathVariable Long id,
            @Valid @RequestBody PostDto.UpdateRequest req) {
        return ResponseEntity.ok(postService.update(id, req));
    }

    /* ── 삭제 ──────────────────────────────────────── */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();   // 204
    }
}
