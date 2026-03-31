package com.heitor.app.service;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.enums.RecordStatus;

import java.util.List;

public interface LoanService {
    List<LoanResponseDTO> getAllLoans(Long userId,
                                      Boolean fine,
                                      LoanStatus loanStatus,
                                      RecordStatus recordStatus
    );

    LoanResponseDTO getLoanById(Long id);

    LoanResponseDTO createLoan(LoanRequestDTO dto);

    LoanResponseDTO returnLoan(Long id);

    void cancelLoan(Long id);
}
