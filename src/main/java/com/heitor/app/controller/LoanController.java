package com.heitor.app.controller;

import com.heitor.app.entity.Fine;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans(
            @RequestParam(required = false) LocalDate loanDate,
            @RequestParam(required = false) LocalDate returnDate,
            @RequestParam(required = false) LoanStatus loanStatus,
            @RequestParam(required = false) User user,
            @RequestParam(required = false) Fine fine
    ) {

        List<Loan> loans = loanService.getAllLoans(
                loanDate,
                returnDate,
                loanStatus,
                user,
                fine
        );

        LOGGER.info("Loans successfully fetched: {}", loans);

        return loans;
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);

        LOGGER.info("Loan successfully fetched: {}", loan);

        return loan;
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan newLoan) {
        Loan loan = loanService.createLoan(newLoan);

        LOGGER.info("Loan successfully created: {}", loan);

        return loan;
    }

    @PatchMapping("/{id}")
    public Loan updateLoan(@RequestBody Loan newLoan, @PathVariable Long id) {
        Loan loan = loanService.updateLoan(newLoan, id);

        LOGGER.info("Loan successfully updated: {}", loan);

        return loan;
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);

        LOGGER.info("Loan successfully deleted");
    }
}
