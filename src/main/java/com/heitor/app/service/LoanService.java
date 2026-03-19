package com.heitor.app.service;

import com.heitor.app.entity.Fine;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enumerate.LoanStatus;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {
    List<Loan> getAllLoans(
            LocalDate loanDate,
            LocalDate returnDate,
            LoanStatus loanStatus,
            User user,
            Fine fine
    );

    Loan getLoanById(Long id);

    Loan createLoan(Loan newLoan);

    Loan updateLoan(Loan newLoan, Long id);

    void deleteLoan(Long id);
}
