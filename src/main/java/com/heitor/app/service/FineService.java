package com.heitor.app.service;

import com.heitor.app.dto.output.FineResponseDTO;
import com.heitor.app.entity.Fine;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;

import java.math.BigDecimal;
import java.util.List;

public interface FineService {
    List<FineResponseDTO> getAllFines(BigDecimal amount,
                                      FineStatus fineStatus,
                                      RecordStatus recordStatus
    );

    FineResponseDTO getFineById(Long id);

    Fine saveFine(Fine fine);

    FineResponseDTO payFine(Long id);
}
