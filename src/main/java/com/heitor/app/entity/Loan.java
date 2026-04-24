package com.heitor.app.entity;

import com.heitor.app.enums.LoanStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.exception.BusinessException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "loan_books",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "loan_due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "loan_return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status", nullable = false)
    private LoanStatus loanStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_record_status", nullable = false)
    private RecordStatus recordStatus;

    @OneToOne(mappedBy = "loan")
    private Fine fine;

    public Loan() {}

    public Loan(Long id,
                LocalDate loanDate,
                LocalDate dueDate,
                LocalDate returnDate,
                LoanStatus loanStatus,
                RecordStatus recordStatus,
                User user,
                Fine fine,
                List<Book> books) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
        this.recordStatus = recordStatus;
        this.user = user;
        this.fine = fine;
        this.books = books;
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

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fine getFine() {
        return fine;
    }

    public void setFine(Fine fine) {
        this.fine = fine;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void initialize() {
        loanDate = LocalDate.now();
        dueDate = loanDate.plusWeeks(1);
        loanStatus = LoanStatus.OPEN;
        recordStatus = RecordStatus.ACTIVE;
    }

    public void validateCanBeReturned() {
        if (loanStatus == LoanStatus.RETURNED) {
            throw new BusinessException("The loan has already been returned.");
        }

        if (loanStatus == LoanStatus.CANCELLED) {
            throw new BusinessException("Cancelled loan cannot be returned.");
        }
    }

    public void finish() {
        returnDate = LocalDate.now();
        loanStatus = LoanStatus.RETURNED;
        recordStatus = RecordStatus.INACTIVE;
    }

    public void cancel() {
        if (loanStatus != LoanStatus.OPEN) {
            throw new BusinessException("Only open loans can be cancelled.");
        }

        loanStatus = LoanStatus.CANCELLED;
        recordStatus = RecordStatus.INACTIVE;
    }

    public void markAsOverdue() {
        if (loanStatus != LoanStatus.OPEN) {
            return;
        }

        loanStatus = LoanStatus.OVERDUE;
    }
}