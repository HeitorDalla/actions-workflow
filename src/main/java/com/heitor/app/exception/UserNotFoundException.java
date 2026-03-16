package com.heitor.app.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Não foi possível encontrar o usuario " + id);
    }
}
