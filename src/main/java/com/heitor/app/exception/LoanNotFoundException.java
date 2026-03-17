package com.heitor.app.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("The loan could not be found: " + id);
    }
}
