package com.heitor.app.mapper;

import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper {
    public List<ReservationResponseDTO> toDtoList(List<Reservation> entities) {
        if (entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public ReservationResponseDTO toDto(Reservation entity) {
        if (entity == null) {
            return null;
        }

        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setBookId(entity.getBook().getId());
        dto.setReservationDate(entity.getReservationDate());
        dto.setReservationStatus(entity.getReservationStatus());

        return dto;
    }
}
