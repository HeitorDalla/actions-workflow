package com.heitor.app.service.impl;

import com.heitor.app.entity.Fine;
import com.heitor.app.enumerate.FineStatus;
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
}
