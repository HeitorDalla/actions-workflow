package com.heitor.app.controller;

import com.heitor.app.entity.Fine;
import com.heitor.app.enumerate.FineStatus;
import com.heitor.app.service.FineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fines")
public class FineController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineController.class);

    private FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public List<Fine> getAllFines(
            @RequestParam(required = false) BigDecimal amount,
            @RequestParam(required = false) FineStatus fineStatus,
            @RequestParam(required = false) LocalDate createdDate,
            @RequestParam(required = false) LocalDate paymentDate) {

        List<Fine> fines = fineService.getAllFines(
                amount,
                fineStatus,
                createdDate,
                paymentDate
        );

        LOGGER.info("Fines successfully fetched: {}", fines);

        return fines;
    }
}
