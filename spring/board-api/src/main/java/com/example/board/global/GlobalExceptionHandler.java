package com.example.board.global;

import com.example.board.exception.DuplicateUsernameException;
import com.example.board.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 전역 예외 핸들러
 * - 404 : ResourceNotFoundException
 * - 409 : DuplicateUsernameException
 * - 400 : @Valid 검증 실패
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ── 공통 에러 바디 ───────────────────────────── */
    record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
        static ErrorResponse of(HttpStatus status, String message) {
            return new ErrorResponse(status.value(), status.getReasonPhrase(), message, LocalDateTime.now());
        }
    }

    /* ── 404 ──────────────────────────────────────── */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    /* ── 409 ──────────────────────────────────────── */
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateUsernameException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(HttpStatus.CONFLICT, ex.getMessage()));
    }

    /* ── 400 : @Valid 실패 ────────────────────────── */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("fieldErrors", fieldErrors);
        body.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(body);
    }
}
