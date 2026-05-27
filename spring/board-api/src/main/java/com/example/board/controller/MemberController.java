package com.example.board.controller;

import com.example.board.dto.MemberDto;
import com.example.board.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * POST /api/members — 회원가입
 * GET  /api/members/{id} — 회원 조회
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* ── 회원가입 ──────────────────────────────────── */
    @PostMapping
    public ResponseEntity<MemberDto.Response> register(
            @Valid @RequestBody MemberDto.RegisterRequest req) {
        MemberDto.Response response = memberService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* ── 회원 단건 조회 ────────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }
}
