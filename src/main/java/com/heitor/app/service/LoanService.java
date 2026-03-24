package com.heitor.app.service;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;

import java.util.List;

public interface LoanService {
    List<LoanResponseDTO> getAllLoans(
            Long userId,
            Boolean fine
    );

    LoanResponseDTO getLoanById(Long id);

    LoanResponseDTO createLoan(LoanRequestDTO dto);

    LoanResponseDTO returnLoan(Long id);

    void cancelLoan(Long id);
}
