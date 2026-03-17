package com.heitor.app.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The user could not be found: " + id);
    }
}
