package com.heitor.app.entity;

import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fines")
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "fine_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "fine_created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "fine_payment_date")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "fine_status", nullable = false)
    private FineStatus fineStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "fine_record_status", nullable = false)
    private RecordStatus recordStatus;

    public Fine() {}

    public Fine(Long id,
                BigDecimal amount,
                LocalDate createdDate,
                LocalDate paymentDate,
                FineStatus fineStatus,
                RecordStatus recordStatus,
                Loan loan) {
        this.id = id;
        this.amount = amount;
        this.createdDate = createdDate;
        this.paymentDate = paymentDate;
        this.fineStatus = fineStatus;
        this.recordStatus = recordStatus;
        this.loan = loan;
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

    public FineStatus getFineStatus() {
        return fineStatus;
    }

    public void setFineStatus(FineStatus fineStatus) {
        this.fineStatus = fineStatus;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void paid() {
        fineStatus = FineStatus.PAID;
        paymentDate = LocalDate.now();
    }
}