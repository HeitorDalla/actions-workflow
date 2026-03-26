package com.heitor.app.controller;

import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.enums.ReservationStatus;
import com.heitor.app.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) ReservationStatus reservationStatus) {

        return ResponseEntity.ok(reservationService.getAllReservations(
                userId,
                bookId,
                reservationStatus)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservartionById(id));
    }
}
