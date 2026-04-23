package com.heitor.app.service.impl;

import com.heitor.app.dto.output.FineResponseDTO;
import com.heitor.app.entity.Fine;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;
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
    private final FineRepository fineRepository;
    private final FineMapper fineMapper;

    public FineServiceImpl(FineRepository fineRepository,
                           FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
    }

    private List<Fine> findAllFines(BigDecimal amount,
                                    FineStatus fineStatus,
                                    RecordStatus recordStatus) {
        return fineRepository.getAllFines(amount, fineStatus, recordStatus);
    }

    @Override
    public List<FineResponseDTO> getAllFines(BigDecimal amount,
                                             FineStatus fineStatus,
                                             RecordStatus recordStatus) {

        List<Fine> fines = findAllFines(amount, fineStatus, recordStatus);

        return fineMapper.toDtoList(fines);
    }

    private Fine findFine(Long id) {
        return fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
    }

    private void validateCanBePaid (Fine currentFine) {
        if (currentFine.getFineStatus() == FineStatus.PAID) {
            throw new BusinessException("The fine has already been paid.");
        }

        if (currentFine.getFineStatus() == FineStatus.CANCELLED) {
            throw new BusinessException("The fine was cancelled.");
        }
    }

    @Override
    public FineResponseDTO getFineById(Long id) {
        Fine fine = findFine(id);

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
        Fine currentFine = findFine(id);

        validateCanBePaid(currentFine);

        currentFine.paid();

        fineRepository.save(currentFine);
        return fineMapper.toDto(currentFine);
    }
}
