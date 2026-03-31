package com.heitor.app.service.impl;

import com.heitor.app.dto.input.LoanRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.entity.Fine;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.exception.BusinessException;
import com.heitor.app.exception.LoanNotFoundException;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.LoanMapper;
import com.heitor.app.repository.LoanRepository;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.FineService;
import com.heitor.app.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final FineService fineService;
    private final LoanMapper mapper;

    public LoanServiceImpl(UserRepository userRepository,
                           LoanRepository loanRepository,
                           FineService fineService,
                           LoanMapper mapper) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.fineService = fineService;
        this.mapper = mapper;
    }

    @Override
    public List<LoanResponseDTO> getAllLoans(Long userId,
                                             Boolean fine,
                                             LoanStatus loanStatus,
                                             RecordStatus recordStatus) {

        List<Loan> loans = loanRepository.getAllLoans(
                userId,
                fine,
                loanStatus,
                recordStatus
        );

        return mapper.toDtoList(loans);
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        return mapper.toDto(loan);
    }

    @Transactional
    @Override
    public LoanResponseDTO createLoan(LoanRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        if (user.getUserStatus() != UserStatus.OK || user.getRecordStatus() != RecordStatus.ACTIVE) {
            throw new BusinessException("User is not allowed to create loans.");
        }

        Loan loan = mapper.toEntity(dto, user);

        // Regras de negócio
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(1));
        loan.setLoanStatus(LoanStatus.OPEN);
        loan.setRecordStatus(RecordStatus.ACTIVE);

        loan = loanRepository.save(loan);
        return mapper.toDto(loan);
    }

    @Transactional
    @Override
    public LoanResponseDTO returnLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        if (loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new BusinessException("The loan has already been returned.");
        }

        if (loan.getLoanStatus() == LoanStatus.CANCELLED) {
            throw new BusinessException("Cancelled loan cannot be returned.");
        }

        // Devolução
        loan.setReturnDate(LocalDate.now());
        loan.setLoanStatus(LoanStatus.RETURNED);
        loan.setRecordStatus(RecordStatus.INACTIVE);

        // Regra de criação de multa
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setAmount(new BigDecimal("25.00"));
            fine.setFineStatus(FineStatus.OPEN);
            fine.setRecordStatus(RecordStatus.ACTIVE);
            fine.setCreatedDate(LocalDate.now());
            fine.setPaymentDate(null);

            Fine savedFine = fineService.saveFine(fine);
            loan.setFine(savedFine);
        }

        loanRepository.save(loan);
        return mapper.toDto(loan);
    }

    @Transactional
    @Override
    public void cancelLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        // Regras de negócio
        if (loan.getLoanStatus() != LoanStatus.OPEN) {
            throw new BusinessException("Only open loans can be cancelled.");
        }

        loan.setLoanStatus(LoanStatus.CANCELLED);
        loan.setRecordStatus(RecordStatus.INACTIVE);

        loanRepository.save(loan);
    }
}
