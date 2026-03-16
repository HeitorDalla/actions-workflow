package com.heitor.app.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Não foi possível encontrar o livro " + id);
    }
}
