package com.heitor.app.service;

import com.heitor.app.entity.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getAllLoans();

    Loan getLoanById(Long id);

    Loan createLoan(Loan newLoan);

    Loan updateLoan(Loan newLoan, Long id);

    void deleteLoan(Long id);
}
