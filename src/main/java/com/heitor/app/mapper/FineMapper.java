package com.heitor.app.mapper;

import com.heitor.app.dto.output.FineResponseDTO;
import com.heitor.app.entity.Fine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FineMapper {
    public List<FineResponseDTO> toDtoList(List<Fine> entities) {
        if (entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public FineResponseDTO toDto(Fine entity) {
        if (entity == null) {
            return null;
        }

        FineResponseDTO dto = new FineResponseDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setFineStatus(entity.getFineStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setLoanId(entity.getLoan().getId());

        return dto;
    }
}
