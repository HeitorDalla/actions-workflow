package com.heitor.app.service.impl;

import com.heitor.app.entity.Fine;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.exception.LoanNotFoundException;
import com.heitor.app.repository.LoanRepository;
import com.heitor.app.service.LoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> getAllLoans(
            LocalDate loanDate,
            LocalDate returnDate,
            LoanStatus loanStatus,
            User user,
            Fine fine
    ) {

        return loanRepository.getAllLoans(
                loanDate,
                returnDate,
                loanStatus,
                user,
                fine
        );
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    @Override
    public Loan createLoan(Loan newLoan) {
        return loanRepository.save(newLoan);
    }

    @Override
    public Loan updateLoan(Loan newLoan, Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        if (newLoan.getLoanDate() != null) {
            loan.setLoanDate(newLoan.getLoanDate());
        }

        if (newLoan.getReturnDate() != null) {
            loan.setReturnDate(newLoan.getReturnDate());
        }

        if (newLoan.getLoanStatus() != null) {
            loan.setLoanStatus(newLoan.getLoanStatus());
        }

        if (newLoan.getUser() != null) {
            // SET USER
            loan.setUser(newLoan.getUser());
        }

        if (newLoan.getFine() != null) {
            // SET FINE
            loan.setFine(newLoan.getFine());
        }

        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        Loan deletedLoan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        loanRepository.delete(deletedLoan);
    }
}
