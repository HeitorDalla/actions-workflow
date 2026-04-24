package com.heitor.app.mapper;

import com.heitor.app.dto.input.ReservationRequestDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.entity.Book;
import com.heitor.app.entity.Reservation;
import com.heitor.app.entity.User;
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
        dto.setDueDate(entity.getDueDate());
        dto.setReturnDate(entity.getReturnDate());
        dto.setReservationStatus(entity.getReservationStatus());
        dto.setRecordStatus(entity.getRecordStatus());

        return dto;
    }

    public Reservation toEntity(ReservationRequestDTO dto, User user, Book book) {
        if (dto == null) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);

        return reservation;
    }
}
