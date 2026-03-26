package com.heitor.app.service;

import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {
    List<ReservationResponseDTO> getAllReservations(
            Long userId,
            Long bookId,
            ReservationStatus reservationStatus
    );
}
