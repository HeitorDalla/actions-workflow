package com.heitor.app.dto.input;

import jakarta.validation.constraints.NotNull;

public class LoanRequestDTO {
    @NotNull
    private Long userId;

    public LoanRequestDTO() {}

    public LoanRequestDTO(Long userId,
                          Boolean hasFine) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
