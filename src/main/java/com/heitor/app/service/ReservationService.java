package com.heitor.app.service;

import com.heitor.app.dto.input.ReservationRequestDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.ReservationStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface ReservationService {
    List<ReservationResponseDTO> getAllReservations(Long userId,
                                                    Long bookId,
                                                    ReservationStatus reservationStatus,
                                                    RecordStatus recordStatus
    );

    ReservationResponseDTO getReservationById(Long id);

    ReservationResponseDTO createReservation(@Valid ReservationRequestDTO dto);

    void cancelReservation(Long id);

    ReservationResponseDTO returnReservation(Long id);
}
