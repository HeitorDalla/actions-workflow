package com.heitor.app.dto.output;

import com.heitor.app.enums.LoanStatus;

import java.time.LocalDate;

public class LoanResponseDTO {
    private Long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus loanStatus;
    private Long userId;
    private Boolean hasFine;

    public LoanResponseDTO() {}

    public LoanResponseDTO(Long id,
                           LocalDate loanDate,
                           LocalDate dueDate,
                           LocalDate returnDate,
                           LoanStatus loanStatus,
                           Long userId,
                           Boolean hasFine) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
