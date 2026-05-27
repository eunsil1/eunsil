package com.example.board.exception;

/**
 * 리소스를 찾지 못했을 때 → 404 응답
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
