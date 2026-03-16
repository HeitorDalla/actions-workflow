package com.heitor.app.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("Não foi possível encontrar o emprestimo " + id);
    }
}
