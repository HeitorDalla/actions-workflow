package com.heitor.app.enums;

public enum ReservationStatus {
    PENDING, // reserva criada
    CONFIRMED, // livro disponível e separada para o usuário
    CANCELLED, // cancelada manualmente
    EXPIRED // perdeu validade automaticamente
}
