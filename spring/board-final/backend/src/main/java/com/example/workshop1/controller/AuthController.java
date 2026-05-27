package com.example.workshop1.controller;

import com.example.workshop1.security.LoginUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    /* ── 로그인 ──────────────────────────────── */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req,
                                   HttpServletRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.get("username"), req.get("password")));

            SecurityContext sc = SecurityContextHolder.createEmptyContext();
            sc.setAuthentication(auth);
            SecurityContextHolder.setContext(sc);
            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", sc);

            LoginUserDetails user = (LoginUserDetails) auth.getPrincipal();
            return ResponseEntity.ok(Map.of(
                "memberId", user.getMemberId(),
                "username", user.getUsername(),
                "nickname", user.getNickname()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "아이디 또는 비밀번호가 틀렸습니다."));
        }
    }

    /* ── 내 정보 ─────────────────────────────── */
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal LoginUserDetails user) {
        if (user == null)
            return ResponseEntity.status(401).body(Map.of("message", "로그인이 필요합니다."));
        return ResponseEntity.ok(Map.of(
            "memberId", user.getMemberId(),
            "username", user.getUsername(),
            "nickname", user.getNickname()
        ));
    }

    /* ── 로그아웃 ────────────────────────────── */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "로그아웃 성공"));
    }
}
