package com.heitor.app.exception;

public class LoanItemNotFoundException extends RuntimeException {
    public LoanItemNotFoundException(Long id) {
            super("The loan item could not be found: " + id);
    }
}
