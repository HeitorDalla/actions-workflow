package com.heitor.app.controller;

import com.heitor.app.dto.output.FineResponseDTO;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.service.FineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fines")
public class FineController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineController.class);

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public ResponseEntity<List<FineResponseDTO>> getAllFines(
            @RequestParam(required = false) BigDecimal amount,
            @RequestParam(required = false) FineStatus fineStatus,
            @RequestParam(required = false) RecordStatus recordStatus) {

        return ResponseEntity.ok(fineService.getAllFines(
                amount,
                fineStatus,
                recordStatus
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FineResponseDTO> getFineById(@PathVariable Long id) {
        return ResponseEntity.ok(fineService.getFineById(id));
    }

    @PatchMapping("/{id}/payment")
    public ResponseEntity<FineResponseDTO> payFine(@PathVariable Long id) {
        return ResponseEntity.ok(fineService.payFine(id));
    }
}
