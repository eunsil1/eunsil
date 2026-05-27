package com.example.workshop1.exception;
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String msg) { super(msg); }
}
