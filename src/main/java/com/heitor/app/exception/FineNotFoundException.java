package com.heitor.app.exception;

public class FineNotFoundException extends RuntimeException {
    public FineNotFoundException(Long id) {
        super("The fine could not be found: " + id);
    }
}
