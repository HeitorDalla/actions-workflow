package com.heitor.app.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class LoanRequestDTO {
    @NotNull
    private Long userId;

    @NotEmpty
    private List<Long> bookIds;

    public LoanRequestDTO() {}

    public LoanRequestDTO(Long userId, List<Long> bookIds) {
        this.userId = userId;
        this.bookIds = bookIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
