package com.heitor.app.service.impl;

import com.heitor.app.dto.output.FineResponseDTO;
import com.heitor.app.entity.Fine;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.exception.BusinessException;
import com.heitor.app.exception.FineNotFoundException;
import com.heitor.app.mapper.FineMapper;
import com.heitor.app.repository.FineRepository;
import com.heitor.app.service.FineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {
    private FineRepository fineRepository;
    private FineMapper fineMapper;

    public FineServiceImpl(FineRepository fineRepository, FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
    }

    @Override
    public List<FineResponseDTO> getAllFines(
            BigDecimal amount,
            FineStatus fineStatus) {

        List<Fine> fines = fineRepository.getAllFines(amount, fineStatus);

        return fineMapper.toDtoList(fines);
    }

    @Override
    public FineResponseDTO getFineByID(Long id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));

        return fineMapper.toDto(fine);
    }

    @Transactional
    @Override
    public Fine saveFine(Fine fine) {
        if (fine.getLoan() == null) {
            throw new IllegalArgumentException("Fine must be attached");
        }

        return fineRepository.save(fine);
    }

    @Transactional
    @Override
    public FineResponseDTO payFine(Long id) {
        Fine currentFine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));

        // Regras de negócio
        if (currentFine.getFineStatus() == FineStatus.PAID) {
            throw new BusinessException("The fine has already been paid.");
        }

        if (currentFine.getFineStatus() == FineStatus.CANCELLED) {
            throw new BusinessException("The fine was cancelled.");
        }

        currentFine.setFineStatus(FineStatus.PAID);
        currentFine.setPaymentDate(LocalDate.now());

        fineRepository.save(currentFine);
        return fineMapper.toDto(currentFine);
    }
}
