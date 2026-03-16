package com.heitor.app.exception;

public class ReserveNotFoundException extends RuntimeException {
    public ReserveNotFoundException(Long id) {
        super("Não foi possível encontrar a reserva " + id);
    }
}
