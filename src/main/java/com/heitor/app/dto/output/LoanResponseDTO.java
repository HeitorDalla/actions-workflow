package com.heitor.app.dto.output;

import com.heitor.app.entity.LoanItem;
import com.heitor.app.enums.LoanStatus;

import java.time.LocalDate;
import java.util.List;

public class LoanResponseDTO {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus loanStatus;
    private Long userId;
    private Boolean hasFine;

    public LoanResponseDTO() {}

    public LoanResponseDTO(Long id,
                           LocalDate loanDate,
                           LocalDate returnDate,
                           LoanStatus loanStatus,
                           Long userId,
                           Boolean hasFine,
                           List<LoanItem> loanItems) {
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
        this.userId = userId;
        this.hasFine = hasFine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getHasFine() {
        return hasFine;
    }

    public void setHasFine(Boolean hasFine) {
        this.hasFine = hasFine;
    }
}
