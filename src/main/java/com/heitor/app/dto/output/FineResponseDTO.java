package com.heitor.app.dto.output;

import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FineResponseDTO {
    private Long id;
    private BigDecimal amount;
    private FineStatus fineStatus;
    private LocalDate createdDate;
    private LocalDate paymentDate;
    private Long loanId;
    private RecordStatus recordStatus;

    public FineResponseDTO() {}

    public FineResponseDTO(Long id,
                           BigDecimal amount,
                           FineStatus fineStatus,
                           LocalDate createdDate,
                           LocalDate paymentDate,
                           Long loanId,
                           RecordStatus recordStatus) {
        this.id = id;
        this.amount = amount;
        this.fineStatus = fineStatus;
        this.createdDate = createdDate;
        this.paymentDate = paymentDate;
        this.loanId = loanId;
        this.recordStatus = recordStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FineStatus getFineStatus() {
        return fineStatus;
    }

    public void setFineStatus(FineStatus fineStatus) {
        this.fineStatus = fineStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
