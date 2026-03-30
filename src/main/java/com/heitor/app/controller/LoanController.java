package com.heitor.app.controller;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean fine,
            @RequestParam(required = false) LoanStatus loanStatus) {

        return ResponseEntity.ok(loanService.getAllLoans(
                userId,
                fine,
                loanStatus
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanRequestDTO dto) {
        return ResponseEntity.ok(loanService.createLoan(dto));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnLoan(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelLoan(@PathVariable Long id) {
        loanService.cancelLoan(id);

        return ResponseEntity.noContent().build();
    }
}
