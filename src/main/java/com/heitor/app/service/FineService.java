package com.heitor.app.service;

import com.heitor.app.entity.Fine;
import com.heitor.app.enums.FineStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FineService {
    List<Fine> getAllFines(
            BigDecimal amount,
            FineStatus fineStatus,
            LocalDate createdDate,
            LocalDate paymentDate
    );

    Fine getFineByID(Long id);

    Fine createFine(Fine fine);

    Fine updateFine(Fine newFine, Long id);
}
