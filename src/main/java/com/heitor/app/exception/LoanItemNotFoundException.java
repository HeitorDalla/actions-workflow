package com.heitor.app.exception;

public class LoanItemNotFoundException extends RuntimeException {
    public LoanItemNotFoundException(Long id) {
        super("Não foi possível encontrar o emprestimo " + id);
    }
}
