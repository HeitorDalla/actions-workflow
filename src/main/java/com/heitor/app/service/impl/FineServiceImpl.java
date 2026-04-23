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

    private Fine findFine(Long id) {
        return fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
    }

    @Override
    public List<FineResponseDTO> getAllFines(BigDecimal amount,
                                             FineStatus fineStatus,
                                             RecordStatus recordStatus) {

        List<Fine> fines = findAllFines(amount, fineStatus, recordStatus);

        return fineMapper.toDtoList(fines);
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
            throw new BusinessException("Fine must be associated with a loan.");
        }

        return fineRepository.save(fine);
    }

    @Transactional
    @Override
    public FineResponseDTO payFine(Long id) {
        Fine fine = findFine(id);

        fine.pay();

        fineRepository.save(fine);
        return fineMapper.toDto(fine);
    }

    @Transactional
    @Override
    public void cancelFine(Long id) {
        Fine fine = findFine(id);

        fine.cancel();

        fineRepository.save(fine);
    }
}
