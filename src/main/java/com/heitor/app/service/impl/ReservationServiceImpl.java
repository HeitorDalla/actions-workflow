package com.heitor.app.service.impl;

import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.entity.Reservation;
import com.heitor.app.enums.ReservationStatus;
import com.heitor.app.exception.ReserveNotFoundException;
import com.heitor.app.mapper.ReservationMapper;
import com.heitor.app.repository.ReservationRepository;
import com.heitor.app.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations(Long userId,
                                                           Long bookId,
                                                           ReservationStatus reservationStatus) {

        List<Reservation> reservations = reservationRepository.findAllReservations(userId, bookId, reservationStatus);

        return reservationMapper.toDtoList(reservations);
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReserveNotFoundException(id));

        return reservationMapper.toDto(reservation);
    }
}
