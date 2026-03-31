package com.heitor.app.mapper;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanMapper {

    public List<LoanResponseDTO> toDtoList(List<Loan> loans) {
        if (loans.isEmpty()) {
            return List.of();
        }

        return loans.stream()
                .map(this::toDto)
                .toList();
    }

    public LoanResponseDTO toDto(Loan loan) {
        if (loan == null) {
            return null;
        }

        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setId(loan.getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setLoanStatus(loan.getLoanStatus());
        dto.setRecordStatus(loan.getRecordStatus());
        dto.setUserId(loan.getUser().getId());
        dto.setHasFine(loan.getFine() != null);

        return dto;
    }

    public Loan toEntity(LoanRequestDTO dto, User user) {
        if (dto == null) {
            return null;
        }

        Loan loan = new Loan();
        loan.setUser(user);

        return loan;
    }
}
