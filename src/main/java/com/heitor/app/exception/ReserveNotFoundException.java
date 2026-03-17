package com.heitor.app.exception;

public class ReserveNotFoundException extends RuntimeException {
    public ReserveNotFoundException(Long id) {
        super("The reserve could not be found: " + id);
    }
}
