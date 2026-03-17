package com.heitor.app.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("The book could not be found: " + id);
    }
}
