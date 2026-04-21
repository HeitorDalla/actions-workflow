package com.heitor.app.mapper;

import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.entity.Book;
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
        dto.setBooksId(
                loan.getBooks()
                        .stream()
                        .map(Book::getId)
                        .toList()
        );
        dto.setHasFine(loan.getFine() != null);

        return dto;
    }

    public Loan toEntity(User user, List<Book> books) {
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBooks(books);

        return loan;
    }
}
