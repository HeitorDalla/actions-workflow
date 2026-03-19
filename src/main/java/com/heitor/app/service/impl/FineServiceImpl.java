package com.heitor.app.service.impl;

import com.heitor.app.entity.Fine;
import com.heitor.app.enumerate.FineStatus;
import com.heitor.app.exception.FineNotFoundException;
import com.heitor.app.repository.FineRepository;
import com.heitor.app.service.FineService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {
    private FineRepository fineRepository;

    public FineServiceImpl(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @Override
    public List<Fine> getAllFines(
            BigDecimal amount,
            FineStatus fineStatus,
            LocalDate createdDate,
            LocalDate paymentDate) {

        return fineRepository.getAllFines(
                amount,
                fineStatus,
                createdDate,
                paymentDate
        );
    }

    @Override
    public Fine getFineByID(Long id) {
        return fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
    }

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine updateFine(Fine newFine, Long id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));

        if (newFine.getAmount() != null) {
            fine.setAmount(newFine.getAmount());
        }

        if (newFine.getFineStatus() != null) {
            fine.setFineStatus(newFine.getFineStatus());
        }

        if (newFine.getCreatedDate() != null) {
            fine.setCreatedDate(newFine.getCreatedDate());
        }

        if (newFine.getPaymentDate() != null) {
            fine.setPaymentDate(newFine.getPaymentDate());
        }

        return fineRepository.save(fine);
    }
}
