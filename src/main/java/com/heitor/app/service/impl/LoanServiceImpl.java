package com.heitor.app.service.impl;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.exception.LoanNotFoundException;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.LoanMapper;
import com.heitor.app.repository.LoanRepository;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.LoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private UserRepository userRepository;
    private LoanRepository loanRepository;
    private LoanMapper mapper;

    public LoanServiceImpl(UserRepository userRepository, LoanRepository loanRepository, LoanMapper mapper) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.mapper = mapper;
    }

    @Override
    public List<LoanResponseDTO> getAllLoans(
            Long userId,
            Boolean fine
    ) {

        List<Loan> loans = loanRepository.getAllLoans(
                userId,
                fine
        );

        return mapper.toDtoList(loans);
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        return mapper.toDto(loan);
    }

    @Override
    public LoanResponseDTO createLoan(LoanRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        Loan loan = mapper.toEntity(dto, user);

        loan.setLoanDate(LocalDate.now());
        loan.setLoanStatus(LoanStatus.OPEN);

        loan = loanRepository.save(loan);

        return mapper.toDto(loan);
    }

    @Override
    public LoanResponseDTO returnLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        loan.setReturnDate(LocalDate.now());
        loan.setLoanStatus(LoanStatus.RETURNED);

        loanRepository.save(loan);

        return mapper.toDto(loan);
    }

    @Override
    public void cancelLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        loan.setLoanStatus(LoanStatus.CANCELLED);
        loan.setReturnDate(LocalDate.now());

        loanRepository.save(loan);
    }
}
