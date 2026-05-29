package com.example.workshop01.web;

import com.example.workshop01.domain.Board;
import com.example.workshop01.domain.BoardImage;
import com.example.workshop01.domain.User;
import com.example.workshop01.security.LoginUser;
import com.example.workshop01.service.BoardService;
import com.example.workshop01.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    private final BoardService boardService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public ApiController(BoardService boardService,
                         UserService userService,
                         AuthenticationManager authenticationManager) {
        this.boardService          = boardService;
        this.userService           = userService;
        this.authenticationManager = authenticationManager;
    }

    /* ── 회원가입 ─────────────────────────────────── */
    @PostMapping("/api/members")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody Map<String, String> req) {
        Map<String, Object> body = new HashMap<>();
        try {
            userService.register(req.get("username"), req.get("password"), req.get("name"));
            body.put("message", "회원가입 성공");
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (IllegalArgumentException e) {
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
    }

    /* ── 로그인 ───────────────────────────────────── */
    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> req,
            HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.get("username"), req.get("password")));

            SecurityContext sc = SecurityContextHolder.createEmptyContext();
            sc.setAuthentication(auth);
            SecurityContextHolder.setContext(sc);
            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", sc);

            LoginUser user = (LoginUser) auth.getPrincipal();
            body.put("id",       user.getId());
            body.put("username", user.getUsername());
            body.put("name",     user.getDisplayName());
            return ResponseEntity.ok(body);

        } catch (BadCredentialsException e) {
            body.put("message", "아이디 또는 비밀번호가 틀렸습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
    }

    /* ── 내 정보 ──────────────────────────────────── */
    @GetMapping("/api/auth/me")
    public ResponseEntity<Map<String, Object>> me(
            @AuthenticationPrincipal LoginUser user) {
        Map<String, Object> body = new HashMap<>();
        if (user == null) {
            body.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
        body.put("id",       user.getId());
        body.put("username", user.getUsername());
        body.put("name",     user.getDisplayName());
        return ResponseEntity.ok(body);
    }

    /* ── 로그아웃 ─────────────────────────────────── */
    @PostMapping("/api/auth/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();
        Map<String, Object> body = new HashMap<>();
        body.put("message", "로그아웃 성공");
        return ResponseEntity.ok(body);
    }

    /* ── 글 목록 ──────────────────────────────────── */
    @GetMapping("/api/posts")
    public ResponseEntity<List<Map<String, Object>>> list() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Board b : boardService.findAll()) {
            Map<String, Object> item = new HashMap<>();
            item.put("id",         b.getId());
            item.put("title",      b.getTitle());
            item.put("authorName", b.getAuthor().getName());
            item.put("createdAt",  b.getCreatedAt().toString());

            if (!b.getImages().isEmpty()) {
                item.put("thumbnailUrl",
                        "/uploads/" + b.getImages().get(0).getThumbnailSavedName());
            } else {
                item.put("thumbnailUrl", null);
            }

            result.add(item);
        }
        return ResponseEntity.ok(result);
    }

    /* ── 글 상세 ──────────────────────────────────── */
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try {
            Board b = boardService.findById(id);
            List<Map<String, Object>> images = new ArrayList<>();
            for (BoardImage img : b.getImages()) {
                Map<String, Object> imgMap = new HashMap<>();
                imgMap.put("id",           img.getId());
                imgMap.put("url",          "/uploads/" + img.getSavedName());
                imgMap.put("thumbnailUrl", "/uploads/" + img.getThumbnailSavedName());
                images.add(imgMap);
            }
            body.put("id",         b.getId());
            body.put("title",      b.getTitle());
            body.put("content",    b.getContent());
            body.put("authorId",   b.getAuthor().getId());
            body.put("authorName", b.getAuthor().getName());
            body.put("images",     images);
            body.put("createdAt",  b.getCreatedAt().toString());
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    /* ── 글 등록 ──────────────────────────────────── */
    @PostMapping(value = "/api/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @AuthenticationPrincipal LoginUser loginUser) {
        Map<String, Object> body = new HashMap<>();
        if (loginUser == null) {
            body.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
        User author = userService.findByUsername(loginUser.getUsername());
        Board board = boardService.create(title, content, author, files);
        body.put("id",    board.getId());
        body.put("title", board.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    /* ── 글 수정 ──────────────────────────────────── */
    @PutMapping(value = "/api/posts/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @AuthenticationPrincipal LoginUser loginUser) {
        Map<String, Object> body = new HashMap<>();
        if (loginUser == null) {
            body.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
        try {
            boardService.update(id, title, content, loginUser.getUsername(), files);
            body.put("message", "수정 완료");
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            body.put("message", e.getMessage());
            int status = e.getMessage().contains("작성자") ? 403 : 404;
            return ResponseEntity.status(status).body(body);
        }
    }

    /* ── 글 삭제 ──────────────────────────────────── */
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal LoginUser loginUser) {
        Map<String, Object> body = new HashMap<>();
        if (loginUser == null) {
            body.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
        try {
            boardService.delete(id, loginUser.getUsername());
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            body.put("message", e.getMessage());
            int status = e.getMessage().contains("작성자") ? 403 : 404;
            return ResponseEntity.status(status).body(body);
        }
    }
}
