package com.heitor.app.service;

import com.heitor.app.entity.Fine;
import com.heitor.app.enumerate.FineStatus;

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
}
