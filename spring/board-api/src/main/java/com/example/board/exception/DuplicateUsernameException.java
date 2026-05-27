package com.example.board.exception;

/**
 * 이미 사용 중인 username 으로 가입 시도 시 → 409 응답
 */
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
