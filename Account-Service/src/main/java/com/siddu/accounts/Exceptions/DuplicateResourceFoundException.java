package com.siddu.accounts.Exceptions;

public class DuplicateResourceFoundException extends RuntimeException {
    public DuplicateResourceFoundException(String message) {
        super(message);
    }
}
